package com.example.mdmovies_midterm.Network

import com.example.mdmovies_midterm.Models.NowPlayingMoviesModel
import com.example.mdmovies_midterm.Models.TopRatedMoviesModel
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
    suspend fun getTopRatedMovies (@Query("api_key") key : String, @Query("page") page : Int):
            Response<TopRatedMoviesModel>

    @GET("search/movie")
    suspend fun searchMovies (@Query("api_key") key: String, @Query("query") query: String):
            Response<TopRatedMoviesModel>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies (@Query("api_key") key : String, @Query("page") page : Int):
            Response<NowPlayingMoviesModel>

}