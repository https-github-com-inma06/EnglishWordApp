package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.uhavecodingproblem.wordsrpg.data.mockdata.PackageInformation

/**
 * wordsrpg
 * Class: PackageRead
 * Created by pyg10.
 * Created On 2021-01-29.
 * Description:
 */
//패키지 테이블
data class PackageRead(
    @SerializedName("p_id") val p_id: String? = null,
    @SerializedName("package_name") val package_name: String? = null,
    @SerializedName("package_difficulty") val package_difficulty: String? = null,
    @SerializedName("package_thumbnail") val package_thumbnail: String? = null,
    @SerializedName("total_word") val total_word: String? = null,
    @SerializedName("is_custom") val is_custom: String? = null,
    @SerializedName("customer_id") val customer_id: String? = null
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
            writeString(is_custom)
            writeString(customer_id)
        }
    }

    companion object CREATOR : Parcelable.Creator<PackageRead> {
        override fun createFromParcel(parcel: Parcel): PackageRead {
            return PackageRead(parcel)
        }

        override fun newArray(size: Int): Array<PackageRead?> {
            return arrayOfNulls(size)
        }
    }
}
