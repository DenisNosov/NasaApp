package dev.denisnosoff.nasaapp.di

import dagger.Component
import dev.denisnosoff.nasaapp.di.modules.AppModule
import dev.denisnosoff.nasaapp.di.modules.NasaApiModule
import dev.denisnosoff.nasaapp.di.modules.RoomDatabaseModule
import dev.denisnosoff.nasaapp.ui.mainactivity.MainActivity

@Component(modules = [AppModule::class, NasaApiModule::class, RoomDatabaseModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}