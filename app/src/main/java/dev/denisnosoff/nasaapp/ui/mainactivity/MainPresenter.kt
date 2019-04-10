package dev.denisnosoff.nasaapp.ui.mainactivity

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.util.Router

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    lateinit var router: Router

    fun init(router_: Router) {
        router = router_
        router.navigateToMainFragment()
    }

    fun onShortItemClick(photo: PhotoRoomEntity) {
        router.navigateToPhotoFragment(photo)
    }
}