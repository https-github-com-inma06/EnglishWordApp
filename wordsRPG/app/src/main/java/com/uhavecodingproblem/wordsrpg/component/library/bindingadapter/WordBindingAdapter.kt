package com.uhavecodingproblem.wordsrpg.component.library.bindingadapter

import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.uhavecodingproblem.wordsrpg.data.Stage
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

    @JvmStatic
    @BindingAdapter("progress")
    fun progress(progressBar: ProgressBar, item: MutableList<Stage>) {
        var count = 0
        for (i in item.indices) {
            if (item[i].stageStatus == 3)
                count++
        }
        progressBar.max = item.size
        progressBar.progress = count
    }

}
