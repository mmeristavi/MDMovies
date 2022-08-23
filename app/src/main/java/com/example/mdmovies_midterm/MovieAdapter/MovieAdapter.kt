package com.example.mdmovies_midterm.MovieAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mdmovies_midterm.Models.MoviesModel
import com.example.mdmovies_midterm.R
import com.example.mdmovies_midterm.Utils.Resource
import com.example.mdmovies_midterm.databinding.ItemListBinding

class MovieAdapter(private var movieList: Resource.Success<List<MoviesModel.Result?>>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemListBinding.inflate
                (
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = movieList.data[position]
        holder.binding.tvTitle.text = currentItem?.title
        holder.binding.tvRating.text = currentItem?.voteAverage.toString()
        holder.binding.tvOverview.text = currentItem?.overview
        Glide.with(holder.binding.ivPoster.context)
            .load("https://image.tmdb.org/t/p/w500/" +
                    currentItem?.posterPath)
            .error(R.drawable.homepage)
            .into(holder.binding.ivPoster)
    }


    override fun getItemCount(): Int = movieList.data.size

    inner class MovieViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

//        fun bind (model: Resource.Success<List<MoviesModel.Result?>>) {
//            binding.apply {
//                tvTitle.text = model.data[absoluteAdapterPosition]?.title
//                tvRating.text = model.data[absoluteAdapterPosition]?.voteAverage.toString()
//                tvOverview.text = model.data[absoluteAdapterPosition]?.overview
//                Glide.with(ivPoster.context)
//                    .load("https://image.tmdb.org/t/p/w500/" +
//                            model.data[absoluteAdapterPosition]?.posterPath)
//                    .error(R.drawable.homepage)
//                    .into(ivPoster)
//            }
//        }
    }

//    fun setData(searchedList: MutableList<MoviesModel.Result?>) {
//        val diffUtil = DiffUtil(movieList, searchedList)
//        val diffResults = androidx.recyclerview.widget.DiffUtil.calculateDiff(diffUtil)
//        movieList = searchedList
//        diffResults.dispatchUpdatesTo(this)
//    }



}