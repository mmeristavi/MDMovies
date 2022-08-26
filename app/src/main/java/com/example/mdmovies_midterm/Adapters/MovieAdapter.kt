package com.example.mdmovies_midterm.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mdmovies_midterm.Extensions.loadImage
import com.example.mdmovies_midterm.Models.MoviesModel
import com.example.mdmovies_midterm.databinding.ItemListBinding

class MovieAdapter() :
    PagingDataAdapter<MoviesModel.Result, MovieAdapter.MovieViewHolder>(MovieItemCallback) {


    private object MovieItemCallback: DiffUtil.ItemCallback<MoviesModel.Result>() {

        override fun areItemsTheSame (oldItem: MoviesModel.Result, newItem: MoviesModel.Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame (oldItem: MoviesModel.Result, newItem: MoviesModel.Result): Boolean {
            return oldItem == newItem
        }


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder (
        ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false ))


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            tvTitle.text = currentItem?.title
            tvRating.text = currentItem?.voteAverage.toString()
            tvOverview.text = currentItem?.overview
            ivPoster.loadImage(currentItem?.posterPath)
        }
    }


    inner class MovieViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


}


