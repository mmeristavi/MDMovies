package com.example.mdmovies_midterm.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mdmovies_midterm.BaseFragment
import com.example.mdmovies_midterm.Models.MoviesModel
import com.example.mdmovies_midterm.MovieAdapter.MovieAdapter
import com.example.mdmovies_midterm.R
import com.example.mdmovies_midterm.Utils.Resource
import com.example.mdmovies_midterm.ViewModels.HomeViewModel
import com.example.mdmovies_midterm.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    private lateinit var adapter: MovieAdapter

    private val viewModel: HomeViewModel by viewModels()
    private var movieList = mutableListOf<MoviesModel.Result?>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logOutListener ()

        viewModel.getMovies(key = requireContext().getString(R.string.key))

        adapter = MovieAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        filter()


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
        }
    }


    private fun filter() {
        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            val filteredList = movieList.filter {
                it?.title?.lowercase()?.contains(text.toString().lowercase()) ?: false
            }
            adapter.setData(filteredList.toMutableList())
        }
    }


    private fun logOutListener () {
        binding.btnLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSignInFragment())
        }
    }


}


//            viewModel.getMoviesByName(key = "key", title: String)





