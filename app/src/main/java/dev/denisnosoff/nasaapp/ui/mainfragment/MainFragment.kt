package dev.denisnosoff.nasaapp.ui.mainfragment

import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.mvp.MvpAppCompatFragment


class MainFragment : MvpAppCompatFragment() {

    lateinit var shortClick: OnShortItemClickListener

    companion object {
        const val TAG = "MainFragment"

        fun newInstance() : MainFragment {
            return MainFragment()
        }
    }

    interface OnShortItemClickListener {
        fun onShortClick(photo: PhotoRoomEntity)
    }

    interface OnLongItemClickListener {
        fun onLongClick(photoId: Int)
    }


}