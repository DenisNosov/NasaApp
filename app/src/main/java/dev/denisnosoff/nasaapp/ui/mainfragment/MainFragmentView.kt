package dev.denisnosoff.nasaapp.ui.mainfragment

import com.arellomobile.mvp.MvpView
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.util.state.Statable
import dev.denisnosoff.nasaapp.util.state.State

interface MainFragmentView : MvpView {

    fun updateList(photosList: List<PhotoRoomEntity>)

    fun setSuccess()
    fun setLoading()
    fun setError(errorText: String)

}