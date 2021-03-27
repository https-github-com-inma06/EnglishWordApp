package com.uhavecodingproblem.wordsrpg.ui.dialog

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.TestResultCalculatorViewModel
import com.uhavecodingproblem.wordsrpg.data.model.CorrectAnswer
import com.uhavecodingproblem.wordsrpg.data.model.Learning
import com.uhavecodingproblem.wordsrpg.data.model.Test
import com.uhavecodingproblem.wordsrpg.databinding.DialogTestResultBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import com.uhavecodingproblem.wordsrpg.util.Logger
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

    private val answerItem = mutableListOf<Test>()
    private val correctAnswerItem = mutableListOf<CorrectAnswer>()
    private var learning: Learning? = null

    interface OnDialogFragmentExit{
        fun onDismissDialog()
        fun onCancelDialog()
    }

    companion object{

        private var exitListener : OnDialogFragmentExit? = null

        fun getInstance(learning: Learning, answerList: MutableList<Test>, correctAnswer: MutableList<CorrectAnswer>, listener: OnDialogFragmentExit) : TestResultDialogFragment{
            exitListener = listener
            val dialogFragment = TestResultDialogFragment()
            val bundle = Bundle().apply {
                putParcelable("learning", learning)
                putParcelableArrayList("answer", answerList as ArrayList<Test>)
                putParcelableArrayList("correctAnswer", correctAnswer as ArrayList<CorrectAnswer>)
            }
            dialogFragment.arguments = bundle
            return dialogFragment
        }
    }


    override fun onResume() {
        super.onResume()
        requireContext().dialogResize(this@TestResultDialogFragment, 0.8f, 0.4f)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getParcelableArrayList<Test>("answer")?.let { test ->
                answerItem.addAll(test)
            }
            it.getParcelableArrayList<CorrectAnswer>("correctAnswer")?.let { correctAnswer->
                correctAnswerItem.addAll(correctAnswer)
            }
            it.getParcelable<Learning>("learning")?.let{learning->
                this.learning = learning
            }
        }
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

        Logger.d("${answerItem.size} , ${correctAnswerItem.size}")

        binding.apply {
            val result =resultViewModel.calculatorScore(answerItem, correctAnswerItem)
            learning?.let {resultViewModel.updateResult(result.totalQuestion, result.correct, it)}

            rightAnswer = result.correct.toString()
            wrongAnswer = result.wrong.toString()
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