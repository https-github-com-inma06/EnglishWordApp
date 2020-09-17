package com.uhavecodingproblem.wordsrpg.ui.activity

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.ActivitySettingBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity

/**
 * wordsrpg
 * Class: SettingActivity.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 *
 * 설정 엑티비티 입니다.
 */
class SettingActivity:BaseActivity<ActivitySettingBinding>(R.layout.activity_setting) {

    override fun ActivitySettingBinding.onCreateSetData() {
        Logger.v("실행")
    }
}