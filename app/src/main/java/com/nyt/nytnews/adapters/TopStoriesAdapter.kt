package com.nyt.nytnews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nyt.nytnews.R
import com.nyt.nytnews.models.Result
import kotlinx.android.synthetic.main.top_stories_cell.view.*
import java.lang.Exception

/**
 * This is the Recycler View adapter class for top stories fragment
 * @author Shreyash Singh
 * @version 1.0
 */
class TopStoriesAdapter(private val topStoriesAdapterListener: TopStoriesAdapterListener) :
    RecyclerView.Adapter<TopStoriesAdapter.TopStoriesViewHolder>() {
    interface TopStoriesAdapterListener {
        fun onBookMarked(result: Result)
        fun onClicked(result: Result)
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.shortUrl == newItem.shortUrl
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.bookmarked == newItem.bookmarked
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)

    inner class TopStoriesViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        fun init(result: Result) {

            itemView.setOnClickListener {
                topStoriesAdapterListener.onClicked(result)
            }


            try {
                itemView.iv_topStories.load(result.multimedia[3].url) {
                    placeholder(R.drawable.placeholder)
                }
            } catch (e: Exception) {
                itemView.iv_topStories.setImageResource(R.drawable.placeholder)
            }

            itemView.tv_titleTopStories.text = result.title
            itemView.tv_dateTopStories.text = result.displayDate

            setImage(result, itemView.ib_topStoriesCell)

            itemView.ib_topStoriesCell.setOnClickListener {
                result.bookmarked = !result.bookmarked
                setImage(result, (it as ImageButton), true)
            }
        }

        /**
         * Method to change bookmark image based on the user click
         * @param result result of the button click.
         * @param imageButton reference if image button
         * @param change control parameter used for switching the conditions.
         **/
        private fun setImage(result: Result, imageButton: ImageButton, change: Boolean = false) {
            if (result.bookmarked) {
                imageButton.setImageResource(R.drawable.ic_baseline_bookmark_24)
                //  Toast.makeText(itemView.context, "Bookmark Added!", Toast.LENGTH_SHORT).show()
            } else {
                imageButton.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
                //  Toast.makeText(itemView.context, "Bookmark Removed!", Toast.LENGTH_SHORT).show()
            }
            if (change)
                topStoriesAdapterListener.onBookMarked(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopStoriesViewHolder {
        return TopStoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.top_stories_cell, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TopStoriesViewHolder, position: Int) {
        holder.init(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}