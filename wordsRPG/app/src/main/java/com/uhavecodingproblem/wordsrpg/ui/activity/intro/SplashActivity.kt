package com.uhavecodingproblem.wordsrpg.ui.activity.intro

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivitySplashBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.MainActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val splashAnim by lazy {
        AnimationUtils.loadAnimation(
            this@SplashActivity,
            R.anim.anim_splash
        )
    }

    private val splashHandler = Handler(Looper.myLooper()!!)

    private val pref by lazy{ getSharedPreferences(packageName, MODE_PRIVATE)}
    override fun ActivitySplashBinding.onCreate() {

        tvSplashLogo.alpha = 0f
        ivSplashLogo.startAnimation(splashAnim)
        tvSplashLogo.animate().setStartDelay(1500L).alpha(1f).setDuration(1000L).start()

        splashHandler.postDelayed({

            if (!pref.getBoolean(GuideActivity.AppFirstRunCheck, false)) {
                startActivity(Intent(this@SplashActivity, GuideActivity::class.java))
            }else{
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }

            finish()
        }, 3500L)
    }

    override fun onDestroy() {
        super.onDestroy()
        splashHandler.removeCallbacksAndMessages(null)
    }
}