package com.uhavecodingproblem.wordsrpg.component.bindingadpater

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.uhavecodingproblem.wordsrpg.data.StageInformation

/**
 * wordsrpg
 * Class: StageBindingAdapter
 * Created by pyg10.
 * Created On 2020-10-28.
 * Description:
 */
object StageBindingAdapter {

    @JvmStatic
    @BindingAdapter("score")
    fun getScore(textView: TextView, stageInformationData: StageInformation) {
        var score = 0
        for (i in stageInformationData.wordList.indices) {
            if (stageInformationData.wordList[i].isTestPassed)
                score++
        }
        val result: String?
        result = if (score == 0)
            "에러발생"
        else
            "획득점수 : ${score * 10} / 100"
        textView.text = result
    }

}