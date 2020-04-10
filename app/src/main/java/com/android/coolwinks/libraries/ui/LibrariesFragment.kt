package com.android.coolwinks.libraries.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.coolwings.R
import com.android.coolwinks.BaseDaggerFragment
import com.android.coolwinks.libraries.model.UsedLibraryItem
import com.android.coolwinks.libraries.viewmodel.LibraryViewModel
import com.android.coolwinks.utils.gone
import kotlinx.android.synthetic.main.fragment_libraries.*
import kotlinx.android.synthetic.main.fragment_user_message.loadingView
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class LibrariesFragment : BaseDaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val viewModel: LibraryViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactory).get(LibraryViewModel::class.java)
    }
    private lateinit var libraryFragmentAdapter: LibraryFragmentAdapter
    private val usedLibraryDataObserver = Observer<List<UsedLibraryItem>> {
        if (it.isNotEmpty()) {
            loadingView.gone()
            libraryFragmentAdapter.submitList(it)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_libraries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.usedLibraryList.observe(viewLifecycleOwner, usedLibraryDataObserver)
        libraryFragmentAdapter = LibraryFragmentAdapter()
        usedLibraryRecyclerView.adapter = libraryFragmentAdapter

    }


}
