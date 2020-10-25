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
import com.uhavecodingproblem.wordsrpg.databinding.BasicPackageStageSelectionDialogNoneBinding
import com.uhavecodingproblem.wordsrpg.databinding.BasicPackageStageSelectionDialogOtherBinding

/**
 * wordsrpg
 * Class: BasicPackageSelectionDialog
 * Created by pyg10.
 * Created On 2020-10-25.
 * Description:
 */
class BasicPackageSelectionDialog(context: Context,private val stage: Stage, private val packageName: String, private val thumbnailImageUri: String): Dialog(context) {

    private lateinit var basicPackageStageSelectionDialogNoneBinding: BasicPackageStageSelectionDialogNoneBinding
    private lateinit var basicPackageStageSelectionDialogOtherBinding: BasicPackageStageSelectionDialogOtherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            basicPackageStageSelectionDialogNoneBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.basic_package_stage_selection_dialog_none, null, false)
            basicPackageStageSelectionDialogNoneBinding.run {
                packagename = packageName
                thumbnailimage = thumbnailImageUri
                stageinformation = stage
                selectiondialog = this@BasicPackageSelectionDialog
            }
            basicPackageStageSelectionDialogNoneBinding.layoutSelectionDialog.clipToOutline = true
            setContentView(basicPackageStageSelectionDialogNoneBinding.root)
        }else{
            basicPackageStageSelectionDialogOtherBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.basic_package_stage_selection_dialog_other, null, false)
            basicPackageStageSelectionDialogOtherBinding.run {
                packagename = packageName
                thumbnailimage = thumbnailImageUri
                stageinformation = stage
                selectiondialog = this@BasicPackageSelectionDialog
            }
            basicPackageStageSelectionDialogOtherBinding.layoutSelectionDialog.clipToOutline = true
            setContentView(basicPackageStageSelectionDialogOtherBinding.root)
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