package dev.denisnosoff.nasaapp.ui.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.denisnosoff.nasaapp.App
import dev.denisnosoff.nasaapp.R
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.ui.mainfragment.MainFragment
import dev.denisnosoff.nasaapp.util.Router
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainFragment.OnShortItemClickListener {

    override fun onShortClick(photos: List<PhotoRoomEntity>, position: Int) {
        mainPresenter.onShortItemClick(photos, position)
    }

    @Inject
    lateinit var mainPresenter: MainPresenter

    private val router = Router(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.appComponent.inject(this)

        mainPresenter.init(router)
    }
}
