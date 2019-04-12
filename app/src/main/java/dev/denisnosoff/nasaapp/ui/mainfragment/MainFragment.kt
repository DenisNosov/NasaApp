package dev.denisnosoff.nasaapp.ui.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.arellomobile.mvp.presenter.InjectPresenter
import dev.denisnosoff.nasaapp.App
import dev.denisnosoff.nasaapp.R
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.mvp.MvpAppCompatFragment
import dev.denisnosoff.nasaapp.util.hide
import dev.denisnosoff.nasaapp.util.show
import dev.denisnosoff.nasaapp.util.state.Statable
import dev.denisnosoff.nasaapp.util.state.State
import kotlinx.android.synthetic.main.fragment_main.*


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
        mainFragmentPresenter.init()

        return view
    }

    override fun updateList(photosList: List<PhotoRoomEntity>) {

    }

    override fun setError(text: String) {
        state = State.ERROR
        tvMainError.text = text
    }

    override fun setSuccess() {
        state = State.SUCCESSFUL
    }

    override fun setLoading() {
        state = State.LOADING
    }
}