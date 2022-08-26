package com.example.mdmovies_midterm.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mdmovies_midterm.BaseFragment
import com.example.mdmovies_midterm.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        onClickListeners()
    }

    private fun onClickListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()
            ) {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(LoginFragmentDirections.actionSignInFragmentToHomeFragment())
                    } else {
                        Toast.makeText(requireContext(), "Wrong Credentials", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionSignInFragmentToWelcomeFragment())
        }
    }
}