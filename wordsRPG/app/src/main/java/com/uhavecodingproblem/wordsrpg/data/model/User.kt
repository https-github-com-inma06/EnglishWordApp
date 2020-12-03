package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class User(

    var userId: Long,  //유저 아이디
    val userEmail: String,     //유저 이메일
    val userPassword: String,   //유저 패스워드
    var userName: String? = null,   //유저 네임
    var profileImage: String? = null,  //이미지
    var friendList: List<User>? = null,     //친구 목록
    val ratingBadge: String? = null, //랭킹 등급
    var score: Long? = null, // 배틀 점수

    var processBattleList: List<BattleData>? = null, //진행중인 배틀 관련 데이타
    var notificationList: List<Notification>? = null // 알림 관련 데이타

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(CREATOR),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.createTypedArrayList(BattleData),
        parcel.createTypedArrayList(Notification)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(userId)
        parcel.writeString(userEmail)
        parcel.writeString(userPassword)
        parcel.writeString(userName)
        parcel.writeString(profileImage)
        parcel.writeTypedList(friendList)
        parcel.writeString(ratingBadge)
        parcel.writeValue(score)
        parcel.writeTypedList(processBattleList)
        parcel.writeTypedList(notificationList)
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