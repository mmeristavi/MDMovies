package com.example.mdmovies_midterm.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.mdmovies_midterm.Adapters.SearchedMovieAdapter
import com.example.mdmovies_midterm.BaseFragment
import com.example.mdmovies_midterm.Extensions.focus
import com.example.mdmovies_midterm.Models.MoviesModel
import com.example.mdmovies_midterm.R
import com.example.mdmovies_midterm.Utils.Resource
import com.example.mdmovies_midterm.ViewModels.SearchMovieViewModel
import com.example.mdmovies_midterm.databinding.FragmentSearchMovieBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SearchMovieFragment :
    BaseFragment<FragmentSearchMovieBinding>(FragmentSearchMovieBinding::inflate) {

    private lateinit var adapter: SearchedMovieAdapter

    private val viewModel: SearchMovieViewModel by viewModels()
    private var movieList = mutableListOf<MoviesModel.Result>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildAdapter()
        searchList()
        observers()
        logOutListener()
        focusOnSearch()


    }


    private fun searchList() {
        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.getSearchedMovies(
                key = requireContext().getString(R.string.key),
                query = text.toString()
            )
        }
    }



    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newState.collect {
                    when (it) {
                        is Resource.Success -> {
                            movieList = it.data
                            adapter.setData(movieList.toMutableList())

                            Log.d("Success", "${it.data.size}")
                            binding.progressBar.isVisible = false
                        }
                        is Resource.Error -> {
                            Log.d("Error", it.errorMessage)
                            binding.progressBar.isVisible = false
                        }
                        is Resource.Loader -> {
                            Log.d("Loader", "${it.isLoading}")
                            binding.progressBar.isVisible = it.isLoading
                        }
                    }
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.newState.collect {
                        adapter.setData(movieList.toMutableList())
                    }
                }
            }
        }
    }


    private fun buildAdapter() {
        adapter = SearchedMovieAdapter()
        binding.recyclerView.adapter = adapter
    }


    private fun logOutListener() {
        binding.btnLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController()
                .navigate(SearchMovieFragmentDirections.actionSearchMovieFragmentToSignInFragment())
        }
    }

    private fun focusOnSearch() {
        binding.etSearch.focus()
    }

}


