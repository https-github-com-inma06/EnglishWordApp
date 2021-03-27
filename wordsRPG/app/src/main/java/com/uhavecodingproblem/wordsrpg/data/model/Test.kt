package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable
import com.uhavecodingproblem.wordsrpg.data.mockdata.StageInformation

/**
 * wordsrpg
 * Class: Test
 * Created by pyg10.
 * Created On 2021-03-23.
 * Description:
 */
data class Test(val index: String, val question: Question, var example: MutableList<Example>) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readValue(Question::class.java.classLoader) as Question,
        parcel.createTypedArrayList(Example) as MutableList<Example>
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.run {
            writeString(index)
            writeValue(question)
            writeTypedList(example)
        }
    }

    companion object CREATOR : Parcelable.Creator<Test> {
        override fun createFromParcel(parcel: Parcel): Test {
            return Test(parcel)
        }

        override fun newArray(size: Int): Array<Test?> {
            return arrayOfNulls(size)
        }
    }
}
