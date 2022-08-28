package com.example.mdmovies_midterm.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.example.mdmovies_midterm.Adapters.LoaderAdapter
import com.example.mdmovies_midterm.Adapters.NowPlayingMoviesAdapter
import com.example.mdmovies_midterm.Adapters.TopRatedMoviesAdapter
import com.example.mdmovies_midterm.BaseFragment
import com.example.mdmovies_midterm.Models.NowPlayingMoviesModel
import com.example.mdmovies_midterm.R
import com.example.mdmovies_midterm.ViewModels.HomeViewModel
import com.example.mdmovies_midterm.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    private lateinit var topRatedMoviesAdapter: TopRatedMoviesAdapter
    private lateinit var nowPlayingMoviesAdapter: NowPlayingMoviesAdapter

    private val viewModel: HomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        logOutListener()
        spinnerSetUp()

    }

    private fun spinnerSetUp(){
        val categories = arrayOf("Top Rated", "Now Playing")
        val spinner = binding.Spinner
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, categories)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0) { topRatedMoviesObservers() }
                if (position == 1) { nowPlayingMoviesObservers() }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                topRatedMoviesObservers()
            }
        }
    }


    private fun topRatedMoviesObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.topRatedMoviePager.collect {
                buildTopRatedMoviesAdapter()
                topRatedMoviesAdapter.submitData(it)
            }
        }
    }


    private fun nowPlayingMoviesObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.nowPlayingMoviesPager.collect {
                buildNowPlayingMoviesAdapter()
                nowPlayingMoviesAdapter.submitData(it)
            }
        }
    }


    private fun buildTopRatedMoviesAdapter() {
        topRatedMoviesAdapter = TopRatedMoviesAdapter()
        binding.recyclerView.adapter = topRatedMoviesAdapter
        topRatedMoviesAdapter.withLoadStateFooter(footer = LoaderAdapter())
    }


    private fun buildNowPlayingMoviesAdapter() {
        nowPlayingMoviesAdapter = NowPlayingMoviesAdapter()
        binding.recyclerView.adapter = nowPlayingMoviesAdapter
        nowPlayingMoviesAdapter.withLoadStateFooter(footer = LoaderAdapter())
    }


    private fun logOutListener() {
        binding.btnLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToSignInFragment())
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.searchBar) {
            findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToSearchMovieFragment())
        }
        return true
    }


}








