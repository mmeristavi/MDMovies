package com.example.mdmovies_midterm.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mdmovies_midterm.BaseFragment
import com.example.mdmovies_midterm.databinding.FragmentWelcomeBinding
import com.google.firebase.auth.FirebaseAuth

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseAuth.getInstance().currentUser?.let {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment())
        }

        listeners()


    }


    private fun listeners() {
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToSignInFragment())
        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment())
        }
    }
}
