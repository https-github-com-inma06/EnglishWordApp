package com.uhavecodingproblem.wordsrpg.ui.activity

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.animation.BounceInterpolator
import android.view.animation.Interpolator
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivitySplashBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import com.uhavecodingproblem.wordsrpg.util.tedPermission
import kotlinx.coroutines.CoroutineScope

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun ActivitySplashBinding.onCreate() {
        tedPermission({
            setAnimation()
        }, {
            finish()
        })
    }

    private fun ActivitySplashBinding.setAnimation() {
        ivLogo.animate().translationY(-1000f).setDuration(1).withEndAction {
            ivLogo.animate().translationY(0f).setInterpolator(BounceInterpolator())
                .setDuration(1000).withEndAction {
                Handler(Looper.myLooper()!!).postDelayed(
                    { startActivity(Intent(this@SplashActivity, MainActivity::class.java)) },
                    500
                )
            }.start()
        }.start()
    }
}