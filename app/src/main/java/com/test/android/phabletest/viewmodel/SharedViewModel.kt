package com.test.android.phabletest.viewmodel

import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.test.android.phabletest.data.db.entity.UserDetails
import com.test.android.phabletest.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(val repository: UserRepository) :ViewModel() {
    fun saveOrUpdateData(userDetails: UserDetails) {
        if(TextUtils.isEmpty(userDetails.name)||TextUtils.isEmpty(userDetails.email)) return
        CoroutineScope(Dispatchers.IO).launch {
            if(userDetails.id!=null){ //if id exists that means user want to update data
                repository.updateUser(userDetails)
            }else{
                repository.saveUser(userDetails)
            }

        }
    }


    fun getAllUser(): LiveData<List<UserDetails>> {
        return  repository.getAllUsers()
    }

    fun deleteUserDetails( details: UserDetails) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteUser(details)
        }
    }

}