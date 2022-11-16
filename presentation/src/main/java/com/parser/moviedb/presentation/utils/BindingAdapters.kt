package com.parser.moviedb.presentation.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object BindingAdapters {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String?) {
        if (!(url == null || url.isEmpty())) {
            Glide.with(imageView.context).load(url.formatUrl()).apply(
                RequestOptions().fitCenter().diskCacheStrategy(
                    DiskCacheStrategy.AUTOMATIC
                )
            ).into(imageView)
        }
    }
}