package dev.denisnosoff.nasaapp.ui.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import dev.denisnosoff.nasaapp.R
import dev.denisnosoff.nasaapp.data.room.model.PhotoRoomEntity
import dev.denisnosoff.nasaapp.mvp.MvpAppCompatFragment
import dev.denisnosoff.nasaapp.util.hide
import dev.denisnosoff.nasaapp.util.show
import dev.denisnosoff.nasaapp.util.state.Statable
import dev.denisnosoff.nasaapp.util.state.State
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : MvpAppCompatFragment() , MainFragmentView , Statable {

    override fun setUiState(_state: State) {
        state = _state
    }

    override var state: State = State.SUCCESSFUL
    set(value) {
        field = value
        changeUi(field)
    }

    override fun changeUi(state: State) {
        when (state) {
            State.LOADING -> {
                mainProgressBar.show()
                mainViewGroup.hide()
                mainErrorTextView.hide()
            }
            State.SUCCESSFUL -> {
                mainProgressBar.hide()
                mainViewGroup.show()
                mainErrorTextView.hide()
            }
            State.ERROR -> {
                mainProgressBar.hide()
                mainViewGroup.hide()
                mainErrorTextView.show()
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

        mainFragmentPresenter.init()

        return view
    }
}