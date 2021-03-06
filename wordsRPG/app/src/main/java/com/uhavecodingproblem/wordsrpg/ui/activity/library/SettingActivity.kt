package com.uhavecodingproblem.wordsrpg.ui.activity.library

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.ActivitySettingBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility

/**
 * wordsrpg
 * Class: SettingActivity.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 *
 * 설정 엑티비티 입니다.
 */

/* TODO : 삭제해도 될듯함 중복됨. 매니페스트 .ui.activity.SettingActivity 패키지로 수정했음. */
class SettingActivity: BaseUtility.BaseActivity<ActivitySettingBinding>(R.layout.activity_setting) {

    override fun ActivitySettingBinding.onCreate() {
        Logger.v("실행")
    }
}