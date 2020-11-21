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
import androidx.databinding.DataBindingUtil
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.CustomPackageData
import com.uhavecodingproblem.wordsrpg.databinding.CustomPackageDialogBinding

/**
 * wordsrpg
 * Class: PackageDialog
 * Created by pyg10.
 * Created On 2020-10-09.
 * Description:
 */
class PackageDialog(context: Context, val type: Any, private val isBasic: Boolean) : Dialog(context) {

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

        val binding: CustomPackageDialogBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_package_dialog, null, false)

        binding.run {
            information = type as CustomPackageData
            customdialog = this@PackageDialog
        }

        setContentView(binding.root)

        binding.layoutImage.clipToOutline = true

    }

    fun moveStudy(v: View) {

    }

    fun moveTest(v: View) {

    }

}