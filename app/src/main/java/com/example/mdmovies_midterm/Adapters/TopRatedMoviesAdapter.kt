package com.example.mdmovies_midterm.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mdmovies_midterm.Extensions.loadImage
import com.example.mdmovies_midterm.Models.TopRatedMoviesModel
import com.example.mdmovies_midterm.databinding.TopRatedMoviesListBinding

class TopRatedMoviesAdapter() :
    PagingDataAdapter<TopRatedMoviesModel.Result, TopRatedMoviesAdapter.TopRatedViewHolder>(MovieItemCallback) {


    private object MovieItemCallback: DiffUtil.ItemCallback<TopRatedMoviesModel.Result>() {

        override fun areItemsTheSame (oldItem: TopRatedMoviesModel.Result, newItem: TopRatedMoviesModel.Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame (oldItem: TopRatedMoviesModel.Result, newItem: TopRatedMoviesModel.Result): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TopRatedViewHolder (
        TopRatedMoviesListBinding.inflate(LayoutInflater.from(parent.context), parent, false ))

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            tvTitle.text = currentItem?.title
            tvRating.text = currentItem?.voteAverage.toString()
            tvOverview.text = currentItem?.overview
            ivPoster.loadImage(currentItem?.posterPath)
        }
    }

    inner class TopRatedViewHolder(val binding: TopRatedMoviesListBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


}


