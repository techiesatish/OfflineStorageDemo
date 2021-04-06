package com.test.android.offlinestorage.data.repository

import androidx.lifecycle.LiveData
import com.test.android.offlinestorage.data.db.UserDataBase
import com.test.android.offlinestorage.data.db.entity.UserDetails

class UserRepository(val appDatabase: UserDataBase?) {

    fun getAllUsers(): LiveData<List<UserDetails>> {
        return appDatabase!!.userDao()!!.getAllUsers() //fetch all users
    }
    suspend fun saveUser(userDetails: UserDetails) {
         appDatabase!!.userDao()!!.insertUser(userDetails) //insert user
    }
    suspend  fun updateUser(userDetails: UserDetails) {
        appDatabase!!.userDao()!!.updateUser(userDetails) //update user
    }
    suspend  fun deleteUser(userDetails: UserDetails) {
        appDatabase!!.userDao()!!.deleteUser(userDetails) //delete user
    }


}