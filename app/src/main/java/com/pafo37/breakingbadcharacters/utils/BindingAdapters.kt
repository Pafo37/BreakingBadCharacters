package com.pafo37.breakingbadcharacters.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("visibility")
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("loadUrl")
fun loadImageFromUrl(imageView: ImageView, url: String?) {
    if (url != null) {
        Glide.with(imageView).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)
    }
}
