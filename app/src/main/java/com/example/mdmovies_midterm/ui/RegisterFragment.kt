package com.example.mdmovies_midterm.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mdmovies_midterm.BaseFragment
import com.example.mdmovies_midterm.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListeners()
    }


    private fun onClickListeners() {
        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val repeatPassword = binding.etRepeatPassword.text.toString()

            if (email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (password != repeatPassword) {
                Toast.makeText(
                    requireContext(),
                    "Passwords do not match",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { task ->
                    findNavController()
                        .navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())
                }
                .addOnFailureListener { task ->
                    Toast.makeText(requireContext(), "Password should contain at least 6 characters", Toast.LENGTH_SHORT)
                        .show()
                }
        }
        binding.btnBack.setOnClickListener {
            findNavController()
                .navigate(RegisterFragmentDirections.actionRegisterFragmentToWelcomeFragment())
        }

    }
}


