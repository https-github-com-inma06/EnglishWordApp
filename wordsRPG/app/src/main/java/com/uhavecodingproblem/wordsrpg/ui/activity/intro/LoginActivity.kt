package com.uhavecodingproblem.wordsrpg.ui.activity.intro

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityLoginBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.MainActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import com.uhavecodingproblem.wordsrpg.ui.fragment.MainMyRoomFragment.Companion.loginCheck

class LoginActivity:BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun ActivityLoginBinding.onCreate() {}

    fun btnLoginClickEvent(view:View){
        /**
         * FIXME:임시로 지정, 차후에 로그인 방법 로직 다시 설정
         */
        loginCheck = true
        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
    }

    fun btnGoToTheRegisterClickEvent(view: View){
        startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
    }
}