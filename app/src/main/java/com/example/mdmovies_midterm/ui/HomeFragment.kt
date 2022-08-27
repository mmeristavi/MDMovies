package com.example.mdmovies_midterm.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mdmovies_midterm.Adapters.LoaderAdapter
import com.example.mdmovies_midterm.Adapters.MovieAdapter
import com.example.mdmovies_midterm.BaseFragment
import com.example.mdmovies_midterm.Models.MoviesModel
import com.example.mdmovies_midterm.R
import com.example.mdmovies_midterm.ViewModels.HomeViewModel
import com.example.mdmovies_midterm.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    private lateinit var adapter: MovieAdapter

    private val viewModel: HomeViewModel by viewModels()
//    private var movieList = mutableListOf<MoviesModel.Result?>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        logOutListener()
        buildAdapter()
//        filter()
        observers()


    }



//        viewModel.getMovies(key = requireContext().getString(R.string.key))
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.newState.collect {
//                    when (it) {
//                        is Resource.Success -> {
//
//                            movieList = it.data
//                            adapter.submitData(movieList.toMutableList())
//
//                            Log.d("Success", "${it.data.size}")
//                            binding.progressBar.isVisible = false
//                        }
//                        is Resource.Error -> {
//                            Log.d("Error", it.errorMessage)
//                            binding.progressBar.isVisible = false
//                        }
//                        is Resource.Loader -> {
//                            Log.d("Loader", "${it.isLoading}")
//                            binding.progressBar.isVisible = it.isLoading
//                        }
//                    }
//                }
//            }
//
//            viewLifecycleOwner.lifecycleScope.launch {
//                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    viewModel.newState.collect {
//                        adapter.submitData(it)
//                    }
//                }
//            }
//        }


    private fun buildAdapter() {
        adapter = MovieAdapter()
        binding.recyclerView.adapter = adapter
        adapter.withLoadStateFooter(footer = LoaderAdapter())
    }


    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.moviePager.collect {
                adapter.submitData(it)
                Log.d("incoming", "213")
            }
        }
    }


//    private fun filter() {
//        binding.etSearch.doOnTextChanged { text, _, _, _ ->
//            val filteredList = movieList.filter {
//                it?.title?.lowercase()?.contains(text.toString().lowercase()) ?: false
//            }
//        }
//    }



    private fun logOutListener() {
        binding.btnLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSignInFragment())
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item, menu)

    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.searchBar) {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchMovieFragment())
        }
        return true
    }

}








