package com.uhavecodingproblem.wordsrpg.ui.activity.intro


import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityGuideBinding
import com.uhavecodingproblem.wordsrpg.util.ResourcePath.RESOURCE_PATH
import com.uhavecodingproblem.wordsrpg.ui.activity.MainActivity
import com.uhavecodingproblem.wordsrpg.ui.adapter.GuideViewPagerAdapter
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity

class GuideActivity : BaseActivity<ActivityGuideBinding>(R.layout.activity_guide) {

    private val pref by lazy{ getSharedPreferences(packageName, MODE_PRIVATE)}
    companion object {
        const val AppFirstRunCheck = "AppFirstRunCheck"
    }

    private var prevPosition = 0

    override fun ActivityGuideBinding.onCreate() {
        val imageList = setAdapter()
        setIndicatorLogic(imageList)
    }

    private fun ActivityGuideBinding.setIndicatorLogic(
        imageList: MutableList<String>
    ) {
        ivGuideIndicator0.isSelected = true
        guideViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (position == imageList.size - 1)
                    btnAppStart.visibility = View.VISIBLE

                findViewById<ImageView>(
                    resources.getIdentifier(
                        "iv_guideIndicator${prevPosition}",
                        "id",
                        packageName
                    )
                ).isSelected = false

                findViewById<ImageView>(
                    resources.getIdentifier(
                        "iv_guideIndicator$position",
                        "id",
                        packageName
                    )
                ).isSelected = true
                prevPosition = position
            }

            override fun onPageScrollStateChanged(state: Int) {}

        })
    }

    private fun ActivityGuideBinding.setAdapter(): MutableList<String> {
        val imageList = MutableList(4) { "" }
        for (i in imageList.indices) {
            imageList[i] = RESOURCE_PATH + resources.getIdentifier(
                "guide_sampleimage${i + 1}",
                "drawable", packageName
            ).toString()
        }
        guideViewPager.adapter = GuideViewPagerAdapter(imageList)
        return imageList
    }

    fun setBtnAppStartClickListener(view:View) {
        pref.edit().putBoolean(AppFirstRunCheck, true).apply()
            startActivity(Intent(this, MainActivity::class.java))
        finish()

    }

}