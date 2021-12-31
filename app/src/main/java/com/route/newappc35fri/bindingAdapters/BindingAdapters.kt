package com.route.newappc35fri.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:imageUrl")
fun setImageByUrl(imageView: ImageView, urlToImage: String) {
    Glide.with(imageView)
        .load(urlToImage)
        .into(imageView);

}