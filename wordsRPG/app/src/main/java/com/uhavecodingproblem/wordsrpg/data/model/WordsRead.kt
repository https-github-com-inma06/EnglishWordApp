package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * wordsrpg
 * Class: WordsRead
 * Created by pyg10.
 * Created On 2021-01-29.
 * Description:
 */

data class WordsRead(
    val w_id: String = "",
    val word: String = "",
    val mean: String = "",
    val example: String? = null,
    val difficulty: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            it.writeString(w_id)
            it.writeString(word)
            it.writeString(mean)
            it.writeString(example)
            it.writeString(difficulty)
        }
    }

    companion object CREATOR : Parcelable.Creator<WordsRead> {
        override fun createFromParcel(parcel: Parcel): WordsRead {
            return WordsRead(parcel)
        }

        override fun newArray(size: Int): Array<WordsRead?> {
            return arrayOfNulls(size)
        }
    }
}
