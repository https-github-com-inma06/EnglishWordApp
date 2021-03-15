package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * wordsrpg
 * Class: PackageWithStage
 * Created by pyg10.
 * Created On 2021-03-14.
 * Description:
 */
data class PackageWithStage(
    var p_id: String,
    val package_name: String,
    val package_thumbnail: String,
    var total_stage: String,
    var clear_stage: String
): Parcelable{

    constructor(parcel: Parcel): this(
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
            writeString(package_thumbnail)
            writeString(total_stage)
            writeString(clear_stage)
        }
    }

    companion object CREATOR : Parcelable.Creator<PackageWithStage> {
        override fun createFromParcel(parcel: Parcel): PackageWithStage {
            return PackageWithStage(parcel)
        }

        override fun newArray(size: Int): Array<PackageWithStage?> {
            return arrayOfNulls(size)
        }
    }
}
