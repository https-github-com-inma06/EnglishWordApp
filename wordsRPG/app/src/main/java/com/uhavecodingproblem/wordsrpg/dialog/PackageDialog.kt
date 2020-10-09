package com.uhavecodingproblem.wordsrpg.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.CustomPackageData
import com.uhavecodingproblem.wordsrpg.data.WordType
import com.uhavecodingproblem.wordsrpg.databinding.BasicPackageDialogBinding
import com.uhavecodingproblem.wordsrpg.databinding.CustomPackageDialogBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.LibraryActivity

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
        window?.attributes = layoutParam

        //기본패키지인가?
        if (isBasic) {
            val binding: BasicPackageDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.basic_package_dialog, null, false)

            binding.run {
                information = type as WordType
                customdialog = this@PackageDialog
            }

            setContentView(binding.root)
        }else{
            val binding: CustomPackageDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_package_dialog, null, false)

            binding.run {
                information = type as CustomPackageData
                customdialog = this@PackageDialog
            }

            setContentView(binding.root)
        }
    }

    fun moveStudy(v: View){
        Intent(context, LibraryActivity::class.java).also {
            it.putExtra("Words", type as WordType)
            context.startActivity(it)
        }
    }

    fun moveTest(v: View){

    }

}