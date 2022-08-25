package com.example.mdmovies_midterm.Utils

sealed class Resource {
    data class Success<T>(val data: T) : Resource()
    data class Error (val errorMessage: String) : Resource()
    data class Loader (val isLoading: Boolean) : Resource()
}
