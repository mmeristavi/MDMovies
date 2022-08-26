package com.example.mdmovies_midterm.Extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.mdmovies_midterm.R

fun ImageView.loadImage (url: String?) {
    Glide.with(context)
        .load("https://image.tmdb.org/t/p/w500/" + url)
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)
        .into(this)
}