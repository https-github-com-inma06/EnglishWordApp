package com.uhavecodingproblem.wordsrpg.ui.activity

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.ActivityNotificationBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility

/**
 * wordsrpg
 * Class: NotificationActivity.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 *
 * 알림창 엑티비티 입니다.
 */

class NotificationActivity: BaseUtility.BaseActivity<ActivityNotificationBinding>(R.layout.activity_notification) {

    override fun ActivityNotificationBinding.onCreate() {
        Logger.d("실행")
    }


}