package com.uhavecodingproblem.wordsrpg.component

import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.uhavecodingproblem.wordsrpg.data.WordData

/**
 * wordsrpg
 * Class: WordBindingAdapter
 * Created by pyg10.
 * Created On 2020-10-05.
 * Description:
 */
object WordBindingAdapter {


    @JvmStatic
    @BindingAdapter("backgroundImage", "error")
    fun loadBackgroundImage(constraintLayout: ConstraintLayout, url: String, error: Drawable) {
        Glide.with(constraintLayout.context)
            .load(url)
            .error(error)
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    constraintLayout.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }

    @JvmStatic
    @BindingAdapter("passCount")
    fun setCount(textView: TextView, item: MutableList<WordData>) {
        var count = 0
        for (i in item.indices) {
            if (item[i].isPassed)
                count++
        }
        val value = "$count / ${item.size}"
        textView.text = value
    }

}
