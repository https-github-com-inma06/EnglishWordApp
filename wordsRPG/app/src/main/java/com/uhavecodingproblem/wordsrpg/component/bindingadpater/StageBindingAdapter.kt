package com.uhavecodingproblem.wordsrpg.component.bindingadpater

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * wordsrpg
 * Class: StageBindingAdapter
 * Created by pyg10.
 * Created On 2020-10-28.
 * Description:
 */
object StageBindingAdapter {

    @JvmStatic
    @BindingAdapter("adapterPosition", "currentStagePosition", "isReverse")
    fun isVisible(view: View, adapterPosition: Int, currentStagePosition: Int, isReverse: Boolean){
        if(adapterPosition == currentStagePosition) {
            if (!isReverse)
                view.visibility = View.GONE
            else
                view.visibility = View.VISIBLE
        }
        else {
            if (!isReverse)
                view.visibility = View.VISIBLE
            else
                view.visibility = View.GONE
        }
    }

}