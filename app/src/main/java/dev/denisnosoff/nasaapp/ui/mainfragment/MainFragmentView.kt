package dev.denisnosoff.nasaapp.ui.mainfragment

import com.arellomobile.mvp.MvpView
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity

interface MainFragmentView : MvpView {

    fun updateListWithNewPhotos(photosList: List<PhotoRoomEntity>)

    fun setSuccess()
    fun setLoading()
    fun setError(errorText: String)

}