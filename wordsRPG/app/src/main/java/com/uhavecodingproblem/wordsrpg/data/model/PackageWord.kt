package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable

data class PackageWord(
    val pw_id:String = "",
    val p_id:String = "",
    val w_id:String = "",
    val s_id:String = ""
):Parcelable{
    constructor(parcel: Parcel): this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.run {
            writeString(pw_id)
            writeString(p_id)
            writeString(w_id)
            writeString(s_id)
        }
    }

    companion object CREATOR : Parcelable.Creator<PackageWord> {
        override fun createFromParcel(parcel: Parcel): PackageWord {
            return PackageWord(parcel)
        }

        override fun newArray(size: Int): Array<PackageWord?> {
            return arrayOfNulls(size)
        }
    }
}
