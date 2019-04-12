package dev.denisnosoff.nasaapp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.denisnosoff.nasaapp.data.sharedprefs.SharedPrefs
import javax.inject.Singleton

@Module
class SharedPrefsModule {

    @Singleton
    @Provides
    fun provideSharedPrefs(context: Context) : SharedPrefs {
        return SharedPrefs(context)
    }
}