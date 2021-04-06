package com.test.android.offlinestorage.viewmodel

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.test.android.offlinestorage.data.db.entity.UserDetails
import com.test.android.offlinestorage.data.repository.UserRepository
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