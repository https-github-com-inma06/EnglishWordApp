package com.uhavecodingproblem.wordsrpg.component

import android.graphics.drawable.Drawable
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.ui.fragment.MyCustomPackageFragment

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


    //editext search action binding 용
    @BindingAdapter("searchAction")
    @JvmStatic
    fun keyboardSearchAction(editText: EditText,editorActionListener: TextView.OnEditorActionListener){
        editText.setOnEditorActionListener(editorActionListener)
    }

}