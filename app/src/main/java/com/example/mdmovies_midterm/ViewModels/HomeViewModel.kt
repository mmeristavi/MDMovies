package com.example.mdmovies_midterm.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mdmovies_midterm.PagingSource.NowPlayingDataSource
import com.example.mdmovies_midterm.PagingSource.TopRatedDataSource

class HomeViewModel : ViewModel() {


    val topRatedMoviePager = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = { TopRatedDataSource() }).flow.cachedIn(viewModelScope)




    val nowPlayingMoviesPager = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = { NowPlayingDataSource() }).flow.cachedIn(viewModelScope)


}

