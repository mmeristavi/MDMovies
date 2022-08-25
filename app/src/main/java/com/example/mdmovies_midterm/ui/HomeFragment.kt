package com.example.mdmovies_midterm.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mdmovies_midterm.MovieAdapter.MovieAdapter
import com.example.mdmovies_midterm.Utils.Resource
import com.example.mdmovies_midterm.ViewModels.HomeViewModel
import com.example.mdmovies_midterm.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {


    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: MovieAdapter
    private val viewModel: HomeViewModel by viewModels()

//    private var searchedList = mutableListOf<MoviesModel.Result?>()
//    private var movieList = mutableListOf<MoviesModel.Result?>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovies()


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newState.collect {
                    when (it) {
                        is Resource.Success -> {
                            adapter = MovieAdapter(it)
                            binding?.recyclerView?.adapter = adapter
                            binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
//                            filteredMovies()
                            Log.d("Success", "${it.data.size}")
                            binding?.progressBar?.isVisible = false
                        }
                        is Resource.Error -> {
                            Log.d("Error", it.errorMessage)
                            binding?.progressBar?.isVisible = false
                        }
                        is Resource.Loader -> {
                            Log.d("Loader", "${it.isLoading}")
                            binding?.progressBar?.isVisible = it.isLoading
                        }
                    }
                }
            }
        }
    }


//    private fun filteredMovies() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.newState.collect {
//                    binding?.search?.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
//                        override fun onQueryTextSubmit(query: String?): Boolean {
//                            return true
//                        }
//
//                        override fun onQueryTextChange(newText: String?): Boolean {
//                            searchedList = if (newText!!.isNotEmpty()) {
//                                val search = newText.lowercase(Locale.getDefault())
//                                movieList.filter {
//                                    it?.title?.lowercase(Locale.getDefault())?.contains(search)!!
//                                }.toMutableList()
//                            } else {
//                                movieList
//                            }
//                            adapter.setData(searchedList)
//                            return true
//                        }
//                    }
//                    )
//
//                }
//            }
//        }
//    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}