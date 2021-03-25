package com.uhavecodingproblem.wordsrpg.ui.dialog

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.TestResultCalculatorViewModel
import com.uhavecodingproblem.wordsrpg.data.model.RequestTest
import com.uhavecodingproblem.wordsrpg.data.model.ResponseTest
import com.uhavecodingproblem.wordsrpg.databinding.DialogTestResultBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import com.uhavecodingproblem.wordsrpg.util.dialogResize

/**
 * wordsrpg
 * Class: TestResultDialogFragment
 * Created by pyg10.
 * Created On 2021-03-23.
 * Description:
 */
class TestResultDialogFragment: BaseUtility.BaseDialogFragment<DialogTestResultBinding>(R.layout.dialog_test_result) {

    private val resultViewModel by viewModels<TestResultCalculatorViewModel>()

    interface OnDialogFragmentExit{
        fun onDismissDialog()
        fun onCancelDialog()
    }

    companion object{

        private var exitListener : OnDialogFragmentExit? = null

        fun getInstance(answerList: MutableList<ResponseTest>, correctAnswerList: MutableList<String>, listener: OnDialogFragmentExit) : TestResultDialogFragment{
            exitListener = listener
            return TestResultDialogFragment()
        }
    }


    override fun onResume() {
        super.onResume()
        requireContext().dialogResize(this@TestResultDialogFragment, 0.8f, 0.4f)
    }

    override fun DialogTestResultBinding.onViewCreated() {
        val layoutParams = WindowManager.LayoutParams().apply {
            flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            dimAmount = 0.8f
        }

        dialog?.window?.apply {
            attributes = layoutParams
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestFeature(Window.FEATURE_NO_TITLE)
        }

        binding.apply {
            rightAnswer = "7"
            wrongAnswer = "3"
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        exitListener?.onDismissDialog()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        exitListener?.onCancelDialog()
    }
}