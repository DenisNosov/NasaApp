package dev.denisnosoff.nasaapp.ui.mainactivity

import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.util.Router

class MainPresenter {

    lateinit var router: Router

    fun init(_router: Router) {
        router = _router
        router.init()
    }

    fun onShortItemClick(photos: List<PhotoRoomEntity>, position: Int) {
        router.navigateToPhotoFragment(photos, position)
    }
}