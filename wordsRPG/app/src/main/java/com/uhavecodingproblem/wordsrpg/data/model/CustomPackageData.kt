package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * wordsrpg
 * Class: CustomPackageData.
 * Created by leedonghun.
 * Created On 2020-09-25.
 * Description:
 * 커스텀 패키지 데이터 클래스
 */

data class CustomPackageData(
   val c_id: String,
   val ownerName: String,
   val packageName: String,
   val profileImageUrl: String?,
   val likeCount: Int,
   val subscribeCount: Int,
   val hashTagList: MutableList<String> //Variable Name Change :: hashList -> hashTagList. fixed by atanasio.
) : Parcelable {
    constructor(parcel: Parcel) : this(
       parcel.readString() ?: "",
       parcel.readString() ?: "",
       parcel.readString() ?: "",
       parcel.readString() ?: "",
       parcel.readInt(),
       parcel.readInt(),
       parcel.createStringArrayList() as MutableList<String>
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            it.writeString(c_id)
            it.writeString(ownerName)
            it.writeString(packageName)
            it.writeString(profileImageUrl)
            it.writeInt(likeCount)
            it.writeInt(subscribeCount)
            it.writeStringList(hashTagList)
        }
    }

    companion object CREATOR : Parcelable.Creator<CustomPackageData> {
        override fun createFromParcel(parcel: Parcel): CustomPackageData {
            return CustomPackageData(parcel)
        }

        override fun newArray(size: Int): Array<CustomPackageData?> {
            return arrayOfNulls(size)
        }
    }
}