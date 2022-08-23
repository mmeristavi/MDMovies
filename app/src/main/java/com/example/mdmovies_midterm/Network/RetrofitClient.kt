package com.example.mdmovies_midterm.Network

import com.example.mdmovies_midterm.Models.MoviesModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

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
    @GET("movie/top_rated?api_key=86d68d7f4a0e3dccfa45e4e97b45f379")
    suspend fun getItems (): Response<MoviesModel>
}