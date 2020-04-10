package com.android.coolwinks.users.ui

import android.text.InputType
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
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
        if (adapterPosition % 2 == 0) {
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))
        } else {
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.grayLight))
        }
        if (!user!!.isExpended){
            title.setElegantTextHeight(false);
            title.setInputType(InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE);
            title.setSingleLine(true)
            title.maxLines=1
            title.setEllipsize(TextUtils.TruncateAt.END);

            body.setElegantTextHeight(false);
            body.setInputType(InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE);
            body.setSingleLine(true)
            body.maxLines=1
            body.setEllipsize(TextUtils.TruncateAt.END);
        }else{
            title.setElegantTextHeight(true);
            title.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            title.setSingleLine(false)

            body.setElegantTextHeight(true);
            body.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            body.setSingleLine(false)
        }

    }
}