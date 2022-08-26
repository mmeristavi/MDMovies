package com.example.mdmovies_midterm.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mdmovies_midterm.Models.MoviesModel
import com.example.mdmovies_midterm.PagingSource.DataSource
import com.example.mdmovies_midterm.Utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _newState =
        MutableStateFlow<Resource<MutableList<MoviesModel.Result?>>>(Resource.Success(mutableListOf()))
    val newState = _newState.asStateFlow()

//    fun getMovies(key: String, page: Int) {
//        viewModelScope.launch {
//            val response = RetrofitClient.connectRetrofit().getItems(key, page)
//            if (response.isSuccessful) {
//                val body: MoviesModel? = response.body()
//                _newState.value = Resource.Success(response.body()?.results ?: mutableListOf())
//                Log.d("body", "$body")
//            } else {
//                val errorBody = response.errorBody()
//                _newState.value = Resource.Error(errorBody?.toString() ?: "")
//            }
//            _newState.value = Resource.Loader(false)
//        }
//    }

    val moviePager = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = { DataSource() }).flow.cachedIn(viewModelScope)

}

