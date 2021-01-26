package com.uhavecodingproblem.wordsrpg.component.library.bindingadpater

import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.uhavecodingproblem.wordsrpg.data.StageInformation

/**
 * wordsrpg
 * Class: WordBindingAdapter
 * Created by pyg10.
 * Created On 2020-10-05.
 * Description:
 */
object WordBindingAdapter {

    @JvmStatic
    @BindingAdapter("progress")
    fun progress(progressBar: ProgressBar, item: MutableList<StageInformation>) {
        var count = 0
        for (i in item.indices) {
            if (item[i].stageStatus == 3)
                count++
        }
        progressBar.max = item.size
        progressBar.progress = count
    }

}
