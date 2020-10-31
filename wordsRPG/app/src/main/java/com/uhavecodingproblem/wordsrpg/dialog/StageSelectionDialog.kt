package com.uhavecodingproblem.wordsrpg.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.Stage
import com.uhavecodingproblem.wordsrpg.databinding.DialogStageSelectionNoneBinding
import com.uhavecodingproblem.wordsrpg.databinding.DialogStageSelectionOtherBinding
import com.uhavecodingproblem.wordsrpg.util.Logger

/**
 * wordsrpg
 * Class: BasicPackageSelectionDialog
 * Created by pyg10.
 * Created On 2020-10-25.
 * Description:
 */
class StageSelectionDialog(context: Context, private val stage: Stage, private val packageName: String, private val thumbnailImageUri: String): Dialog(context) {

    private lateinit var stageSelectionNone: DialogStageSelectionNoneBinding
    private lateinit var stageSelectionOther: DialogStageSelectionOtherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Logger.v("StageSelectionDialog onCreate")

        val layoutParam = WindowManager.LayoutParams().apply {
            flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            dimAmount = 0.8f
        }
        //setCancelable(false)
        window?.let {
            it.attributes = layoutParam
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.requestFeature(Window.FEATURE_NO_TITLE)
        }

        if (stage.stageStatus == 0){
            stageSelectionNone = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_stage_selection_none, null, false)
            stageSelectionNone.run {
                packagename = packageName
                thumbnailimage = thumbnailImageUri
                stageinformation = stage
                selectiondialog = this@StageSelectionDialog
            }
            stageSelectionNone.layoutSelectionDialog.clipToOutline = true
            setContentView(stageSelectionNone.root)
        }else{
            stageSelectionOther = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_stage_selection_other, null, false)
            stageSelectionOther.run {
                packagename = packageName
                thumbnailimage = thumbnailImageUri
                stageinformation = stage
                selectiondialog = this@StageSelectionDialog
            }
            stageSelectionOther.layoutSelectionDialog.clipToOutline = true
            setContentView(stageSelectionOther.root)
        }
    }

    fun exit(v: View){
        cancel()
    }

    fun moveStudy(v: View){
        Toast.makeText(context, "학습하러가기", Toast.LENGTH_SHORT).show()
    }

    fun moveTest(v: View){
        Toast.makeText(context, "테스트보러가기", Toast.LENGTH_SHORT).show()
    }

}