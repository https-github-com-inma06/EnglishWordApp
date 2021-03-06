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
import com.uhavecodingproblem.wordsrpg.databinding.DialogCustomPackageBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility


/**
 * wordsrpg
 * Class: PackageDialog
 * Created by pyg10.
 * Created On 2020-10-09.
 * Description:
 */
class PackageDialog(context: Context, val type: Any) : BaseUtility.BaseDialog<DialogCustomPackageBinding>(R.layout.dialog_custom_package, context) {

    override fun DialogCustomPackageBinding.onCreate() {
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


        binding.run {
            information = type as CustomPackageData
            customdialog = this@PackageDialog
        }

        binding.layoutImage.clipToOutline = true
    }

    fun moveStudy(v: View) {

    }

    fun moveTest(v: View) {

    }

}