package com.uhavecodingproblem.wordsrpg.ui.activity.setting

import android.content.Intent
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivitySettingBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.RegisterActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import com.uhavecodingproblem.wordsrpg.util.Logger
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_setting.*


/**
 * wordsrpg
 * Class: SettingActivity.
 * Created by Atanasio.
 * Created On 2021-03-06.
 * Description:
 *
 * 설정 엑티비티 입니다.
 */
class SettingActivity : BaseUtility.BaseActivity<ActivitySettingBinding>(R.layout.activity_setting) {

    companion object {
        const val REQUEST_IMAGE_CODE = 1059
    }

    override fun ActivitySettingBinding.onCreate() {
        Logger.d("실행")
        settingActivity = this@SettingActivity
    }


    fun setClickListener(v: View) {
        when (v.id) {
            binding.ivProfileImageSelect.id ->
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also {
                    startActivityForResult(it, REQUEST_IMAGE_CODE)
                }
            binding.btnLicense.id ->
                Toast.makeText(this@SettingActivity, "라이센스", Toast.LENGTH_SHORT).show()
            binding.btnVersion.id ->
                Toast.makeText(this@SettingActivity, "버전", Toast.LENGTH_SHORT).show()
            binding.btnLogout.id ->
                Toast.makeText(this@SettingActivity, "로그아웃", Toast.LENGTH_SHORT).show()


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RegisterActivity.REQUEST_IMAGE_CODE)


            //TODO: UCrop 적용시켜야함

        data?.data.let {
            Glide.with(this)
                .load(it)
                .centerCrop()
                .circleCrop()
                .into(binding.ivProfileImageSelect)
        }
    }
}