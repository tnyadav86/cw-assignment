package com.android.coolwinks.users.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.coolwinks.RecyclerViewItemClickListener
import com.android.coolwinks.users.model.User

class UserMessageAdapter(val recyclerViewItemClickListener: RecyclerViewItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var user = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        UserMessageViewHolder(parent, recyclerViewItemClickListener)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = user[position]
        (holder as UserMessageViewHolder).bindTo(user)
    }

    override fun getItemCount(): Int {
        return user.size
    }

    fun getItemAtPosition(position: Int): User {
        return user.get(position)
    }

    fun submitList(newUserList: List<User>) {
        user.clear()
        user.addAll(newUserList)
        notifyDataSetChanged()
    }
}