package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * wordsrpg
 * Class: CorrectAnswerList
 * Created by pyg10.
 * Created On 2021-03-26.
 * Description:
 */
data class CorrectAnswer(val type: Int, val correct_answer: String) : Parcelable{
    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.run {
            writeInt(type)
            writeString(correct_answer)
        }
    }

    companion object CREATOR : Parcelable.Creator<CorrectAnswer> {
        override fun createFromParcel(parcel: Parcel): CorrectAnswer {
            return CorrectAnswer(parcel)
        }

        override fun newArray(size: Int): Array<CorrectAnswer?> {
            return arrayOfNulls(size)
        }
    }
}
