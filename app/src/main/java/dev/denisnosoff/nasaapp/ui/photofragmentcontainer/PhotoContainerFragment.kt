package dev.denisnosoff.nasaapp.ui.photofragmentcontainer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.denisnosoff.nasaapp.R
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.ui.photofragment.PhotoFragment
import kotlinx.android.synthetic.main.fragment_photo_container.*

class PhotoContainerFragment : Fragment() {

    companion object {

        const val TAG = "PhotoContainerFragment"

        const val PHOTOS_LIST_KEY = "PhotosListKey"
        const val POSITION_KEY = "Position"

        fun newInstance(photosList: List<PhotoRoomEntity>, position: Int) : PhotoContainerFragment {
            val fragment = PhotoContainerFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(PHOTOS_LIST_KEY, ArrayList(photosList))
            bundle.putInt(POSITION_KEY, position)
            fragment.arguments = bundle
            return fragment
        }

    }

    private lateinit var photosList: List<PhotoRoomEntity>
    private var position: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_photo_container, container, false)

        photosList = arguments?.get(PHOTOS_LIST_KEY) as List<PhotoRoomEntity>
        position = arguments?.get(POSITION_KEY) as Int



        return view
    }

    override fun onStart() {
        super.onStart()

        initContainer()
    }

    private fun initContainer() {
        photosContainer.adapter = StatePagerAdapter(
            photosList.map { PhotoFragment.newInstance(it) } ,
            childFragmentManager
        )
        photosContainer.currentItem = position
    }

}