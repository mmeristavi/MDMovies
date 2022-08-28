package com.example.mdmovies_midterm.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mdmovies_midterm.Extensions.loadImage
import com.example.mdmovies_midterm.Models.NowPlayingMoviesModel
import com.example.mdmovies_midterm.databinding.NowPlayingItemsListBinding

class NowPlayingMoviesAdapter()  :
    PagingDataAdapter<NowPlayingMoviesModel.Result, NowPlayingMoviesAdapter.NowPlayingViewHolder>(MovieItemCallback) {


    private object MovieItemCallback : DiffUtil.ItemCallback<NowPlayingMoviesModel.Result>() {

        override fun areItemsTheSame(oldItem: NowPlayingMoviesModel.Result, newItem: NowPlayingMoviesModel.Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NowPlayingMoviesModel.Result, newItem: NowPlayingMoviesModel.Result): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NowPlayingViewHolder(
        NowPlayingItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            tvTitle.text = currentItem?.title
            tvRating.text = currentItem?.voteAverage.toString()
            tvOverview.text = currentItem?.overview
            ivPoster.loadImage(currentItem?.posterPath)
        }
    }

    inner class NowPlayingViewHolder(val binding: NowPlayingItemsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

}