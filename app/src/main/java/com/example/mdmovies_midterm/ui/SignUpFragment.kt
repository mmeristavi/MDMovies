package com.example.mdmovies_midterm.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mdmovies_midterm.MainActivity
import com.example.mdmovies_midterm.R
import com.example.mdmovies_midterm.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {

    private var binding: FragmentSignUpBinding? = null

    companion object {
        fun newInstance() = SignUpFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RegisterActivityListeners()
    }

    private fun listeners() {

    }

    private fun RegisterActivityListeners() {
        binding!!.registerBut.setOnClickListener {
            val email = binding!!.emailLayout.text.toString()
            val pass = binding!!.password.text.toString()
            val repass = binding!!.repearPassword.text.toString()

            if (email.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (pass != repass) {
                Toast.makeText(
                    requireContext(),
                    "Passwords do not match",
                    Toast.LENGTH_SHORT
                ).show()
            }



            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener { task ->
                        findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
                    }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Wrong credentials", Toast.LENGTH_SHORT)
                        .show()
                }
                    }
                }
        }
