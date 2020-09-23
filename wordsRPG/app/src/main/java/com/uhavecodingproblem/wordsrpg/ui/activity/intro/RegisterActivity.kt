package com.uhavecodingproblem.wordsrpg.ui.activity.intro

import android.content.Intent
import android.view.View
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityRegisterBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.MainActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import com.uhavecodingproblem.wordsrpg.ui.fragment.MainMyRoomFragment

class RegisterActivity:BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {
    override fun ActivityRegisterBinding.onCreate() {}

    fun btnRegisterComplete(view: View){
        startActivity(Intent(this@RegisterActivity,MainActivity::class.java))
        /**
         * TODO:임시로 지정,로직 결정 후 변경 예정
         */
        MainMyRoomFragment.loginCheck = true
    }
}