package com.uhavecodingproblem.wordsrpg.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

/**
 * wordsrpg
 * Class: ByLevelLibraryItem
 * Created by pyg10.
 * Created On 2020-10-02.
 * Description:
 */

@Entity
data class PackageInformation(
    val userId: String, // 유저 아이디
    val type: String, // 수준별, 시험별, 카테고리별..
    val name: String, // 초등학교 1학년, 중학교 1학년, 고등학교 1학년, 토익 등등
    val thumbnailImage: String, // 배경이미지
    val stageList: MutableList<StageInformation> // 스테이지
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString()?: "",
            parcel.createTypedArrayList(StageInformation.CREATOR) as MutableList<StageInformation>

    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.run {
            writeString(userId)
            writeString(type)
            writeString(name)
            writeString(thumbnailImage)
            writeTypedList(stageList)
        }
    }

    companion object CREATOR : Parcelable.Creator<PackageInformation> {
        override fun createFromParcel(parcel: Parcel): PackageInformation {
            return PackageInformation(parcel)
        }

        override fun newArray(size: Int): Array<PackageInformation?> {
            return arrayOfNulls(size)
        }
    }

}