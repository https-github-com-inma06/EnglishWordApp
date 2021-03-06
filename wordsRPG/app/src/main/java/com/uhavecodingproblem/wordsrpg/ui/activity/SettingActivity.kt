package com.uhavecodingproblem.wordsrpg.ui.activity

import android.content.Intent
import android.widget.Toast
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.ActivitySettingBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility

/**
 * wordsrpg
 * Class: SettingActivity.
 * Created by Atanasio.
 * Created On 2021-03-06.
 * Description:
 *
 * 설정 엑티비티 입니다.
 */
class SettingActivity: BaseUtility.BaseActivity<ActivitySettingBinding>(R.layout.activity_setting) {

    override fun ActivitySettingBinding.onCreate() {
        Logger.v("실행")

        btnLogout.setOnClickListener {
            Toast.makeText(this@SettingActivity,"로그아웃",Toast.LENGTH_SHORT).show()
        }

        btnLicense.setOnClickListener {
            Toast.makeText(this@SettingActivity,"라이센스",Toast.LENGTH_SHORT).show()
        }

        btnVersion.setOnClickListener {
            Toast.makeText(this@SettingActivity,"버전",Toast.LENGTH_SHORT).show()
        }


    }
}