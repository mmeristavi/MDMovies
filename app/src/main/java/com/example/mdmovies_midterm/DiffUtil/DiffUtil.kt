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
        return movieList[oldItemPosition]?.title == searchedMovieList[newItemPosition]?.title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            movieList[oldItemPosition]?.title != searchedMovieList[newItemPosition]?.title -> {
                false
            }
            movieList[oldItemPosition]?.voteAverage != searchedMovieList[newItemPosition]?.voteAverage -> {
                false
            }
            movieList[oldItemPosition]?.overview != searchedMovieList[newItemPosition]?.overview -> {
                false
            }
            movieList[oldItemPosition]?.posterPath != searchedMovieList[newItemPosition]?.posterPath -> {
                false
            }
            else -> true
        }
    }
}