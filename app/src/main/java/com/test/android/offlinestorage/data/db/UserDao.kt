package com.test.android.offlinestorage.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.test.android.offlinestorage.data.db.entity.UserDetails

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
   fun getAllUsers(): LiveData<List<UserDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertUser(users: UserDetails)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(users: UserDetails)

    @Delete
    suspend fun deleteUser(note: UserDetails)




}