package com.test.android.phabletest.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize



@Entity(tableName = "user_table")
@Parcelize
data class UserDetails(
        @PrimaryKey(autoGenerate = true)
        val id: Int?,
        val email: String,
        val name: String): Parcelable