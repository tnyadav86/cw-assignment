package com.android.coolwinks.libraries.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.coolwings.R
import com.android.coolwinks.libraries.model.UsedLibraryItem

class LibraryFragmentViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_library, parent, false)
) {

    private val libraryName = itemView.findViewById<TextView>(R.id.textLibraryName)
    private val libraryDescription = itemView.findViewById<TextView>(R.id.textLibraryDescription)
    private var usedLibraryItem: UsedLibraryItem? = null

    fun bindTo(usedLibraryItem: UsedLibraryItem) {
        this.usedLibraryItem = usedLibraryItem
        libraryName.text = usedLibraryItem.title
        libraryDescription.text = usedLibraryItem.descriptions
    }
}