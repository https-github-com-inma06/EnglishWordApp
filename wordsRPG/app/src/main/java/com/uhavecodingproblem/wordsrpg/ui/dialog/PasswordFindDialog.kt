package com.uhavecodingproblem.wordsrpg.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.DialogPasswordFindBinding

class PasswordFindDialog(context: Context) : Dialog(context) {
    var binding: DialogPasswordFindBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context), R.layout.dialog_password_find,
        null,false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            setContentView(root)
            WindowManager.LayoutParams().let {
                val dp = context.resources.displayMetrics.density
                it.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
                it.dimAmount = 0.8f
                it.height = (350 * dp).toInt()

                window?.attributes = it

            }
        }
    }
}