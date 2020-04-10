package com.android.coolwinks.users.ui

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.coolwings.R
import com.android.coolwinks.RecyclerViewItemClickListener
import com.android.coolwinks.users.model.User

class UserMessageViewHolder(
    parent: ViewGroup,
    recyclerViewItemClickListener: RecyclerViewItemClickListener
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user_message, parent, false)
) {

    private val title = itemView.findViewById<TextView>(R.id.textMessageTitle)
    private val body = itemView.findViewById<TextView>(R.id.textMessageBody)
    private var user: User? = null

    init {
        itemView.setOnClickListener {

            recyclerViewItemClickListener.onItemClick(adapterPosition)


        }
    }

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(user: User) {
        this.user = user
        title.text = user.title
        body.text = user.body

        if (!user.isExpended) {
            title.isSingleLine = true
            title.maxLines = 1
            title.ellipsize = TextUtils.TruncateAt.END

            body.isSingleLine = true
            body.maxLines = 1
            body.ellipsize = TextUtils.TruncateAt.END
        } else {
            title.isSingleLine = false

            body.isSingleLine = false
        }

    }
}