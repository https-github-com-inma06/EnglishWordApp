package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * wordsrpg
 * Class: PackageRead
 * Created by pyg10.
 * Created On 2021-01-29.
 * Description:
 */
//패키지 테이블
data class Package(
    val p_id: String = "",
    val package_name: String = "",
    val package_difficulty: String = "",
    val package_thumbnail: String = "",
    val total_word: String = "",
    val customCheck: String = "",
    val customer_id: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this (
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.run {
            writeString(p_id)
            writeString(package_name)
            writeString(package_difficulty)
            writeString(package_thumbnail)
            writeString(total_word)
            writeString(customCheck)
            writeString(customer_id)
        }
    }

    companion object CREATOR : Parcelable.Creator<Package> {
        override fun createFromParcel(parcel: Parcel): Package {
            return Package(parcel)
        }

        override fun newArray(size: Int): Array<Package?> {
            return arrayOfNulls(size)
        }
    }
}
