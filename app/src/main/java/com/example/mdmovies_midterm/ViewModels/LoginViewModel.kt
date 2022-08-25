package com.example.mdmovies_midterm.ViewModels

import androidx.lifecycle.ViewModel
import com.example.mdmovies_midterm.Data.DataStoreHandler
import com.example.mdmovies_midterm.Network.RetrofitClient
import com.example.mdmovies_midterm.Utils.Resource
import kotlinx.coroutines.flow.flow

class LoginViewModel : ViewModel() {

    fun signInResponse() = flow<Resource> {
        emit(Resource.Loader(isLoading = true))

        val response = RetrofitClient.connectRetrofit().getItems()

        if (response.isSuccessful) {
            emit(Resource.Success(response.body()))
        } else {
            emit(Resource.Error(response.errorBody()?.string() ?: ""))
        }
        emit(Resource.Loader(isLoading = false))
    }

    suspend fun save (key: String, value: String){
        DataStoreHandler.save(key, value)
    }


    fun getPreferences () = DataStoreHandler.getPreferences()


}
