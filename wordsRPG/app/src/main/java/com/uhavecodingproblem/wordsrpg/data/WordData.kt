package com.uhavecodingproblem.wordsrpg.data

import android.os.Parcel
import android.os.Parcelable

/**
 * wordsrpg
 * Class: Word
 * Created by pyg10.
 * Created On 2020-10-05.
 * Description:
 */
data class WordData(val word: String, // 단어
                    val mean: String, // 의미
                    val isPassed: Boolean // 학습통과여부
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readInt() == 1
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.run {
            writeString(word)
            writeString(mean)
            writeInt(if (isPassed) 1 else 0)
        }
    }

    companion object CREATOR : Parcelable.Creator<WordData> {
        override fun createFromParcel(parcel: Parcel): WordData {
            return WordData(parcel)
        }

        override fun newArray(size: Int): Array<WordData?> {
            return arrayOfNulls(size)
        }
    }
}