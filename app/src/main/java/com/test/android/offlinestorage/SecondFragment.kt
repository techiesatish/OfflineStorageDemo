package com.test.android.offlinestorage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.test.android.offlinestorage.data.db.UserDataBase
import com.test.android.offlinestorage.data.db.entity.UserDetails
import com.test.android.offlinestorage.data.repository.UserRepository
import com.test.android.offlinestorage.databinding.FragmentSecondBinding
import com.test.android.offlinestorage.viewmodel.SharedViewModel
import com.test.android.offlinestorage.viewmodel.ViewModelFactory


class SecondFragment : Fragment() {
lateinit var viewModel: SharedViewModel
lateinit var binding: FragmentSecondBinding
    lateinit var factory: ViewModelFactory
    lateinit var userRepository: UserRepository
    lateinit var userDataBase: UserDataBase
    lateinit var navController: NavController
    var updateFlag=false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding= FragmentSecondBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userDataBase = UserDataBase(activity!!)
        userRepository = UserRepository(userDataBase)
        factory = ViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, factory)[SharedViewModel::class.java]
        navController=Navigation.findNavController(activity!!,R.id.my_nav_host_fragment)



        val args=arguments?.getParcelable<UserDetails>("UserDetails")
        if(args!=null){
            binding.etUserName.setText(args.name)
            binding.etUserEmail.setText(args.email)
            updateFlag=true
        }

        binding.btnSave.setOnClickListener {
            val userid = if (args != null) args?.id else null
            val userDetails=UserDetails(id=userid,email= binding.etUserEmail.text.toString().trim(),name=binding.etUserName.text.toString().trim())
            viewModel.saveOrUpdateData(userDetails).also {
                navController.navigate(R.id.action_secondFragment_to_firstFragment) //navigate to first fragment
            }

        }
    }

}