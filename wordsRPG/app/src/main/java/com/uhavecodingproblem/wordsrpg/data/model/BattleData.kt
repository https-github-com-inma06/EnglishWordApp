package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable

data class BattleData (
    val b_id: String , //배틀 고유 id
    val current_page:Int, // 현재 게임 페이지
    val total_page:Int, //총 문제 페이지
    val getPoint:Int //현재 점수

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            it.writeString(b_id)
            it.writeInt(current_page)
            it.writeInt(total_page)
            it.writeInt(getPoint)
        }
    }

    companion object CREATOR : Parcelable.Creator<BattleData> {
        override fun createFromParcel(parcel: Parcel): BattleData {
            return BattleData(parcel)
        }

        override fun newArray(size: Int): Array<BattleData?> {
            return arrayOfNulls(size)
        }
    }
}