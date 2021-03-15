package com.uhavecodingproblem.wordsrpg.component.library.bindingadpater

import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.uhavecodingproblem.wordsrpg.data.mockdata.StageInformation
import com.uhavecodingproblem.wordsrpg.data.model.PackageWithStage
import java.lang.NumberFormatException

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
    fun progress(progressBar: ProgressBar, item: PackageWithStage?) {
        try{
            item?.let {
                progressBar.max = item.total_stage.toInt()
                progressBar.progress = item.clear_stage.toInt()
            }
        }catch (e: NumberFormatException){
            progressBar.max = 0
            progressBar.progress = 0
        }catch (e: NullPointerException){
            progressBar.max = 0
            progressBar.progress = 0
        }
    }

}
