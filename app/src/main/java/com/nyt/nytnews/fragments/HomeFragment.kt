package com.nyt.nytnews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.nyt.nytnews.R
import com.nyt.nytnews.adapters.HomeFragmentPageAdapter
import com.nyt.nytnews.utility.Constants
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * This is home fragment, Which holds the viewpager
 * @author Shreyash Singh
 * @version 1.0
 */

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewPager()
    }

    /**
     * Function to setup viewpager
     */
    private fun setUpViewPager() {
        val tabLayout = tabLayoutHomeFrag
        val viewPager = viewPagerHomeFrag

        val homeFragAdapter = HomeFragmentPageAdapter(this)
        viewPager.adapter = homeFragAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = Constants.homeTabLayoutNames[position]
        }.attach()
    }
}