package dev.denisnosoff.nasaapp.ui.photofragment

import android.os.Bundle
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.mvp.MvpAppCompatFragment

class PhotoFragment : MvpAppCompatFragment() {

    companion object {
        const val TAG = "PhotoFragment"

        const val PHOTO_KEY = "PhotoKey"

        fun newInstance(photo: PhotoRoomEntity) : PhotoFragment {
            val bundle = Bundle()
            bundle.putParcelable(PHOTO_KEY, photo)
            val fragment = PhotoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}