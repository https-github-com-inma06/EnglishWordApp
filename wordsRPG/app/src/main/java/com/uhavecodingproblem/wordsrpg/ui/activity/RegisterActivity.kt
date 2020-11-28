package com.uhavecodingproblem.wordsrpg.ui.activity

import android.content.Intent
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityRegisterBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_splash.*

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {

    companion object {
        const val REQUEST_IMAGE_CODE = 1058
    }


    override fun ActivityRegisterBinding.onCreate() {

    }


    fun setIvProfileImageSelectClickListener(v: View) {
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also {
            startActivityForResult(it, REQUEST_IMAGE_CODE)
        }
    }

    fun setBtnRegisterCompleteClickListener(v: View) {

        binding.apply {
            if (etEmail.text.toString().isEmpty() || etResgisterPassword.text.toString().isEmpty() ||
                etPasswordCheck.toString().isEmpty()
            )
                Toast.makeText(this@RegisterActivity, "필수항목 채워주셈", Toast.LENGTH_SHORT).show()
            else if (etResgisterPassword.text.toString() == etPasswordCheck.text.toString())
                Toast.makeText(this@RegisterActivity, "패스워드와 재확인이 맞지 않음", Toast.LENGTH_SHORT).show()
            else
                startActivity(Intent(this@RegisterActivity, MainActivity::class.java))

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CODE)
            data?.data.let {
                Glide.with(this).load(it).into(binding.ivProfileImageSelect)
            }
    }
}