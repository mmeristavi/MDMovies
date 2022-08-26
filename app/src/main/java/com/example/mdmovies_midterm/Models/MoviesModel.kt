package com.example.mdmovies_midterm.Models

import com.google.gson.annotations.SerializedName

data class MoviesModel(
    val page: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    val results: MutableList<Result>?
){
    data class Result(
        val adult: Boolean?,
        @SerializedName("genre_ids")
        val genreIds: List<Int?>?,
        val id: Int?,
        @SerializedName("original_language")
        val originalLanguage: String?,
        val overview: String?,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("release_date")
        val releaseDate: String?,
        val title: String?,
        @SerializedName("vote_average")
        val voteAverage: Double?,
    )
}