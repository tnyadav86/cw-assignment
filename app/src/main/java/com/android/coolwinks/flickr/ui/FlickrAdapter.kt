package com.android.coolwinks.flickr.ui

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.coolwinks.RecyclerViewItemClickListener
import com.android.coolwinks.flickr.model.Photo

class FlickrAdapter(private val recyclerViewItemClickListener: RecyclerViewItemClickListener) :
    PagedListAdapter<Photo, RecyclerView.ViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        FlickrViewHolder(parent, recyclerViewItemClickListener)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FlickrViewHolder).bindTo(getItem(position))
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem == newItem
        }
    }
}