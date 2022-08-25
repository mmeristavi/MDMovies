package com.example.mdmovies_midterm.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mdmovies_midterm.BaseFragment
import com.example.mdmovies_midterm.Data.PreferenceKeys
import com.example.mdmovies_midterm.Utils.Resource
import com.example.mdmovies_midterm.ViewModels.LoginViewModel
import com.example.mdmovies_midterm.databinding.ActivityMainBinding.inflate
import com.example.mdmovies_midterm.databinding.FragmentLoginBinding
import com.example.mdmovies_midterm.databinding.FragmentSignInBinding
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        loginActivityListeners()
    }

    private fun loginActivityListeners() {
        binding.signInBut.setOnClickListener {
            val email = binding.eMail.text.toString()
            val pass = binding.password.text.toString()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(context, "ველების შევსება სავალდებულოა", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }else {
                observers()
            }
        }
    }


    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.signInResponse().collect {
                when (it) {
                    is Resource.Success<*> -> {
                        Log.d("LoginResponse", "${it.data}")
                        if (binding.cbRemember.isChecked) {
                            viewModel.save(PreferenceKeys.email, binding.etUsername.text.toString())
                        } else {
                            findNavController()
                                .navigate(
                                    LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                                        binding.etUsername.text.toString()
                                    )
                                )
                        }
                    }
                    is Resource.Error -> {
                        Log.d("ErrorResponse", it.errorMessage)
                        Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loader -> {
                        binding.ProgressBar.isVisible = it.isLoading
                    }
                }
            }
        }
    }


    private fun fragmentResultsListener() {
        setFragmentResultListener(FragmentResponseAPI.AUTH_KEY) { _, bundle ->
            binding.etUsername.setText(
                bundle.getString(
                    FragmentResponseAPI.EMAIL,
                    "There is no value"
                )
            )
            binding.etPassword.setText(
                bundle.getString(
                    FragmentResponseAPI.PASSWORD,
                    "There is no value"
                )
            )
        }
    }


    private fun checkSessions() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPreferences().collect {
                if (it.contains(stringPreferencesKey(PreferenceKeys.email))) {
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                            email = it[stringPreferencesKey(PreferenceKeys.email)] ?: ""
                        )
                    )
                }
            }
        }
    }


    private fun getLoginInfo() = LoginModel(
        binding.etUsername.text.toString(),
        binding.etPassword.text.toString()
    )





}