package com.example.mdmovies_midterm.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mdmovies_midterm.Models.MoviesModel
import com.example.mdmovies_midterm.Network.RetrofitClient
import com.example.mdmovies_midterm.Utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _newState =
        MutableStateFlow<Resource<List<MoviesModel.Result?>>>(Resource.Success(emptyList()))

    val newState = _newState.asStateFlow()

    fun getMovies() {
        viewModelScope.launch {
            val response = RetrofitClient.connectRetrofit().getItems()
            if (response.isSuccessful) {
                val body: MoviesModel? = response.body()
                _newState.value = Resource.Success(response.body()?.results ?: emptyList())
                Log.d("body", "$body")
            } else {
                val errorBody = response.errorBody()
                _newState.value = Resource.Error(errorBody?.toString() ?: "")
            }
            _newState.value = Resource.Loader(false)
        }
    }
}