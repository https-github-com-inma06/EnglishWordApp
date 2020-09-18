package com.uhavecodingproblem.wordsrpg.ui.activity.intro

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivitySplashBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class SplashActivity:BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val splashHandler = Handler(Looper.myLooper()!!)
     override fun ActivitySplashBinding.onCreate() {
         splashHandler.postDelayed({
            startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
            finish()
        },3000L)
    }

    override fun onDestroy() {
        super.onDestroy()
        splashHandler.removeCallbacksAndMessages(null)
    }
}