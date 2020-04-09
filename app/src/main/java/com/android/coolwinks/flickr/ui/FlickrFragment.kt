package com.android.coolwinks.flickr.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import com.android.coolwings.R
import com.android.coolwinks.BaseFragment
import com.android.coolwinks.RecyclerViewItemClickListener
import com.android.coolwinks.database.AppDatabase
import com.android.coolwinks.flickr.model.*
import com.android.coolwinks.flickr.viewmodel.FlickrViewModel
import com.android.coolwinks.network.ApiClient
import com.android.coolwinks.network.ApiService
import com.android.coolwinks.utils.TaskStatusResult
import com.android.coolwinks.utils.gone
import com.android.coolwinks.utils.toast
import com.android.coolwinks.utils.visible
import kotlinx.android.synthetic.main.fragment_flicker.*

/**
 * A simple [Fragment] subclass.
 */
class FlickrFragment : BaseFragment(), RecyclerViewItemClickListener {
    private lateinit var viewModel: FlickrViewModel
    private lateinit var flickrAdapter: FlickrAdapter

    private val flickrDataObserver = Observer<PagedList<Photo>> {
        if (it.isNotEmpty()) {
            loadingView.gone()
            flickrAdapter.submitList(it)
        }
    }
    private val taskStatusDataObserver = Observer<TaskStatusResult> {
        when (it) {
            is TaskStatusResult.Loading -> {
                loadingView.visible()
            }

            is TaskStatusResult.Success -> {
                loadingView.gone()

            }

            is TaskStatusResult.Error -> {
                it.errorMessage?.let { it1 -> toast(it1) }
                loadingView.gone()
            }
        }

    }
    private val refreshTaskStatusDataObserver = Observer<TaskStatusResult> {
        when (it) {

            is TaskStatusResult.Success -> {
               swipeRefreshLayout.isRefreshing=false

            }

            is TaskStatusResult.Error -> {
                swipeRefreshLayout.isRefreshing=false
            }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService = ApiClient.client.create(ApiService::class.java)
        val flickrRemoteDataSource = FlickrRemoteDataSource(apiService)
        val photoDao = AppDatabase.getInstance(appCompatActivity).photoDao()
        val flickerLocalDataSource = FlickerLocalDataSource(photoDao)
        val flickrRepository = FlickrRepository(flickrRemoteDataSource, flickerLocalDataSource)

        viewModel = ViewModelProvider(
            this,
            FlickrViewModelFactory(flickrRepository)
        ).get(FlickrViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flicker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.photoListLivedata.observe(viewLifecycleOwner, flickrDataObserver)
        viewModel.taskStatusLiveData.observe(viewLifecycleOwner, taskStatusDataObserver)
        viewModel.getPhotos()
        flickrAdapter = FlickrAdapter(this)
        flickrRecyclerView.adapter = flickrAdapter
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshPhotos().observe(viewLifecycleOwner,refreshTaskStatusDataObserver)
        }

    }

    override fun onItemClick(data: Any?) {
        data as Photo
        val action = FlickrFragmentDirections.actionFlickerFragmentToFlickrImagrFragment(data.url_z)
        findNavController().navigate(action)
    }
}
