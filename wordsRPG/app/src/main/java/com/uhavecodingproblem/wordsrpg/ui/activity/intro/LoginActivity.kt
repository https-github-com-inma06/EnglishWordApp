package com.uhavecodingproblem.wordsrpg.ui.activity.intro

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.kakao.sdk.auth.LoginClient
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityLoginBinding
import com.uhavecodingproblem.wordsrpg.databinding.DialogFindPasswordBinding
import com.uhavecodingproblem.wordsrpg.repository.AuthRepository
import com.uhavecodingproblem.wordsrpg.ui.activity.MainActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import com.uhavecodingproblem.wordsrpg.ui.fragment.MainMyRoomFragment.Companion.loginCheck
import com.uhavecodingproblem.wordsrpg.util.toastShow
import com.uhavecodingproblem.wordsrpg.viewmodel.AuthViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val authViewModel by viewModels<AuthViewModel>()
    override fun ActivityLoginBinding.onCreate() {}

    fun btnLoginClickEvent(view: View) {
        val id = binding.etIdLogin.text.toString()
        val password = binding.etPasswordLogin.text.toString()

        authViewModel.userLogin(id, password,
            onSucceed = {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }, onFailure = {
                toastShow("아이디와 비밀번호를 확인해보세요")
            }
        )
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

    fun setBtnKaKaoTalkLoginClickListener(v:View){
        LoginClient.instance.loginWithKakaoTalk(this) { token, error ->
            if (error != null) {
                Log.d("TAG", "로그인 실패", error)
            }
            else if (token != null) {
                Log.d("TAG", "로그인 성공 ${token.accessToken}")
            }
        }
    }
}