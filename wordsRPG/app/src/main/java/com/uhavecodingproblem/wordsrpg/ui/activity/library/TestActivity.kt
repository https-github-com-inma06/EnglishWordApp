package com.uhavecodingproblem.wordsrpg.ui.activity.library

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.CountDownTimer
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.WordObserveViewModel
import com.uhavecodingproblem.wordsrpg.component.library.viewpageradapter.TestViewPagerAdapter
import com.uhavecodingproblem.wordsrpg.data.model.Learning
import com.uhavecodingproblem.wordsrpg.data.model.PackageWithStage
import com.uhavecodingproblem.wordsrpg.data.model.RequestTest
import com.uhavecodingproblem.wordsrpg.data.model.WordsRead
import com.uhavecodingproblem.wordsrpg.databinding.ActivityTestBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import com.uhavecodingproblem.wordsrpg.ui.dialog.TestResultDialogFragment
import com.uhavecodingproblem.wordsrpg.util.Logger

class TestActivity : BaseUtility.BaseActivity<ActivityTestBinding>(R.layout.activity_test) {

    private var testAdapter: TestViewPagerAdapter? = null
    private var testItem = mutableListOf<WordsRead>()
    private var countDownTimer: CountDownTimer? = null
    private val wordViewModel by viewModels<WordObserveViewModel>()
    private var progressCountDownAnimator: ObjectAnimator? = null
    private var abnormalTermination = false

    private val requestTestItem = mutableListOf<RequestTest>()

    override fun ActivityTestBinding.onCreate() {
        setUpTestItem()
        viewPagerInit()
        observeWord()
        progressBarInit()
    }

    private fun setUpTestItem() {
        intent.getParcelableExtra<Learning>("test")?.let {
            wordViewModel.loadWordLink(it.p_id, it.s_id, true)
        }
        intent.getParcelableExtra<PackageWithStage>("packageWithStage")?.let { packageInfo ->
            setToolbarTitle(packageInfo.package_name)
        }
    }

    private fun setToolbarTitle(packageName: String){
        setSupportActionBar(binding.testToolbar)

        val actionbar = supportActionBar
        actionbar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.title = packageName
    }

    private fun progressBarInit(){
        progressCountDownAnimator = ObjectAnimator.ofInt(binding.progressTimer, "progress", 100, 0).apply {
            duration = 60 * 1000
            interpolator = LinearInterpolator()
            addListener(object : Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator?) {
                    progressCountDown()
                }

                override fun onAnimationEnd(animation: Animator?) {
                    if (!abnormalTermination)
                        showResultDialog()
                }

                override fun onAnimationCancel(animation: Animator?) {

                }

                override fun onAnimationRepeat(animation: Animator?) {

                }
            })
        }
        progressCountDownAnimator?.start()
    }

    private fun showResultDialog(){
        val dialog = TestResultDialogFragment.getInstance(object : TestResultDialogFragment.OnDialogFragmentExit{
            override fun onDismissDialog() {
                finish()
            }

            override fun onCancelDialog() {
                finish()
            }
        })
        dialog.show(supportFragmentManager, "Result")
    }

    private fun progressCountDown(){
        countDownTimer = object : CountDownTimer(60*1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.tvTimer.text = calculatorTimer(millisUntilFinished)
            }

            override fun onFinish() {
                binding.tvTimer.text = "종료"
            }
        }
        countDownTimer?.start()
    }

    private fun calculatorTimer(time: Long): String{
        val min = time / 60 / 1000
        val sec = time / 1000 % 60
        return if (min > 0) "$min : $sec"
        else "$sec"
    }

    private fun observeWord() {
        wordViewModel.wordList.observe(this@TestActivity) { list ->

//            testItem.clear()
//            testItem.addAll(it)
//            testAdapter?.notifyDataSetChanged()
        }
    }

    private fun viewPagerInit() {
        binding.viewpager2Test.apply {
            testAdapter = TestViewPagerAdapter(testItem)
            adapter = testAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            isUserInputEnabled = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (countDownTimer != null) {
            abnormalTermination = true
            countDownTimer?.cancel()
            countDownTimer = null
        }
        if (progressCountDownAnimator != null){
            abnormalTermination = true
            progressCountDownAnimator?.cancel()
            progressCountDownAnimator = null
        }
    }
}