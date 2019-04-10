package dev.denisnosoff.nasaapp.data.room.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class PhotoRoomEntity (
    @PrimaryKey val id: Int = 0,
    val cameraName: String = "",
    val earthDate: String = "",
    val imgSrc: String = "",
    val roverName: String = ""
) : Parcelable