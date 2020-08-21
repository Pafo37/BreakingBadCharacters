package com.pafo37.breakingbadcharacters.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.squareup.picasso.Picasso

/*
@BindingAdapter("loadUrl")
fun loadImageFromUrl(imageView: ImageView, url: String?) {
    if (url != null) {
        Picasso.get().load(url).into(imageView)
    }
}*/

@BindingAdapter("loadUrl")
fun loadImageFromUrl(imageView: ImageView, url: String?) {
    if (url != null) {
        Glide.with(imageView).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)
    }
}
