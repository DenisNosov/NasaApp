package dev.denisnosoff.nasaapp

import android.app.Application
import dev.denisnosoff.nasaapp.di.AppComponent
import dev.denisnosoff.nasaapp.di.DaggerAppComponent
import dev.denisnosoff.nasaapp.di.modules.AppModule
import dev.denisnosoff.nasaapp.di.modules.MvpModule
import dev.denisnosoff.nasaapp.di.modules.NasaApiModule
import dev.denisnosoff.nasaapp.di.modules.RoomDatabaseModule

class App :  Application(){

    companion object {
        private val BASE_URL = "https://api.nasa.gov/"

        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .mvpModule(MvpModule())
            .nasaApiModule(NasaApiModule(BASE_URL))
            .roomDatabaseModule(RoomDatabaseModule())
            .build()

        super.onCreate()
    }

}