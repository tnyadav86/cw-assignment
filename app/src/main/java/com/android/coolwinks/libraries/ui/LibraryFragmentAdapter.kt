package com.android.coolwinks.libraries.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.coolwinks.libraries.model.UsedLibraryItem

class LibraryFragmentAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var usedLibraryItemList = ArrayList<UsedLibraryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        LibraryFragmentViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val usedLibraryItem = usedLibraryItemList[position]
        (holder as LibraryFragmentViewHolder).bindTo(usedLibraryItem)
    }

    override fun getItemCount(): Int {
        return usedLibraryItemList.size
    }

    fun submitList(newUserList: List<UsedLibraryItem>) {
        usedLibraryItemList.clear()
        usedLibraryItemList.addAll(newUserList)
        notifyDataSetChanged()
    }
}