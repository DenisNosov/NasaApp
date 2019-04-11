package dev.denisnosoff.nasaapp.data.nasaapi

import dev.denisnosoff.nasaapp.data.nasaapi.model.PhotosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    @GET("mars-photos/api/v1/rovers/curiosity/latest_photos")
    fun getCuriosityLatestPhotos(@Query("api_key") apiKey : String) : Single<PhotosResponse>

    @GET("mars-photos/api/v1/rovers/opportunity/latest_photos")
    fun getOpportunityLatestPhotos(@Query("api_key") apiKey : String) : Single<PhotosResponse>

    @GET("mars-photos/api/v1/rovers/spirit/latest_photos")
    fun getSpiritLatestPhotos(@Query("api_key") apiKey : String) : Single<PhotosResponse>

}