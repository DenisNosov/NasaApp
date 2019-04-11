package dev.denisnosoff.nasaapp.ui.photofragment

import android.text.format.DateFormat
import android.util.Log.d
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import java.text.SimpleDateFormat

@InjectViewState
class PhotoPresenter : MvpPresenter<PhotoView>() {


    fun init(photo: PhotoRoomEntity?) {

        d("TAG", photo.toString())

        photo?.let {
            val date = SimpleDateFormat("yyyy-MM-dd").parse(it.earthDate)
            val dateString = DateFormat.format("dd.MM.yyyy", date)

            d("TAG", it.id.toString())

            viewState.setDate(dateString.toString())
            viewState.setDescription("Made by ${it.roverName} rover on ${it.cameraName}")
            viewState.setPhoto(it.imgSrc)
        }
    }
}