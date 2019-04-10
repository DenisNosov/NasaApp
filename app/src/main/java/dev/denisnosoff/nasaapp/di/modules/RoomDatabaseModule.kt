package dev.denisnosoff.nasaapp.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.denisnosoff.nasaapp.data.room.PhotosDataBase
import javax.inject.Singleton

@Module
class RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context) : PhotosDataBase {
        return Room.databaseBuilder(context, PhotosDataBase::class.java, "database")
            .build()
    }

}