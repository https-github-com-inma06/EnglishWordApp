package com.uhavecodingproblem.wordsrpg.component

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.uhavecodingproblem.wordsrpg.R

/**
 * wordsrpg
 * Class: BindingAdapter.
 * Created by leedonghun.
 * Created On 2020-09-25.
 * Description:
 *
 * 커스텀  패키지 binding adapter 모음
 *
 */
object CustomPackageBindingAdapter {

    //커스텀 패키지 썸네일 이미지  binding 용
    @BindingAdapter("image","error")
    @JvmStatic
    fun loadImage(imageView: ImageView, url:String, error: Drawable){
        Glide.with(imageView.context)
            .load(url)
            .error(error)
            .centerCrop()
            .into(imageView)
    }
}