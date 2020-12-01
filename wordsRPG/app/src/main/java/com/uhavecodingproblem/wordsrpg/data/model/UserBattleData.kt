package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class UserBattleData(

    val battleId:Long, //배틀 모델 id
    val ratingBadge: String?=null, //랭킹 등급
    val score: Long // 배틀 점수

):  Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(battleId)
        parcel.writeString(ratingBadge)
        parcel.writeLong(score)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserBattleData> {
        override fun createFromParcel(parcel: Parcel): UserBattleData {
            return UserBattleData(parcel)
        }

        override fun newArray(size: Int): Array<UserBattleData?> {
            return arrayOfNulls(size)
        }
    }
}