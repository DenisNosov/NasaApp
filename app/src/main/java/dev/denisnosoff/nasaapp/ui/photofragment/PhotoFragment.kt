package dev.denisnosoff.nasaapp.ui.photofragment

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dev.denisnosoff.nasaapp.R
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.mvp.MvpAppCompatFragment
import kotlinx.android.synthetic.main.fragment_photo.*
import kotlinx.android.synthetic.main.fragment_photo.view.*
import org.jetbrains.anko.custom.style
import org.jetbrains.anko.toast

class PhotoFragment : MvpAppCompatFragment(), PhotoView {

    @InjectPresenter
    lateinit var photoPresenter: PhotoPresenter

//    lateinit var pvPhoto: com.github.chrisbanes.photoview.PhotoView
//    lateinit var tvDate: TextView
//    lateinit var tvDescription: TextView

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photo, container, false)

//        pvPhoto = view.pvPhotoFullScreen
//        tvDate = view.tvDateFullScreen
//        tvDescription = view.tvDescriptionFullScreen

        photoPresenter.init(arguments?.get(PHOTO_KEY) as PhotoRoomEntity)

        return view
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).window.statusBarColor = Color.BLACK
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).window.statusBarColor = ContextCompat.getColor(context!!, R.color.colorPrimaryDark)
    }

    override fun setDate(date: String) {
        tvDateFullScreen.text = date
    }

    override fun setDescription(description: String, payRespect: Boolean) {
        tvDescriptionFullScreen.text = description
        if (payRespect) {
            tvDescriptionFullScreen.setTextColor(resources.getColor(R.color.colorLink))
            tvDescriptionFullScreen.setOnClickListener {
                context?.toast("My battery is low and it's getting dark")
            }
        }
    }

    override fun setPhoto(photoUrl: String) {
        Glide.with(this)
            .load(photoUrl)
            .into(pvPhotoFullScreen)
    }

}