package com.uhavecodingproblem.wordsrpg.ui.activity.intro

import android.content.Intent
import android.view.View
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityLoginBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity

class LoginActivity:BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    override fun ActivityLoginBinding.onCreate() {}

    fun btnGoToTheRegisterClickEvent(view: View){
        startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
    }
}