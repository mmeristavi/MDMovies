package com.example.mdmovies_midterm.DiffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.mdmovies_midterm.Models.MoviesModel


class DiffUtil(
    val movieList: MutableList <MoviesModel.Result?>,
    val searchedMovieList: MutableList<MoviesModel.Result?>
) : DiffUtil.Callback() {
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