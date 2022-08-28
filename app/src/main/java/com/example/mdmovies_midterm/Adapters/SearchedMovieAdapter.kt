package com.example.mdmovies_midterm.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mdmovies_midterm.DiffUtil.DiffUtil
import com.example.mdmovies_midterm.Extensions.loadImage
import com.example.mdmovies_midterm.Models.TopRatedMoviesModel
import com.example.mdmovies_midterm.databinding.SearchedItemListBinding

class SearchedMovieAdapter()
    : RecyclerView.Adapter <SearchedMovieAdapter.SearchMovieViewHolder>() {


    var oldSearchedMovieList = mutableListOf<TopRatedMoviesModel.Result?>()

    inner class SearchMovieViewHolder (val binding: SearchedItemListBinding) :
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        return SearchMovieViewHolder(
            SearchedItemListBinding.
            inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = oldSearchedMovieList.size

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        val currentItem = oldSearchedMovieList[position]
        holder.binding.apply {
            tvTitle.text = currentItem?.title
            tvRating.text = currentItem?.voteAverage.toString()
            tvOverview.text = currentItem?.overview
            ivPoster.loadImage(currentItem?.posterPath)
        }
    }

    fun setData(newSearchedMovieList: MutableList<TopRatedMoviesModel.Result?>) {
        val diffUtil = DiffUtil(oldSearchedMovieList, newSearchedMovieList)
        val diffResults = androidx.recyclerview.widget.DiffUtil.calculateDiff(diffUtil)
        oldSearchedMovieList = newSearchedMovieList
        diffResults.dispatchUpdatesTo(this)
    }


}