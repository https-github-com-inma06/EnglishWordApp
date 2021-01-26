package com.uhavecodingproblem.wordsrpg.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.DialogNewpasswordCrateBinding

class NewPasswordCreateDialog(context: Context) : Dialog(context) {

    val binding = DataBindingUtil.inflate<DialogNewpasswordCrateBinding>(
        LayoutInflater.from(context),
        R.layout.dialog_newpassword_crate, null, false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply{
            setContentView(root)
            WindowManager.LayoutParams().let{
                val dp = context.resources.displayMetrics.density
                it.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
                it.dimAmount = 0.8f
                it.height= (250*dp).toInt()
                window?.attributes = it

            }
            setCancelable(false)
        }
    }
}