package com.uhavecodingproblem.wordsrpg.data.model

import java.io.Serializable


data class User(

    val u:String,
    var user_id: Long,  //유저 아이디
    val user_password: String,   //유저 패스워드
    val user_email: String,     //유저 이메일
    var user_nickName: String? = null,   //유저 네임
    var user_profileImg: String? = null,  //이미지
    var user_rank:String? = null, //나의 랭크
    val user_ratingBadge: String? = null, //랭킹 등급
    var user_score: Long? = null, // 배틀 점수
    val user_friendList:List<Int>? = null, //친구 리스트

    var user_battleList: List<Int>? = null, //배틀 데이타 리스트
    var user_battleNotiList: List<Int>? = null // 알림(배틀) 관련 데이타 리스트

):Serializable