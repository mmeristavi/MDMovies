package com.example.mdmovies_midterm.MovieAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mdmovies_midterm.DiffUtil.DiffUtil
import com.example.mdmovies_midterm.Models.MoviesModel
import com.example.mdmovies_midterm.R
import com.example.mdmovies_midterm.databinding.ItemListBinding

class MovieAdapter() :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList: MutableList<MoviesModel.Result?> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder (
        ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false ))


    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = movieList[position]?.title
            tvRating.text = movieList[position]?.voteAverage.toString()
            tvOverview.text = movieList[position]?.overview
            Glide.with(ivPoster.context)
                .load(
                    "https://image.tmdb.org/t/p/w500/" +
                            movieList[position]?.posterPath
                )
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.homepage)
                .into(ivPoster)
        }
    }


    inner class MovieViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    fun setData(searchedList: MutableList<MoviesModel.Result?>) {
        val diffUtil = DiffUtil(movieList, searchedList)
        val diffResults = androidx.recyclerview.widget.DiffUtil.calculateDiff(diffUtil)
        movieList = searchedList
        diffResults.dispatchUpdatesTo(this)
    }

}


