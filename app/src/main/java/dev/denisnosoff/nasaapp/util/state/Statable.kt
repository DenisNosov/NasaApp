package dev.denisnosoff.nasaapp.util.state

interface Statable {

    var state: State

    fun changeUi(state: State)
}