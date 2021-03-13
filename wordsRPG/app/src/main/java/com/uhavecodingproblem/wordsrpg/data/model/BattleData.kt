package com.uhavecodingproblem.wordsrpg.data.model

import java.io.Serializable

data class BattleData (
    val b_id: String , //배틀 고유 id
    val current_page:Int, // 현재 게임 페이지
    val total_page:Int, //총 문제 페이지
    val getPoint:Int //현재 점수

):Serializable