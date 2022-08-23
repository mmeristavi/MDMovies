package com.example.mdmovies_midterm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mdmovies_midterm.R
import com.example.mdmovies_midterm.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private var binding: FragmentWelcomeBinding? = null

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
    }


    private fun listeners() {
        binding!!.signInBut.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToSignInFragment())
        }
        binding!!.signUpBut.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToSignUpFragment())
        }
    }
}