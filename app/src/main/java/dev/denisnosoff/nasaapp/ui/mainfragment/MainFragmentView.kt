package dev.denisnosoff.nasaapp.ui.mainfragment

import com.arellomobile.mvp.MvpView
import dev.denisnosoff.nasaapp.util.state.State

interface MainFragmentView : MvpView {

    fun setUiState(state: State)

}