package dev.denisnosoff.nasaapp.ui.mainfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.denisnosoff.nasaapp.R
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.ui.mainfragment.MainFragment.*
import kotlinx.android.synthetic.main.item_image.view.*

class PhotoAdapter(
    private var photos: List<PhotoRoomEntity>,
    private val onLongItemClickListener: OnLongItemClickListener,
    private val getPhotoPosition : GetPhotoPosition
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))

    override fun getItemCount() = photos.size

    override fun getItemId(position: Int) = photos[position].id.toLong()

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(
            photos[position],
            onLongItemClickListener,
            getPhotoPosition,
            position
            )
    }

    fun updatePhotos(_photos: List<PhotoRoomEntity>) {
        photos = _photos
        notifyDataSetChanged()
    }

    class PhotoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(
            photo: PhotoRoomEntity,
            longClick: OnLongItemClickListener,
            getPhotoPosition: GetPhotoPosition,
            position: Int
        ) {
            Glide.with(view)
                .load(photo.imgSrc)
                .into(view.ivPhoto)
            view.setOnClickListener { getPhotoPosition.invoke(position) }
            view.setOnLongClickListener {
                longClick.onLongClick(photo.id)
                return@setOnLongClickListener true
            }
        }

    }
}