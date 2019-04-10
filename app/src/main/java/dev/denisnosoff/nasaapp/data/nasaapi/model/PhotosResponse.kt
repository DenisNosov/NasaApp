package dev.denisnosoff.nasaapp.data.nasaapi.model

data class PhotosResponse(
    val latest_photos: List<LatestPhoto> = listOf()
)