package com.uhavecodingproblem.wordsrpg.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.item_guide_viewpager.view.*


@BindingAdapter("imageUrl")
fun imageUrl(view:ImageView,url:String) = Glide.with(view.context).load(url).into(view)

fun imageUrlReSize(view:ImageView,url:String,width:Int,height:Int){
    Glide.with(view.context).load(url).apply(RequestOptions().override(width,height)).centerCrop().into(view)


}