package com.test.android.phabletest

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.android.phabletest.adapter.UserAdapter
import com.test.android.phabletest.data.db.UserDataBase
import com.test.android.phabletest.data.db.entity.UserDetails
import com.test.android.phabletest.data.repository.UserRepository
import com.test.android.phabletest.databinding.ActionDialogBinding
import com.test.android.phabletest.databinding.FragmentFirstBinding
import com.test.android.phabletest.listeners.ItemClickListner
import com.test.android.phabletest.viewmodel.SharedViewModel
import com.test.android.phabletest.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FirstFragment : Fragment(),ItemClickListner {

    private lateinit var binding: FragmentFirstBinding
    lateinit var navController: NavController
    lateinit var viewModel: SharedViewModel
    lateinit var adapter:UserAdapter
    lateinit var factory: ViewModelFactory
    lateinit var userRepository: UserRepository
    lateinit var userDataBase: UserDataBase


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentFirstBinding.inflate(inflater,container,false);
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userDataBase = UserDataBase(activity!!)
        userRepository = UserRepository(userDataBase)
        factory = ViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, factory)[SharedViewModel::class.java]
        navController= Navigation.findNavController(activity!!,R.id.my_nav_host_fragment)
        binding!!.fab.setOnClickListener {
            navController.navigate(R.id.action_firstFragment_to_secondFragment);
        }
        initRecyclerView()
        observeUsers()
    }

    //recycler initalisation
    private fun initRecyclerView() {
        binding.recyclerViewPhotos.layoutManager = LinearLayoutManager(activity)
        adapter = UserAdapter(arrayListOf(), activity!!,this)
        binding.recyclerViewPhotos.addItemDecoration(DividerItemDecoration(binding.recyclerViewPhotos.context, (binding.recyclerViewPhotos.layoutManager as LinearLayoutManager).orientation))
        binding.recyclerViewPhotos.adapter = adapter
    }


    //observing the users data
    private fun observeUsers() {

            CoroutineScope(Dispatchers.Main).launch {
                viewModel.getAllUser().observe(activity!!, {
                    adapter.apply {
                        addUsers(it)
                        notifyDataSetChanged()
                    }
                })
            }

    }


    override fun itemClick(userDetails: UserDetails) {
        showDialog(userDetails)
    }


    //showing dialog on click of recycler items
    private fun showDialog(userDetails: UserDetails) {
        val layoutInflater=LayoutInflater.from(activity)
        val binding=ActionDialogBinding.inflate(layoutInflater);
        val dialog=Dialog(activity!!)
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)


        binding.tvUpdate.setOnClickListener {

            val bundle = Bundle()
            bundle.putParcelable("UserDetails",userDetails)
            navController.navigate(R.id.action_firstFragment_to_secondFragment,bundle)
            dialog.dismiss()
        }
        binding.tvDelete.setOnClickListener {
            viewModel.deleteUserDetails(userDetails)
            dialog.dismiss()
            adapter.notifyDataSetChanged()
        }
        dialog.show()
    }


}

