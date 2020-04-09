package com.android.coolwinks.users.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.coolwings.R
import com.android.coolwinks.BaseDaggerFragment
import com.android.coolwinks.RecyclerViewItemClickListener
import com.android.coolwinks.users.viewmodel.UserViewModel
import com.android.coolwinks.utils.TaskStatusResult
import com.android.coolwinks.utils.gone
import com.android.coolwinks.utils.toast
import com.android.coolwinks.utils.visible
import kotlinx.android.synthetic.main.fragment_user.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class UserFragment : BaseDaggerFragment(), RecyclerViewItemClickListener {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactory).get(UserViewModel::class.java)
    }

    private lateinit var userAdapter: UserAdapter
    private val userDataObserver = Observer<List<Int>> {
        if (it.isNotEmpty()){
            loadingView.gone()
            userAdapter.submitList(it)
        }

    }
    private val taskStatusDataObserver = Observer<TaskStatusResult> {
        when (it) {
            is TaskStatusResult.Loading -> {
                loadingView.visible()
            }

            is TaskStatusResult.Success -> {
                loadingView.visible()
            }

            is TaskStatusResult.Error -> {
                loadingView.gone()
                if(userAdapter.itemCount==0)
                    it.errorMessage?.let { it1 -> toast(it1) }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userIdListLivedata.observe(viewLifecycleOwner, userDataObserver)
        viewModel.taskStatusLiveData.observe(viewLifecycleOwner, taskStatusDataObserver)
        viewModel.getUsers()
        userAdapter = UserAdapter(this)
        userRecyclerView.adapter = userAdapter
    }

    override fun onItemClick(data: Any?) {

    }

}
