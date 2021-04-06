package com.test.android.offlinestorage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.android.offlinestorage.data.db.entity.UserDetails
import com.test.android.offlinestorage.databinding.RecyclerItemBinding
import com.test.android.offlinestorage.listeners.ItemClickListner

class UserAdapter(private val list: ArrayList<UserDetails>,val mContext: Context,val itemClickListner: ItemClickListner) : ListAdapter<UserDetails, UserAdapter.UserViewHolder>(DiffUtilUser()) {
lateinit var binding: RecyclerItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        binding = RecyclerItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return UserViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = list.get(position)
        holder.bindItem(item,binding,itemClickListner)
    }

    override fun getItemCount(): Int {
        return list.size
    }




    class UserViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(
            userDetails: UserDetails,
            binding: RecyclerItemBinding,
            itemClickListner: ItemClickListner) {
            view.apply {
                binding.tvName.text = userDetails.name
                binding.tvEmailid.text = userDetails.email
            }
            itemView.setOnClickListener {
                itemClickListner.itemClick(userDetails)
            }

        }
    }

    private class DiffUtilUser : DiffUtil.ItemCallback<UserDetails>() {
        override fun areItemsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
            return newItem.email == oldItem.email
        }

        override fun areContentsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
            return newItem == oldItem
        }
    }


    fun addUsers(users: List<UserDetails>) {
       list.apply {
            clear()
            addAll(users)
        }
    }
}