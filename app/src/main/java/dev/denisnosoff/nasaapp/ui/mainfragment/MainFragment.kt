package dev.denisnosoff.nasaapp.ui.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import dev.denisnosoff.nasaapp.R
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.mvp.MvpAppCompatFragment
import dev.denisnosoff.nasaapp.util.hide
import dev.denisnosoff.nasaapp.util.show
import dev.denisnosoff.nasaapp.util.state.Statable
import dev.denisnosoff.nasaapp.util.state.State
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : MvpAppCompatFragment() , MainFragmentView , Statable{

    override var state: State = State.SUCCESSFUL
    set(value) {
        field = value
        changeUi(field)
    }

    override fun changeUi(state: State) {
        when (state) {
            State.LOADING -> {
                pbMainLoading.show()
                viewGroupMain.hide()
                tvMainError.hide()
            }
            State.SUCCESSFUL -> {
                pbMainLoading.hide()
                viewGroupMain.show()
                tvMainError.hide()
            }
            State.ERROR -> {
                pbMainLoading.hide()
                viewGroupMain.hide()
                tvMainError.show()
            }
        }
    }

    lateinit var shortClick: OnShortItemClickListener

    private lateinit var rvAdapter: PhotoAdapter
    private lateinit var recyclerView: RecyclerView

    private var photos = listOf<PhotoRoomEntity>()
    private var currentPosition: Int = 0

    @InjectPresenter
    lateinit var mainFragmentPresenter: MainFragmentPresenter

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        shortClick = activity as OnShortItemClickListener

        setupRecyclerView(view)

        mainFragmentPresenter.init()

        return view
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.recyclerView
        rvAdapter = PhotoAdapter(
            listOf(),
            object : OnLongItemClickListener {
                override fun onLongClick(photoId: Int) {
                    mainFragmentPresenter.longClick(photoId)
                }
            },
            shortClick)
        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)

    }

    override fun updateListWithNewPhotos(photosList: List<PhotoRoomEntity>) {
        photos = photosList
        if (photos.size >= 20) {
            rvAdapter.updatePhotos(photos.subList(0, 20))
        } else {
            rvAdapter.updatePhotos(photos)
        }
        currentPosition = 0
    }

    override fun setError(errorText: String) {
        state = State.ERROR
        tvMainError.text = errorText
    }

    override fun setSuccess() {
        state = State.SUCCESSFUL
    }

    override fun setLoading() {
        state = State.LOADING
    }
}