package com.test.android.offlinestorage.listeners

import com.test.android.offlinestorage.data.db.entity.UserDetails

interface ItemClickListner{
    fun itemClick(userDetails: UserDetails)

}