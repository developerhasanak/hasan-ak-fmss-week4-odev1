package com.hasan.retrofitapp.util


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.hasan.retrofitapp.R

/**
 * This method adds an extension to ImageView.
 *
 * This extension is for using the Glide module.
 *
 * @param url is a value for ImageView.downloadFromUrl.
 */
fun ImageView.downloadFromUrl(url: String?) {

    val options = RequestOptions()
        .centerCrop()
        .placeholder(R.drawable.progress_animation)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .dontAnimate()
        .dontTransform();
    GlideApp.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .centerCrop()
        .into(this)
}

@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView, url: String?) {
    view.downloadFromUrl(url)
}

object Constants {

    const val BASE_URL = "https://mars.udacity.com/"
}