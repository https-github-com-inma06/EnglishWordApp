package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable

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
    val package_difficulty: String = "", //
    val package_thumbnail: String = "",
    val total_word: String = "",
    val customCheck: String = "", //1일시 커스텀 패키지
    val customer_id: String? = "",
    val hashTagList: MutableList<String>? = null,
    val likeList: MutableList<String>? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createStringArrayList(),
        parcel.createStringArrayList()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            it.writeString(p_id)
            it.writeString(package_name)
            it.writeString(package_difficulty)
            it.writeString(package_thumbnail)
            it.writeString(total_word)
            it.writeString(customCheck)
            it.writeString(customer_id)
            it.writeStringList(hashTagList)
            it.writeStringList(likeList)
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