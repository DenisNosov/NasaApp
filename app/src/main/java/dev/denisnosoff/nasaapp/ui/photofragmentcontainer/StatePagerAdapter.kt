package dev.denisnosoff.nasaapp.ui.photofragmentcontainer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class StatePagerAdapter(
    private var photoFragments: List<Fragment>,
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = photoFragments[position]

    override fun getCount(): Int = photoFragments.size

}