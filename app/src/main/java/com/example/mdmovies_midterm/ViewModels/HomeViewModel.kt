package com.example.mdmovies_midterm.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mdmovies_midterm.PagingSource.DataSource

class HomeViewModel : ViewModel() {


    val moviePager = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = { DataSource() }).flow.cachedIn(viewModelScope)

}

