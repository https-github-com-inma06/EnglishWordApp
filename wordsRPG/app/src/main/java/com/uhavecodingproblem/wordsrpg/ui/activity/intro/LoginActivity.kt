package com.uhavecodingproblem.wordsrpg.ui.activity.intro

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityLoginBinding
import com.uhavecodingproblem.wordsrpg.databinding.DialogFindPasswordBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.MainActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import com.uhavecodingproblem.wordsrpg.ui.fragment.MainMyRoomFragment.Companion.loginCheck
import com.uhavecodingproblem.wordsrpg.util.toastShow

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun ActivityLoginBinding.onCreate() {}

    fun btnLoginClickEvent(view: View) {
        /**
         * FIXME:임시로 지정, 차후에 로그인 방법 로직 다시 설정
         */
        loginCheck = true
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }

    fun btnGoToTheRegisterClickEvent(view: View) {
        startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
    }

    fun btnFindPasswordClickEvent(view: View) {
        val binding = DataBindingUtil.inflate<DialogFindPasswordBinding>(
            LayoutInflater.from(this),
            R.layout.dialog_find_password, null, false
        )
        val dialog = AlertDialog.Builder(this, R.style.ThemeOverlay_AppCompat_Dialog_Alert)
        val createDialog = dialog.create()
        createDialog.apply {
            setView(binding.root)
            show()
        }

        binding.btnSendToEmail.setOnClickListener {
            toastShow("${binding.etEmailCheck.text}의 메일을 확인해주세요")
            createDialog.dismiss()
        }
    }
}