package dev.denisnosoff.nasaapp.ui.mainactivity

import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.util.Router

class MainPresenter {

    lateinit var router: Router

    fun init(_router: Router) {
        router = _router
        router.init()

//        router.navigateToPhotoFragment(
//            PhotoRoomEntity(
//                673453,
//                "Front Hazard Avoidance Camera",
//                "2019-04-10",
//                "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/02373/opgs/edr/fcam/FLB_608161147EDR_F0751386FHAZ00337M_.JPG",
//                "Curiosity"
//        )
//        )
    }

    fun onShortItemClick(photo: PhotoRoomEntity) {
        router.navigateToPhotoFragment(photo)
    }
}