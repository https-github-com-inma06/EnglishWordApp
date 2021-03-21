package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable

data class Friends(
    val user_id: String,
    val friend_id: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            it.writeString(user_id)
            it.writeString(friend_id)
        }
    }

    companion object CREATOR : Parcelable.Creator<Friends> {
        override fun createFromParcel(parcel: Parcel): Friends {
            return Friends(parcel)
        }

        override fun newArray(size: Int): Array<Friends?> {
            return arrayOfNulls(size)
        }
    }
}
