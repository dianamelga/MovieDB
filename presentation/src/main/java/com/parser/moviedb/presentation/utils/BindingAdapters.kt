package com.parser.moviedb.presentation.utils

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.parser.moviedb.presentation.R

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

    @BindingAdapter("present")
    @JvmStatic
    fun present(view: View, present: Boolean) {
        view.visibility = if (present) View.VISIBLE else View.GONE
    }

    @BindingAdapter("yellow")
    @JvmStatic
    fun setYellowBg(view: View, yellow: Boolean) {
        if (yellow) {
            view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.yellow))
        } else {
            view.setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.white))
        }
    }
}