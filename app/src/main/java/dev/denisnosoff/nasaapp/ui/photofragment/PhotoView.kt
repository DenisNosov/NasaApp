package dev.denisnosoff.nasaapp.ui.photofragment

import com.arellomobile.mvp.MvpView

interface PhotoView : MvpView {

    fun setDate(date: String)

    fun setDescription(description: String)

    fun setPhoto(photoUrl: String)

}