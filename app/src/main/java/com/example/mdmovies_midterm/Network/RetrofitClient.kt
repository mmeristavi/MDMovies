package com.example.mdmovies_midterm.Network

import com.example.mdmovies_midterm.Models.MoviesModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private val retrofitBuilder: Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun connectRetrofit () = retrofitBuilder.create(Movies::class.java)

}

interface Movies {
    @GET("movie/top_rated")
    suspend fun getItems (@Query("api_key") key : String, @Query("page") page : Int): Response<MoviesModel>
//
//    @GET("genre/movie/list")
//    suspend fun getGenres (@Query("api_key") key : String): Response<>
}