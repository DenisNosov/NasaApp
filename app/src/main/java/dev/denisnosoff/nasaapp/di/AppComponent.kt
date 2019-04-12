package dev.denisnosoff.nasaapp.di

import dagger.Component
import dev.denisnosoff.nasaapp.di.modules.AppModule
import dev.denisnosoff.nasaapp.di.modules.MvpModule
import dev.denisnosoff.nasaapp.di.modules.NasaApiModule
import dev.denisnosoff.nasaapp.di.modules.RoomDatabaseModule
import dev.denisnosoff.nasaapp.ui.mainactivity.MainActivity
import dev.denisnosoff.nasaapp.ui.mainfragment.MainFragmentPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NasaApiModule::class, RoomDatabaseModule::class, MvpModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(mainFragmentPresenter: MainFragmentPresenter)

}