package dev.denisnosoff.nasaapp.ui.mainactivity

import android.os.Bundle
import dev.denisnosoff.nasaapp.R
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.mvp.MvpAppCompatActivity
import dev.denisnosoff.nasaapp.ui.mainfragment.MainFragment
import dev.denisnosoff.nasaapp.util.Router
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity() , MainView , MainFragment.OnShortItemClickListener {

    override fun onShortClick(photo: PhotoRoomEntity) {
        mainPresenter.onShortItemClick(photo)
    }

    @Inject
    lateinit var mainPresenter: MainPresenter

    private val router = Router(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter.init(router)
    }
}
