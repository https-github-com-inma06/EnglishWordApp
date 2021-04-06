package com.uhavecodingproblem.wordsrpg.component.library.bindingadpater

import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.mockdata.StageInformation

/**
 * wordsrpg
 * Class: StageBindingAdapter
 * Created by pyg10.
 * Created On 2020-10-28.
 * Description:
 */
object StageBindingAdapter {

    @JvmStatic
    @BindingAdapter("stageBackGroundColorLockCheck", "stageBackGroundColorTestCheck")
    fun stageBackground(viewGroup: ViewGroup, isLock: String, isTestPass: String){

        if (isLock == "lock") viewGroup.setBackgroundColor(ContextCompat.getColor(viewGroup.context, R.color.colorGray))
        else{
            if (isTestPass == "pass")
                viewGroup.setBackgroundColor(ContextCompat.getColor(viewGroup.context, R.color.colorLightBlue2))
            else
                viewGroup.setBackgroundColor(ContextCompat.getColor(viewGroup.context, R.color.basic_dialog_text_color))

        }

    }

}