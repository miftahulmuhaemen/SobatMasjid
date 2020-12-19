package com.nazar.sobatmasjid.utils.extensions

import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nazar.sobatmasjid.R

fun ImageView.setImageFromUrl(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl)
        .apply(
            RequestOptions
                .placeholderOf(R.drawable.logo_sobat_masjid)
                .error(R.drawable.logo_sobat_masjid)
        )
        .into(this)
}

fun ImageView.setImageFromUri(imageUri: Uri) {
    Glide.with(this.context)
        .load(imageUri)
        .apply(
            RequestOptions
                .placeholderOf(R.drawable.logo_sobat_masjid)
                .error(R.drawable.logo_sobat_masjid)
        )
        .into(this)
}

fun ImageView.setImageFromUrlWithProgressBar(url: String, progress: View) {
//    GlideApp.with(this.context).load(url).thumbnail(0.1f).listener(PhotosRequestListener(progress))
//        .into(this)
}