package com.uhavecodingproblem.wordsrpg.data

import android.os.Parcel
import android.os.Parcelable

/**
 * wordsrpg
 * Class: ByLevelLibraryItem
 * Created by pyg10.
 * Created On 2020-10-02.
 * Description:
 */

data class WordType(
    val type: String, // 수준별, 시험별, 카테고리별..
    val name: String, // 초등학교 1학년, 중학교 1학년, 고등학교 1학년, 토익 등등
    val thumbnailImage: String, // 배경이미지
    val stage: MutableList<Stage> // 단어들
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString()?: "",
        parcel.createTypedArrayList(Stage.CREATOR) as MutableList<Stage>
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.run {
            writeString(type)
            writeString(name)
            writeString(thumbnailImage)
            writeTypedList(stage)
        }
    }

    companion object CREATOR : Parcelable.Creator<WordType> {
        override fun createFromParcel(parcel: Parcel): WordType {
            return WordType(parcel)
        }

        override fun newArray(size: Int): Array<WordType?> {
            return arrayOfNulls(size)
        }
    }

}