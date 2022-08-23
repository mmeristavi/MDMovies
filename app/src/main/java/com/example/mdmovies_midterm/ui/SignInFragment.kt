package com.example.mdmovies_midterm.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mdmovies_midterm.MainActivity
import com.example.mdmovies_midterm.R
import com.example.mdmovies_midterm.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    private var binding: FragmentSignInBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RegisterActivityListeners()
    }

    private fun RegisterActivityListeners() {
        binding!!.signInBut.setOnClickListener {
            val email = binding!!.eMail.text.toString()
            val pass = binding!!.password.text.toString()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(requireContext(), "ველების შევსება სავალდებულოა", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }


        }
    }
}