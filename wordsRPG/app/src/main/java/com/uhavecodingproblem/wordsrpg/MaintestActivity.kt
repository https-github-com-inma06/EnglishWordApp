package com.uhavecodingproblem.wordsrpg

import android.R
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
import com.uhavecodingproblem.wordsrpg.databinding.ActivityMaintestBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import kotlinx.android.synthetic.main.activity_maintest.*
import java.util.*


class MaintestActivity: BaseUtility.BaseActivity<ActivityMaintestBinding>(com.uhavecodingproblem.wordsrpg.R.layout.activity_maintest) {


    override fun ActivityMaintestBinding.onCreate() {
        mainTestActivity = this@MaintestActivity

//        cvTest.textDirection =
//        val languageToLoad = "fa" // your language
//
//        val locale = Locale(languageToLoad)
//        Locale.setDefault(locale)
//        val config = Configuration()

//        config.setLocale(Locale.ENGLISH)
        baseContext.resources.configuration.setLocale(Locale.KOREAN)
//        baseContext.resources.updateConfiguration(
//            config,
//            baseContext.resources.displayMetrics
//        )


    }


}