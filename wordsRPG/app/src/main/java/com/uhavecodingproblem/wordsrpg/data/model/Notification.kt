package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable


data class Notification(
    val userIdx: Long,   //알림 고유아이디
    var notificationType: Int? = null, //알림 타입 승리,패배,무승부, 배틀신청
    var date: String? = null // 알람 날짜
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(userIdx)
        parcel.writeValue(notificationType)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Notification> {
        override fun createFromParcel(parcel: Parcel): Notification {
            return Notification(parcel)
        }

        override fun newArray(size: Int): Array<Notification?> {
            return arrayOfNulls(size)
        }
    }
}