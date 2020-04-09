package com.android.coolwinks.users.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.coolwinks.RecyclerViewItemClickListener

class UserAdapter(private val recyclerViewItemClickListener: RecyclerViewItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var user = ArrayList<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        UserViewHolder(parent, recyclerViewItemClickListener)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = user[position]
        (holder as UserViewHolder).bindTo(user)
    }

    companion object;

    override fun getItemCount(): Int {
        return user.size
    }

    fun submitList(newUserIdList: List<Int>) {
        user.clear()
        user.addAll(newUserIdList)
        notifyDataSetChanged()
    }
}