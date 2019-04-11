package dev.denisnosoff.nasaapp.di.modules

import dagger.Module
import dagger.Provides
import dev.denisnosoff.nasaapp.ui.mainactivity.MainPresenter

@Module
class MvpModule {

    @Provides
    fun provideMainPresenter() = MainPresenter()

}