package dev.denisnosoff.nasaapp.ui.mainactivity

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import dev.denisnosoff.nasaapp.R
import dev.denisnosoff.nasaapp.mvp.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity() , MainView {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
