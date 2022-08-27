package com.example.mdmovies_midterm.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mdmovies_midterm.Models.MoviesModel
import com.example.mdmovies_midterm.Network.RetrofitClient
import com.example.mdmovies_midterm.Utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchMovieViewModel : ViewModel() {

    private val _newState =
        MutableStateFlow<Resource<MutableList<MoviesModel.Result>>>(Resource.Success(mutableListOf()))
    val newState = _newState.asStateFlow()

    fun getSearchedMovies(key: String, query: String) {
        viewModelScope.launch {
            val response = RetrofitClient.connectRetrofit().searchMovies(key, query)
            if (response.isSuccessful) {
                val body: MoviesModel? = response.body()
                _newState.value = Resource.Success(response.body()?.results ?: mutableListOf())
                Log.d("body", "$body")
            } else {
                val errorBody = response.errorBody()
                _newState.value = Resource.Error(errorBody?.toString() ?: "")
            }
            _newState.value = Resource.Loader(false)
        }
    }




}