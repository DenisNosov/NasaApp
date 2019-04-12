package dev.denisnosoff.nasaapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface PhotosDao {

    @Query("SELECT * FROM PhotoRoomEntity")
    fun getAllPhotos() : Observable<List<PhotoRoomEntity>>

    @Query("DELETE FROM PhotoRoomEntity WHERE id = :photoId")
    fun deleteById(photoId: Int) : Completable

    @Query("DELETE FROM PhotoRoomEntity")
    fun clearDatabase() : Completable

    @Insert
    fun insertPhoto(photo: PhotoRoomEntity) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotos(vararg photos: PhotoRoomEntity) : Completable
}