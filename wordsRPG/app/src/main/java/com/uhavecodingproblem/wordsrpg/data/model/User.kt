package com.uhavecodingproblem.wordsrpg.data.model

data class User(

    val userId: Long,  //유저 아이디
    val userEmail: String,     //유저 이메일
    val userPassword: String,   //유저 패스워드
    val userName: String?=null,   //유저 네임
    
    val profileImage: String? = null,  //이미지
    val battleData: UserBattleData? = null, //배틀 데이터
    val friendList: List<User>? = null     //친구 목록

)

