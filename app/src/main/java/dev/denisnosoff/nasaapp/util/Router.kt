package dev.denisnosoff.nasaapp.util

import androidx.fragment.app.FragmentManager
import dev.denisnosoff.nasaapp.R
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.ui.mainfragment.MainFragment
import dev.denisnosoff.nasaapp.ui.photofragment.PhotoFragment

class Router(private val fragmentManager: FragmentManager) {

    private val id = R.id.container
    private val TRANSACTION_NAME = "PHOTO_FRAGMENT_TRANSACTION"

    fun init() {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(id, MainFragment.newInstance(), MainFragment.TAG)
        transaction.commitNow()
    }

    fun navigateToPhotoFragment(photo: PhotoRoomEntity) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(id, PhotoFragment.newInstance(photo), PhotoFragment.TAG)
        transaction.addToBackStack(TRANSACTION_NAME)
        transaction.commit()
    }

}