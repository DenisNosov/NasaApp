package dev.denisnosoff.nasaapp

import android.app.Application
import dev.denisnosoff.nasaapp.di.AppComponent
import dev.denisnosoff.nasaapp.di.DaggerAppComponent
import dev.denisnosoff.nasaapp.di.modules.AppModule
import dev.denisnosoff.nasaapp.di.modules.NasaApiModule
import dev.denisnosoff.nasaapp.di.modules.RoomDatabaseModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .nasaApiModule(NasaApiModule())
            .roomDatabaseModule(RoomDatabaseModule())
            .build()
    }

}