package com.nyt.nytnews.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nyt.nytnews.fragments.BookMarkedFragmentTop
import com.nyt.nytnews.fragments.TopTopStoriesFragment

/**
 * This is Fragment page adapter, Responsible for switching between fragments on home screen
 * @author Shreyash Singh
 * @version 1.0
 */
class HomeFragmentPageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    companion object {
        const val NO_OF_FRAGMENTS = 2
    }

    /**
     * Method to get count of the fragments
     */
    override fun getItemCount() = NO_OF_FRAGMENTS

    /**
     * Method to switch fragments
     * @param position It takes a integer value which specifies the fragment to be switched.
     */
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TopTopStoriesFragment()
            else -> BookMarkedFragmentTop()
        }
    }

}