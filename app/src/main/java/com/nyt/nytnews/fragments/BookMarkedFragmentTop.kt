package com.nyt.nytnews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nyt.nytnews.R
import com.nyt.nytnews.adapters.TopStoriesAdapter
import com.nyt.nytnews.models.Result
import com.nyt.nytnews.utility.Utility
import com.nyt.nytnews.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_book_marked.*

/**
 * This is Bookmarked Fragment class.
 * @author Shreyash Singh
 * @version 1.0
 */
class BookMarkedFragmentTop : Fragment(), TopStoriesAdapter.TopStoriesAdapterListener {

    lateinit var topStoriesAdapter: TopStoriesAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_marked, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpLiveDataObservers()
    }

    /**
     * Setting up live data observers
     */
    private fun setUpLiveDataObservers() {
        viewModel.bookmarks.observe(viewLifecycleOwner, {
            topStoriesAdapter.differ.submitList(it)
            if (it.isNotEmpty())
                showHideNoDataFound(false)
            else
                showHideNoDataFound(true)
        })
    }

    /**
     * Setting up RecyclerView
     */
    private fun setUpRecyclerView() {
        val lm = GridLayoutManager(requireContext(), 2)
        lm.orientation = LinearLayoutManager.VERTICAL

        topStoriesAdapter = TopStoriesAdapter(this)

        rv_bookmarks.apply {
            adapter = topStoriesAdapter
            layoutManager = lm
        }
    }

    /**
     * Callback method which gets invoked on bookmarked removed.
     */
    override fun onBookMarked(result: Result) {
        viewModel.removeBookMark(result)
    }

    /**
     * On-click callback function which gets invoked when user clicks in the list item
     */
    override fun onClicked(result: Result) {
        viewModel.clickedResult = result
        Utility.navigateFragment(
            requireActivity().supportFragmentManager,
            R.id.mainFragContainer,
            DetailedFragment(),
            "detailedFrag"
        )
    }


    override fun onResume() {
        super.onResume()

        //Refresh BookMark Data every time the fragment is visible
        viewModel.getBookmarks()
    }

    /**
     *  Displays helper images when the bookmark list is empty
     */

    private fun showHideNoDataFound(show: Boolean = false) {
        val visibility = if (show)
            View.VISIBLE
        else
            View.GONE

        iv_noSearchBookmarks.visibility = visibility
        tv_noSearchTextBookMarks.visibility = visibility
    }
}