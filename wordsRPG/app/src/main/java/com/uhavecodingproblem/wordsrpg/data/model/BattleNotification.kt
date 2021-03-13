package com.uhavecodingproblem.wordsrpg.data.model

import java.io.Serializable
import java.util.*


data class BattleNotification(
    val bn_id: String,   //알림 고유아이디
    var notice_category: Int? = null, //알림 타입 승리,패배,무승부, 배틀신청
    var notice_timestamp: Date,
    var update_timestamp: Date
) : Serializable
