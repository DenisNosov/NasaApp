package dev.denisnosoff.nasaapp.ui.mainfragment

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import dev.denisnosoff.nasaapp.App
import dev.denisnosoff.nasaapp.data.nasaapi.NasaApi
import dev.denisnosoff.nasaapp.data.nasaapi.model.LatestPhoto
import dev.denisnosoff.nasaapp.data.room.PhotosDataBase
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.util.state.State
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.merge
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class MainFragmentPresenter : MvpPresenter<MainFragmentView>() {

    private val API_KEY = "6Ls71CpTX46zakFBTv29wWeAT68XIQLDIz5KFL1Q"

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var nasaApi: NasaApi

    @Inject
    lateinit var photosDataBase: PhotosDataBase

    fun init() {
        viewState.setLoading()

        App.appComponent.inject(this)

        getPhotos()
    }

    private fun getPhotos() {
        val curiosityPhotos = nasaApi.getCuriosityLatestPhotos(API_KEY)
            .flatMap { it.latest_photos.toObservable() }
        val opportunityPhotos = nasaApi.getOpportunityLatestPhotos(API_KEY)
            .flatMap { it.latest_photos.toObservable() }
        val spiritPhotos = nasaApi.getSpiritLatestPhotos(API_KEY)
            .flatMap { it.latest_photos.toObservable() }

//        Log.d("TAG", "logging photos $curiosityPhotos")

        val gettingPhotos = listOf<Observable<LatestPhoto>>(curiosityPhotos, opportunityPhotos, spiritPhotos)
            .merge()
            .map { PhotoRoomEntity(
                it.id,
                it.camera.full_name,
                it.earth_date,
                it.img_src,
                it.rover.name
            ) }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    saveData(it)
                    showData(it)
                }, {
                    tryToShowDataFromStorage()
                }
            )

        compositeDisposable.add(gettingPhotos)
    }

    private fun tryToShowDataFromStorage() {
        val gettingPhotosFromStorage = photosDataBase.photosDao().getAllPhotos()
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showData(it)
            }, {
                viewState.setError("Error loading photos")
            })

        compositeDisposable.add(gettingPhotosFromStorage)
    }

    private fun showData(photosList: List<PhotoRoomEntity>) {
        viewState.updateList(photosList)
    }

    private fun saveData(photosList: List<PhotoRoomEntity>) {
        val savingPhotos = photosDataBase.photosDao().insertPhotos(*photosList.toTypedArray())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

        compositeDisposable.add(savingPhotos)
    }

    override fun onDestroy() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onDestroy()
    }
}