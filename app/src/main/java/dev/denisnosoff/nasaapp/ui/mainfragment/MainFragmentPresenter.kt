package dev.denisnosoff.nasaapp.ui.mainfragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import dev.denisnosoff.nasaapp.util.state.State

@InjectViewState
class MainFragmentPresenter : MvpPresenter<MainFragmentView>() {
    fun init() {
        viewState.setUiState(State.LOADING)
    }
}