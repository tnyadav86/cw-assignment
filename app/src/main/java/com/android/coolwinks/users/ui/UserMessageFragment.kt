package com.android.coolwinks.users.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.android.coolwings.R
import com.android.coolwinks.BaseDaggerFragment
import com.android.coolwinks.RecyclerViewItemClickListener
import com.android.coolwinks.users.model.User
import com.android.coolwinks.users.viewmodel.UserMessageViewModel
import com.android.coolwinks.utils.gone
import kotlinx.android.synthetic.main.fragment_user_message.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class UserMessageFragment : BaseDaggerFragment(), RecyclerViewItemClickListener {
    private val args: UserMessageFragmentArgs by navArgs()
    var lastSelectedPosition = -1

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val viewModel: UserMessageViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactory).get(UserMessageViewModel::class.java)
    }
    private lateinit var userMessageAdapter: UserMessageAdapter
    private val userDataObserver = Observer<List<User>> {
        if (it.isNotEmpty()) {
            loadingView.gone()
            lastSelectedPosition=-1
            userMessageAdapter.submitList(it)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appCompatActivity.supportActionBar?.title = "User${args.userId}"
        viewModel.allUserMessageById.observe(viewLifecycleOwner, userDataObserver)
        viewModel.getAllUserMessageById(args.userId)
        userMessageAdapter = UserMessageAdapter(this)
        userMessageRecyclerView.adapter = userMessageAdapter
    }

    override fun onItemClick(data: Any?) {
        data as Int
        val list = userMessageAdapter.getItemList()
        list.get(data).isExpended = !list.get(data).isExpended
        userMessageAdapter.notifyItemChanged(data)
        if (data != lastSelectedPosition && lastSelectedPosition >= 0) {
            list.get(lastSelectedPosition).isExpended = !list.get(lastSelectedPosition).isExpended
            userMessageAdapter.notifyItemChanged(lastSelectedPosition)
        }
        lastSelectedPosition = data
    }

}
