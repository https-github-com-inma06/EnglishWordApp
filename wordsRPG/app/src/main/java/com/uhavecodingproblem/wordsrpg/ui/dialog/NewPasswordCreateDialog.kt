package com.uhavecodingproblem.wordsrpg.ui.dialog

import android.content.Context
import android.view.WindowManager
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.DialogNewpasswordCrateBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility

class NewPasswordCreateDialog(context: Context) :
    BaseUtility.BaseDialog<DialogNewpasswordCrateBinding>(R.layout.dialog_newpassword_crate, context) {
//
//    val binding = DataBindingUtil.inflate<DialogNewpasswordCrateBinding>(
//        LayoutInflater.from(context),
//        R.layout.dialog_newpassword_crate, null, false
//    )

    override fun DialogNewpasswordCrateBinding.onCreate() {
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