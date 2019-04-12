package dev.denisnosoff.nasaapp.di

import dagger.Component
import dev.denisnosoff.nasaapp.di.modules.*
import dev.denisnosoff.nasaapp.ui.mainactivity.MainActivity
import dev.denisnosoff.nasaapp.ui.mainfragment.MainFragmentPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NasaApiModule::class,
    RoomDatabaseModule::class,
    MvpModule::class,
    SharedPrefsModule::class
])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(mainFragmentPresenter: MainFragmentPresenter)

}