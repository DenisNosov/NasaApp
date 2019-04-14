package dev.denisnosoff.nasaapp.ui.mainfragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import dev.denisnosoff.nasaapp.App
import dev.denisnosoff.nasaapp.data.nasaapi.NasaApi
import dev.denisnosoff.nasaapp.data.nasaapi.model.LatestPhoto
import dev.denisnosoff.nasaapp.data.room.PhotosDataBase
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.data.sharedprefs.SharedPrefs
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

    private var deletedPhotosList = arrayListOf<Int>()

    @Inject
    lateinit var nasaApi: NasaApi

    @Inject
    lateinit var photosDataBase: PhotosDataBase

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    fun init() {
        viewState.setLoading()

        App.appComponent.inject(this)

        deletedPhotosList = sharedPrefs.photosList

        getPhotos()
    }

    private fun getPhotos() {

        val gettingPhotos = nasaApi.getCuriosityLatestPhotos(API_KEY)
            .flatMap { it.latest_photos.toObservable() }
            .map { PhotoRoomEntity(
                it.id,
                it.camera.full_name,
                it.earth_date,
                it.img_src,
                it.rover.name
            ) }
            .filter { !deletedPhotosList.contains(it.id) }
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
            .map { it.reversed() }
            .map { it.filter { !deletedPhotosList.contains(it.id) } }
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
        viewState.updateListWithNewPhotos(photosList)
        viewState.setSuccess()
    }

    private fun saveData(photosList: List<PhotoRoomEntity>) {

        photosDataBase.photosDao().clearDatabase()
            .doOnComplete {
                val savingPhotos = photosDataBase.photosDao().insertPhotos(*photosList.toTypedArray())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()

                compositeDisposable.addAll(savingPhotos)
            }
            .subscribeOn(Schedulers.io()).subscribe()




    }

    fun longClick(photoId: Int) {
        deletedPhotosList.add(photoId)
        val deletingPhoto = photosDataBase.photosDao().deleteById(photoId)
            .subscribeOn(Schedulers.io())
            .subscribe {
                tryToShowDataFromStorage()
            }

        compositeDisposable.add(deletingPhoto)
    }

    override fun onDestroy() {

        sharedPrefs.photosList = deletedPhotosList

        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onDestroy()
    }

}