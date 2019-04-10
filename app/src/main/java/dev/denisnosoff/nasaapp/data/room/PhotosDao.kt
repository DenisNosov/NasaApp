package dev.denisnosoff.nasaapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface PhotosDao {

    @Query("SELECT * from PhotoRoomEntity")
    fun getAllPhotos() : Observable<PhotoRoomEntity>

    @Query("DELETE FROM PhotoRoomEntity WHERE id = :photoId")
    fun deleteById(photoId: Int)

    @Insert
    fun insertPhoto(photo: PhotoRoomEntity) : Completable

    @Insert
    fun insertPhotos(vararg photos: PhotoRoomEntity) : Completable
}