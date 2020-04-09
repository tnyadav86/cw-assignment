package com.android.coolwinks.flickr.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.coolwings.R
import com.android.coolwinks.RecyclerViewItemClickListener
import com.android.coolwinks.flickr.model.Photo
import com.android.coolwinks.utils.Util
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class FlickrViewHolder(
    parent: ViewGroup,
    recyclerViewItemClickListener: RecyclerViewItemClickListener
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_flickr, parent, false)
) {

    private val imgUser = itemView.findViewById<ImageView>(R.id.imgUser)
    private val lastUpdateTime = itemView.findViewById<TextView>(R.id.lastUpdateTime)
    var photo: Photo? = null

    init {

        itemView.setOnClickListener {
            recyclerViewItemClickListener.onItemClick(photo)
        }
    }

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(photo: Photo?) {
        this.photo = photo
        lastUpdateTime.text = Util.calculateAge(photo?.lastupdate)

        Glide.with(itemView.context)
            .load(photo?.url_q)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_person)
            .into(imgUser)

    }
}