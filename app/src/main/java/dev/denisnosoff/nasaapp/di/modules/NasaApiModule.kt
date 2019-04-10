package dev.denisnosoff.nasaapp.di.modules

import dagger.Module
import dagger.Provides
import dev.denisnosoff.nasaapp.data.nasaapi.NasaApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NasaApiModule {

    private val BASE_URL = "https://api.nasa.gov/"

    @Singleton
    @Provides
    fun provideNasaApi() : NasaApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(NasaApi::class.java)
    }

}