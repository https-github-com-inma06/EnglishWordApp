package com.uhavecodingproblem.wordsrpg.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
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
import com.uhavecodingproblem.wordsrpg.data.StageInformation
import com.uhavecodingproblem.wordsrpg.databinding.DialogStageSelectionNoneBinding
import com.uhavecodingproblem.wordsrpg.databinding.DialogStageSelectionOtherBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.StudyActivity
import com.uhavecodingproblem.wordsrpg.util.Logger

/**
 * wordsrpg
 * Class: BasicPackageSelectionDialog
 * Created by pyg10.
 * Created On 2020-10-25.
 * Description:
 */
class StageSelectionDialog(
    context: Context,
    private val stageInformation: StageInformation,
    private val packageName: String,
    private val thumbnailImageUri: String
) : Dialog(context) {

    private lateinit var userStatusNone: DialogStageSelectionNoneBinding
    private lateinit var userStatusOther: DialogStageSelectionOtherBinding

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

        if (stageInformation.stageStatus == 0) {
            userStatusNone =
                DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_stage_selection_none, null, false)
            userStatusNone.run {
                name = packageName
                thumbnailuri = thumbnailImageUri
                stage = stageInformation
                dialog = this@StageSelectionDialog
            }
            userStatusNone.layoutSelectionDialog.clipToOutline = true
            setContentView(userStatusNone.root)
        } else {
            userStatusOther =
                DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_stage_selection_other, null, false)
            userStatusOther.run {
                name = packageName
                thumbnailuri = thumbnailImageUri
                stage = stageInformation
                dialog = this@StageSelectionDialog
            }
            userStatusOther.layoutSelectionDialog.clipToOutline = true
            setContentView(userStatusOther.root)
        }
    }

    fun exit(v: View) {
        cancel()
    }

    fun moveStudy(v: View) {
        stageInformation.stageStatus = 1
        Intent(context, StudyActivity::class.java).also {
            it.putExtra("StudyWord", stageInformation)
            context.startActivity(it)
        }
        dismiss()
    }

    fun moveTest(v: View) {
        Toast.makeText(context, "테스트보러가기", Toast.LENGTH_SHORT).show()
        dismiss()
    }

}