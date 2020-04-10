package com.android.coolwinks.users.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.android.coolwinks.users.model.UserMessageRepository
import javax.inject.Inject

class UserMessageViewModel @Inject constructor(val repository: UserMessageRepository) :
    ViewModel() {

    private val userIdLiveData = MutableLiveData<Int>()
    val allUserMessageById = userIdLiveData.switchMap {
            repository.getUsersByUserId(it)


    }

   fun getAllUserMessageById(userId:Int){
       userIdLiveData.value=userId

   }
}