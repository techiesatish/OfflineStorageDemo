package com.test.android.phabletest.listeners

import com.test.android.phabletest.data.db.entity.UserDetails

interface ItemClickListner{
    fun itemClick(userDetails: UserDetails)

}