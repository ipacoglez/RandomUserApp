package com.example.randomuserapp.common

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.randomuserapp.R

fun ImageView.loadImage(url: String) {

    val requestOptions: RequestOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.drawable.loading_sppiner)
        .error(R.drawable.ic_profile_picture)

    Glide
        .with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.loading_sppiner)
        .apply(requestOptions)
        .error(R.drawable.ic_profile_picture)
        .into(this)
}