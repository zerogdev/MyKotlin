package com.mysample.disneymotions.view.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import timber.log.Timber

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Timber.tag("LYK").d("MainPagerAdapter-HomeFragment")
                HomeFragment()
            }
            1 -> {
                Timber.tag("LYK").d("MainPagerAdapter-LibraryFragment")
                LibraryFragment()
            }
            else -> {
                Timber.tag("LYK").d("MainPagerAdapter-RadioFragment")
                RadioFragment()
            }
        }
    }

    override fun getCount(): Int = 1
}