package com.uhavecodingproblem.wordsrpg.ui.dialog

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.DialogPasswordFindBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility

class PasswordFindDialog(context: Context) :
    BaseUtility.BaseDialog<DialogPasswordFindBinding>(R.layout.dialog_password_find, context) {
//    var binding: DialogPasswordFindBinding = DataBindingUtil.inflate(
//        LayoutInflater.from(context), R.layout.dialog_password_find,
//        null, false
//    )

    override fun DialogPasswordFindBinding.onCreate() {
        binding.apply {
            WindowManager.LayoutParams().let {
                val dp = context.resources.displayMetrics.density
                it.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
                it.dimAmount = 0.8f
                it.height = (250 * dp).toInt()

                window?.attributes = it
            }
            setCancelable(false)
        }
    }
}