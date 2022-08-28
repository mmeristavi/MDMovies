package com.example.mdmovies_midterm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mdmovies_midterm.databinding.FragmentResetPassBinding
import com.google.firebase.auth.FirebaseAuth

class ResetPass : Fragment() {
    private lateinit var binding: FragmentResetPassBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPassBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.resetPWD.setOnClickListener {
            val email = binding.emailTv.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Email is empty", Toast.LENGTH_SHORT).show()
            }
            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Email has been sent!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "Email didn't been sent!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }

    }
}