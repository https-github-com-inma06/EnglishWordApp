package com.uhavecodingproblem.wordsrpg.ui.activity.library

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.CountDownTimer
import android.view.animation.LinearInterpolator
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.viewpageradapter.TestViewPagerAdapter
import com.uhavecodingproblem.wordsrpg.data.model.*
import com.uhavecodingproblem.wordsrpg.databinding.ActivityTestBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import com.uhavecodingproblem.wordsrpg.ui.dialog.TestResultDialogFragment
import com.uhavecodingproblem.wordsrpg.util.Logger

class TestActivity : BaseUtility.BaseActivity<ActivityTestBinding>(R.layout.activity_test) {

    private var testAdapter: TestViewPagerAdapter? = null
    private var testItem = mutableListOf<WordsRead>()
    private var countDownTimer: CountDownTimer? = null
    private var progressCountDownAnimator: ObjectAnimator? = null
    private var abnormalTermination = false
    private var currentPackage: ResponseBasicPackage.BasicPackage? = null

    private val responseTestList = mutableListOf<Test>(
        Test("1", Question("air", "공기"), mutableListOf(Example("air", "공기"), Example("act", "행동"), Example("address", "주소"), Example("afraid", "두려워하여"), Example("after", "후에"))),
        Test("2", Question("act", "행동"), mutableListOf(Example("air", "공기"), Example("act", "행동"), Example("address", "주소"), Example("afraid", "두려워하여"), Example("after", "후에"))),
        Test("2", Question("address", "주소"), mutableListOf(Example("air", "공기"), Example("act", "행동"), Example("address", "주소"), Example("afraid", "두려워하여"), Example("after", "후에"))),
        Test("2", Question("afraid", "두려워하여"), mutableListOf(Example("air", "공기"), Example("act", "행동"), Example("address", "주소"), Example("afraid", "두려워하여"), Example("after", "후에"))),
        Test("2", Question("after", "후에"), mutableListOf(Example("air", "공기"), Example("act", "행동"), Example("address", "주소"), Example("afraid", "두려워하여"), Example("after", "후에")))
    )

    private val correctAnswerList = mutableListOf<CorrectAnswer>()

    override fun ActivityTestBinding.onCreate() {
        setUpTestItem()
        viewPagerInit()
        observeWord()
        progressBarInit()
    }

    private fun setUpTestItem() {
        intent.also {
            it.getParcelableExtra<ResponseBasicPackage.BasicPackage>("currentBasicPackage")?.let { packageInformation->
                currentPackage = packageInformation
                setToolbarTitle(packageInformation.packageName)
            }
            it.getParcelableExtra<ResponseBasicPackage.Stage>("selectStage")?.let { stage ->

            }
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
        correctAnswerList.forEach { Logger.d("${it.type} ${it.correct_answer}") }



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

    }

    private fun viewPagerInit() {
        binding.viewpager2Test.apply {
            val shuffleNum = mutableListOf(1,2,3,4,5,6,7,8,9,10).shuffled().toMutableList()
            for (i in responseTestList.indices){
                responseTestList[i].example = responseTestList[i].example.shuffled().toMutableList()
            }
            testAdapter = TestViewPagerAdapter(shuffleNum ,responseTestList, object : TestViewPagerAdapter.OnItemClickListener{
                override fun onNextBtnClickEvent(type: Int, answer: String) {
                    correctAnswerList.add(CorrectAnswer(type, answer))
                    binding.viewpager2Test.currentItem += 1
                }

                override fun onResultPage(type: Int, answer: String) {
                    correctAnswerList.add(CorrectAnswer(type, answer))
                    if (countDownTimer != null) {
                        countDownTimer?.cancel()
                        countDownTimer = null
                    }
                    if (progressCountDownAnimator != null){
                        progressCountDownAnimator?.cancel()
                        progressCountDownAnimator = null
                    }
                }
            })
            adapter = testAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            isUserInputEnabled = true //변경 아타나시오
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