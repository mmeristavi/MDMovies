package com.example.mdmovies_midterm.DiffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.mdmovies_midterm.Models.TopRatedMoviesModel


class DiffUtil(
    val movieList: MutableList <TopRatedMoviesModel.Result?>,
    val searchedMovieList: MutableList<TopRatedMoviesModel.Result?>
) : DiffUtil.Callback() {
    //w
    override fun getOldListSize(): Int {
        return movieList.size
    }

    override fun getNewListSize(): Int {
        return searchedMovieList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return movieList[oldItemPosition]?.id == searchedMovieList[newItemPosition]?.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            movieList[oldItemPosition]?.id != searchedMovieList[newItemPosition]?.id -> {
                false
            }

            else -> true
        }
    }
}