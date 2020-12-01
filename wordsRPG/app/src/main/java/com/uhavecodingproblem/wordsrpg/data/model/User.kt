package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable

data class User(

    val userId: Long,  //유저 아이디
    val userEmail: String,     //유저 이메일
    val userPassword: String,   //유저 패스워드
    val userName: String?=null,   //유저 네임
    
    val profileImage: String? = null,  //이미지
    val battleData: UserBattleData? = null, //배틀 데이터
    val friendList: List<User>? = null     //친구 목록

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(UserBattleData::class.java.classLoader),
        parcel.createTypedArrayList(CREATOR)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(userId)
        parcel.writeString(userEmail)
        parcel.writeString(userPassword)
        parcel.writeString(userName)
        parcel.writeString(profileImage)
        parcel.writeParcelable(battleData, flags)
        parcel.writeTypedList(friendList)
    }

    override fun describeContents(): Int {
        return 0
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

