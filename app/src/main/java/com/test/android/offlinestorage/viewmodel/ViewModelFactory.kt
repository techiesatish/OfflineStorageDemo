package com.test.android.offlinestorage.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.android.offlinestorage.data.repository.UserRepository

class ViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(UserRepository::class.java)
            return constructor.newInstance(repository)
        } catch (e: Exception) {
            Log.e("ViewModelFactory" ,"create: "+e.message.toString() )
        }
        return super.create(modelClass)
    }
}