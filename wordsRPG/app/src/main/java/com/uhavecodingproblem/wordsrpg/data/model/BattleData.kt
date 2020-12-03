package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable

data class BattleData (
    val idx: Long = 0L,
    val currentPage:Int,
    val lastPage:Int,
    val currentScore:Int

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(idx)
        parcel.writeInt(currentPage)
        parcel.writeInt(lastPage)
        parcel.writeInt(currentScore)
    }

    override fun describeContents(): Int {
        return 0
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