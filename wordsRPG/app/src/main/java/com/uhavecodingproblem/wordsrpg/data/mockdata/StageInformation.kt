package com.uhavecodingproblem.wordsrpg.data.mockdata

import android.os.Parcel
import android.os.Parcelable

/**
 * wordsrpg
 * Class: Stage
 * Created by pyg10.
 * Created On 2020-10-23.
 * Description:
 */


data class StageInformation(
    val stageNum: Int, // 스테이지 구분자(단계)
    var stageStatus: Int, // 스테이지 상태( 0 : 학습도 테스트도보지않은 상태 1 : 학습중인상태 2 : 테스트 실패상태 3 : 테스트 통과)
    var wordList: MutableList<WordInformation>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(WordInformation) as MutableList<WordInformation>
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.run {
            writeInt(stageNum)
            writeInt(stageStatus)
            writeTypedList(wordList)
        }
    }

    companion object CREATOR : Parcelable.Creator<StageInformation> {
        override fun createFromParcel(parcel: Parcel): StageInformation {
            return StageInformation(parcel)
        }

        override fun newArray(size: Int): Array<StageInformation?> {
            return arrayOfNulls(size)
        }
    }
}