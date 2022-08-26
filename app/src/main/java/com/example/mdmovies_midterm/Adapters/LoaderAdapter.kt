package com.example.mdmovies_midterm.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mdmovies_midterm.databinding.LoaderBinding

class LoaderAdapter: LoadStateAdapter <LoaderAdapter.LoaderViewHolder> (){

    class LoaderViewHolder (val binding : LoaderBinding)
        : RecyclerView.ViewHolder (binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState):
            LoaderAdapter.LoaderViewHolder {
        return LoaderViewHolder(LoaderBinding.inflate
            (LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.binding.root.isVisible = loadState is LoadState.Loading
    }

}