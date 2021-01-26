package com.uhavecodingproblem.wordsrpg.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.uhavecodingproblem.wordsrpg.R

@BindingAdapter("loadImage")
fun ImageView.loadImage(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.ic_launcher_foreground))
        .into(this)
}


@BindingAdapter("setCircleImage")
fun setCircleImage(iv: ImageView,uri:String){
    iv.clipToOutline = true
    Glide.with(iv.context).load(uri).apply(RequestOptions.circleCropTransform()).into(iv)
}