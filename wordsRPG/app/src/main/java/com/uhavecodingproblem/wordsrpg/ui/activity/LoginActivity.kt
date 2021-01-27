package com.uhavecodingproblem.wordsrpg.ui.activity

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityLoginBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import com.uhavecodingproblem.wordsrpg.ui.dialog.NewPasswordCreateDialog
import com.uhavecodingproblem.wordsrpg.ui.dialog.PasswordFindDialog
import java.util.regex.Pattern


class LoginActivity : BaseUtility.BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val callbackManager: CallbackManager? = CallbackManager.Factory.create();

    companion object {
        const val RC_SIGN_IN = 8224
    }

    override fun ActivityLoginBinding.onCreate() {
        loginActivity = this@LoginActivity
    }

    fun setBtnRegisterClickListener(v:View){
        startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
    }
    fun setBtnFaceBookLoginClickListener(v: View) {

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }

                override fun onCancel() {
                }

                override fun onError(exception: FacebookException) {
                    Log.d("asdasd", "$exception 로그인 실패")
                }
            })
    }

    fun setBtnPasswordFindClickListener(v: View) {
        val dialog = PasswordFindDialog(this@LoginActivity)
        dialog.show()
        dialog.binding.apply {
            btnCertificationNumberSend.setOnClickListener {
                if (!isValidEmail(etEmailNumberSend.text.toString())) {
                    Toast.makeText(
                        this@LoginActivity,
                        "올바른 이메일 형식으로 적어주세요 \n" +
                                "띄어쓰기도 없어야 합니다", Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }

                Toast.makeText(
                    this@LoginActivity,
                    "${etEmailNumberSend.text}으로 인증번호를 보냈습니다.\n인증 번호${456456}를 입력해주세요",
                    Toast.LENGTH_LONG
                ).show()

                etCertificationNumber.visibility = View.VISIBLE
                btnCertificationNumberCheck.visibility = View.VISIBLE
            }

            btnCertificationNumberCheck.setOnClickListener {
                if (etCertificationNumber.text.toString() == 456456.toString()) {
                    dialog.cancel()
                    dialog.dismiss()
                    val newPasswordDialog = NewPasswordCreateDialog(this@LoginActivity)
                    newPasswordDialog.show()
                    newPasswordDialog.binding.apply {
                        btnPasswordChange.setOnClickListener {
                            if (etNewPasswordWrite.text.toString() != etNewPasswordWriteCheck.text.toString()) {
                                Toast.makeText(this@LoginActivity, "비밀번호가 서로 다릅니다.", Toast.LENGTH_SHORT).show()
                                return@setOnClickListener
                            }
                            if(etNewPasswordWrite.text.length <8) {
                                Toast.makeText(this@LoginActivity, "비밀번호는 8자리 이상으로 설정해주세요", Toast.LENGTH_LONG).show()
                                return@setOnClickListener
                            }
                            Toast.makeText(this@LoginActivity, "조건문 모두 true 일시 여기서 api 출동해갔구 비밀번호가 변경할거임", Toast.LENGTH_SHORT).show()
                            newPasswordDialog.cancel()
                            newPasswordDialog.dismiss()
                        }
                    }
                } else
                    Toast.makeText(this@LoginActivity, "인증번호가 틀립니다", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        var check = false
        val regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        val emailePattern = Pattern.compile(regex);
        val text = emailePattern.matcher(email);

        if (text.matches()) {
            check = true;
        }
        return check
    }


    fun setBtnGoogleLoginClickListener(v: View) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent: Intent = mGoogleSignInClient.signInIntent

        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun setBtnKakaoLoginClickListener(v: View) {

        // 로그인 공통 callback 구성
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("asdasd", "로그인 실패", error)
            } else if (token != null) {
                Log.i("asdasd", "로그인 성공 ${token.accessToken}")
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (LoginClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {
            LoginClient.instance.loginWithKakaoTalk(this@LoginActivity, callback = callback)
        } else {
            LoginClient.instance.loginWithKakaoAccount(this@LoginActivity, callback = callback)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task)
            } catch (e: ApiException) {
                handleSignInResult(null)
            }
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>?) {

        val account = completedTask?.getResult(ApiException::class.java)
        if (account != null)
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))

    }
}