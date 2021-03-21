package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*


data class BattleNotification(
    val bn_id: String,   //알림 고유아이디
    var notice_category: Int? = null, //알림 타입 승리,패배,무승부, 배틀신청
    var notice_timestamp: Date,
    var update_timestamp: Date
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Date::class.java.classLoader) as Date,
        parcel.readValue(Date::class.java.classLoader) as Date
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            it.writeString(bn_id)
            it.writeValue(notice_category)
            it.writeValue(notice_timestamp)
            it.writeValue(update_timestamp)
        }
    }

    companion object CREATOR : Parcelable.Creator<BattleNotification> {
        override fun createFromParcel(parcel: Parcel): BattleNotification {
            return BattleNotification(parcel)
        }

        override fun newArray(size: Int): Array<BattleNotification?> {
            return arrayOfNulls(size)
        }
    }
}