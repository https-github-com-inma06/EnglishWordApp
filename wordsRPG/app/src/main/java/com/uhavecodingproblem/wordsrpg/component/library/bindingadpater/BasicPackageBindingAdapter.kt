package com.uhavecodingproblem.wordsrpg.component.library.bindingadpater

import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.uhavecodingproblem.wordsrpg.data.model.ResponseBasicPackage
import java.lang.NumberFormatException

/**
 * wordsrpg
 * Class: WordBindingAdapter
 * Created by pyg10.
 * Created On 2020-10-05.
 * Description:
 */
object BasicPackageBindingAdapter {

    @JvmStatic
    @BindingAdapter("progress")
    fun progress(progressBar: ProgressBar, item: ResponseBasicPackage.BasicPackage) {
        try{
            var clearStage = 0
            item.stageList.forEach { if(it.stageTestStatus == "pass") clearStage++ }

            progressBar.max = item.stageList.size
            progressBar.progress = clearStage
        }catch (e: NumberFormatException){
            progressBar.max = 0
            progressBar.progress = 0
        }catch (e: NullPointerException){
            progressBar.max = 0
            progressBar.progress = 0
        }
    }

}
