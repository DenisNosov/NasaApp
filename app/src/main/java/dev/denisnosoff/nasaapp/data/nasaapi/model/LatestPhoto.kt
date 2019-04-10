package dev.denisnosoff.nasaapp.data.nasaapi.model

data class LatestPhoto(
    val camera: Camera = Camera(),
    val earth_date: String = "",
    val id: Int = 0,
    val img_src: String = "",
    val rover: Rover = Rover()
)