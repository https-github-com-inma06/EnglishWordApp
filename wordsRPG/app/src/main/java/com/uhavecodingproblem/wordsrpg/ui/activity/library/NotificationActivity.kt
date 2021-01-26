package com.uhavecodingproblem.wordsrpg.ui.activity.library

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.ActivityNotificationBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity

/**
 * wordsrpg
 * Class: NotificationActivity.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 *
 * 알림창 엑티비티 입니다.
 */

class NotificationActivity:BaseActivity<ActivityNotificationBinding>(R.layout.activity_notification) {

    override fun ActivityNotificationBinding.onCreate() {
        Logger.v("실행")
    }


}