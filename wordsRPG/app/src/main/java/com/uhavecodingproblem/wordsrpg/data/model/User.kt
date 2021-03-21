package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable


data class User(

    val u:String?=null,
    var user_id: String?=null,  //유저 아이디
    val user_password: String?=null,   //유저 패스워드
    val user_email: String?=null,     //유저 이메일
    var user_nickName: String? = null,   //유저 네임
    var user_profileImg: String? = null,  //이미지
    var user_rank:String? = null, //나의 랭크
    val user_ratingBadge: String? = null, //랭킹 등급
    var user_score: Long? = null, // 배틀 점수
    val user_friendList:List<Int>? = null, //친구 리스트

    var user_battleList: List<Int>? = null, //배틀 데이타 리스트
    var user_battleNotiList: List<Int>? = null // 알림(배틀) 관련 데이타 리스트

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readArrayList(Int::class.java.classLoader) as? List<Int>,
        parcel.readArrayList(Int::class.java.classLoader) as? List<Int>,
        parcel.readArrayList(Int::class.java.classLoader) as? List<Int>
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            it.writeString(u)
            it.writeString(user_id)
            it.writeString(user_password)
            it.writeString(user_email)
            it.writeString(user_nickName)
            it.writeString(user_profileImg)
            it.writeString(user_rank)
            it.writeString(user_ratingBadge)
            it.writeValue(user_score)
            it.writeList(user_friendList)
            it.writeList(user_battleList)
            it.writeList(user_battleNotiList)
        }
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}