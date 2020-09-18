package com.uhavecodingproblem.wordsrpg.ui.activity.intro

import android.content.Intent
import android.view.View
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityRegisterBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.MainActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity

class RegisterActivity:BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {
    override fun ActivityRegisterBinding.onCreate() {}

    fun btnRegisterComplete(view: View){
        startActivity(Intent(this@RegisterActivity,MainActivity::class.java))
    }
}