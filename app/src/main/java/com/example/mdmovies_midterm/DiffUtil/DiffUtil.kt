package com.example.mdmovies_midterm.DiffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.mdmovies_midterm.Models.MoviesModel


class DiffUtil(
    private val movieList: MutableList <MoviesModel.Result?>,
    private val searchedList: MutableList<MoviesModel.Result?>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return movieList.size
    }

    override fun getNewListSize(): Int {
        return searchedList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return movieList[oldItemPosition]?.title == searchedList[newItemPosition]?.title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            movieList[oldItemPosition]?.title != searchedList[newItemPosition]?.title -> {
                false
            }
            movieList[oldItemPosition]?.voteAverage != searchedList[newItemPosition]?.voteAverage -> {
                false
            }
            movieList[oldItemPosition]?.overview != searchedList[newItemPosition]?.overview -> {
                false
            }
            movieList[oldItemPosition]?.posterPath != searchedList[newItemPosition]?.posterPath -> {
                false
            }
            else -> true
        }
    }
}