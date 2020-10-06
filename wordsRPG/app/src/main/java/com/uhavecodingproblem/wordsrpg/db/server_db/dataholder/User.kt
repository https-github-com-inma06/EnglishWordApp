package com.uhavecodingproblem.wordsrpg.db.server_db.dataholder

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("user_id")
    val userId:String,
    @SerializedName("email")
    val email: String,
    @SerializedName("user_password")
    val userPassword: String,
    @SerializedName("user_name")
    val userName: String?,
    @SerializedName("user_nick")
    val userNick: String?,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: String?,
    @SerializedName("remember_token")
    var rememberToken: String?,
    @SerializedName("created_at")
    val createdAt: Int,
    @SerializedName("updated_at")
    val updatedAt: String?

//    @PrimaryKey
//    val idx: String,
//    //유저 아이디
//    @NotNull @PrimaryKey
//    val user_id: String,
//    //유저 이메일
//    @NotNull @PrimaryKey
//    val email_address: String,
//    //암호화 패스워드
//    @NotNull
//    val password: String,
//    //프로필 이미지 경로
//    val user_porofileImg: String,
//    //유저 레벨
//    val user_lv: String,
//    //유저 경험치
//    val user_exp: String,
//    //유저 전화번호
//    @PrimaryKey
//    val phone_number: String,
//    //유저 이름
//    val user_name: String,
//    //유저 닉네임
//    @PrimaryKey
//    val nick_name: String,
//    //생일
//    val birthday: String,
//    //가입일
//    val regdate: String,
//    //가입시 아이피주소
//    val ipaddress: String,
//    //마지막 로그인 시간
//    val last_login: String,
//    //마지막 로그인 아이피주소
//    val last_login_ipaddress: String,
//    //패스워드 변경일
//    val change_password_date: String,
//    //관리자여부
//    val is_admin: String

)
