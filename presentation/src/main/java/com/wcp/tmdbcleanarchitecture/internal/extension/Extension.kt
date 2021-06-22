package com.wcp.tmdbcleanarchitecture.internal.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.wcp.tmdbcleanarchitecture.R

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View {
    return LayoutInflater.from(context)
        .inflate(layoutId, this, false)
}


fun ImageView.loadFromUrl(url: String?) =
    if(url!= null){
        Glide.with(this.context.applicationContext)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }else{
        Glide.with(this.context.applicationContext)
            .load(R.drawable.ic_launcher_background)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }