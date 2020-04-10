package com.android.coolwinks.users.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
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
            lastSelectedPosition = -1
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
        userMessageRecyclerView.addItemDecoration(
            DividerItemDecoration(
                appCompatActivity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onItemClick(currentSelectPosition: Any?) {
        //Implementing logic for avoiding notifyDataSetChanged
        //just notifying last and current selected item position

        currentSelectPosition as Int
        var lastSelectedUser: User? = null
        if (lastSelectedPosition >= 0) {
            lastSelectedUser = userMessageAdapter.getItemAtPosition(lastSelectedPosition)
        }
        val currentSelectedUser = userMessageAdapter.getItemAtPosition(currentSelectPosition)

        if (lastSelectedUser == null) {
            // first time/third time selection for same item
            currentSelectedUser.isExpended = !currentSelectedUser.isExpended
            userMessageAdapter.notifyItemChanged(currentSelectPosition)
            lastSelectedPosition = currentSelectPosition
        } else {
            if (lastSelectedUser == currentSelectedUser) {
                // same item selected 2 time successively
                currentSelectedUser.isExpended = !currentSelectedUser.isExpended
                userMessageAdapter.notifyItemChanged(currentSelectPosition)
                lastSelectedPosition = -1
            } else {
                //Two different item selected successively
                currentSelectedUser.isExpended = !currentSelectedUser.isExpended
                userMessageAdapter.notifyItemChanged(currentSelectPosition)
                lastSelectedUser.isExpended = !lastSelectedUser.isExpended
                userMessageAdapter.notifyItemChanged(lastSelectedPosition)
                lastSelectedPosition = currentSelectPosition

            }
        }

    }

}
