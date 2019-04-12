package dev.denisnosoff.nasaapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity

@Database(entities = [PhotoRoomEntity::class], version = 1, exportSchema = false)
abstract class PhotosDataBase : RoomDatabase() {

    abstract fun photosDao(): PhotosDao

}