package com.uhavecodingproblem.wordsrpg.data

import android.os.Parcel
import android.os.Parcelable

/**
 * wordsrpg
 * Class: Stage
 * Created by pyg10.
 * Created On 2020-10-23.
 * Description:
 */
data class Stage(
    val stageNum: Int, // 스테이지 구분자(단계)
    val stageStatus: Int, // 스테이지 상태( 0 : 학습도 테스트도보지않은 상태 1 : 학습중인상태 2 : 테스트 실패상태 3 : 테스트 통과)
    val stageBadgeCount: Int, // 테스트 통과시 뱃지(ex) star)표시 0 기본상태 아무것도 표시하지 않음 1: star 한개 또는 티어뱃지표기 최대 3까지 점수에 따라 1~3
    val words: MutableList<WordData>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(WordData.CREATOR) as MutableList<WordData>
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.run {
            writeInt(stageNum)
            writeInt(stageStatus)
            writeInt(stageBadgeCount)
            writeTypedList(words)
        }
    }

    companion object CREATOR : Parcelable.Creator<Stage> {
        override fun createFromParcel(parcel: Parcel): Stage {
            return Stage(parcel)
        }

        override fun newArray(size: Int): Array<Stage?> {
            return arrayOfNulls(size)
        }
    }
}