package com.pafo37.breakingbadcharacters.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("loadUrl")
fun loadImageFromUrl(imageView: ImageView, url: String?) {
    if (url != null) {
        Picasso.get().load(url).into(imageView)
    }
}