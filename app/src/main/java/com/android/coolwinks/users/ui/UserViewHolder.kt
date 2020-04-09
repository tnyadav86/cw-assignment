package com.android.coolwinks.users.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.coolwings.R
import com.android.coolwinks.RecyclerViewItemClickListener

class UserViewHolder(
    parent: ViewGroup,
    recyclerViewItemClickListener: RecyclerViewItemClickListener
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {

    private val nameView = itemView.findViewById<TextView>(R.id.textUserId)
    private var userId: Int = 0

    init {

        itemView.setOnClickListener {
            recyclerViewItemClickListener.onItemClick(userId)
        }
    }

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(user: Int) {
        this.userId = user
        nameView.text = "User$userId"
    }
}