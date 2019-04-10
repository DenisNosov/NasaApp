package dev.denisnosoff.nasaapp.util.state

interface Statable {
    fun changeUi(state: State)
}