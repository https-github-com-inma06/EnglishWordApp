package com.uhavecodingproblem.wordsrpg.data.mockdata

import android.os.Parcel
import android.os.Parcelable

/**
 * wordsrpg
 * Class: Word
 * Created by pyg10.
 * Created On 2020-10-05.
 * Description:
 */
data class WordInformation(
    val word: String, // 단어
    val mean: String, // 의미
    val example: String, // 예문
    var isStudyPassed: Boolean, // 학습통과여부
    var isTestPassed: Boolean // 테스트 통과여부(추후 오답노트를 위한 체크)
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readInt() == 1,
        parcel.readInt() == 1
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.run {
            writeString(word)
            writeString(mean)
            writeString(example)
            writeInt(if (isStudyPassed) 1 else 0)
            writeInt(if (isTestPassed) 1 else 0)
        }
    }

    companion object CREATOR : Parcelable.Creator<WordInformation> {
        override fun createFromParcel(parcel: Parcel): WordInformation {
            return WordInformation(parcel)
        }

        override fun newArray(size: Int): Array<WordInformation?> {
            return arrayOfNulls(size)
        }
    }
}