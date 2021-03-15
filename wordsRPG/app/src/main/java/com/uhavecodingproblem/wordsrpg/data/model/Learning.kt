package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable

//학습 테이블
data class Learning(
    //학습 고유값
    val l_id:String = "",
    //유저 고유값
    val u_id:String = "",
    //패키지 고유값
    val p_id:String = "",
    //스테이지 고유값
    val s_id:String = "",
    //현재 페이지(0 이라면 학습중이지 않은상태)
    var current_page: String = "",
    //스테이지 총 페이지수
    val total_page:String="",
    //스테이지의 상태
    var stage_status:String=""
): Parcelable{
    constructor(parcel: Parcel): this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.run {
            writeString(l_id)
            writeString(u_id)
            writeString(p_id)
            writeString(s_id)
            writeString(current_page)
            writeString(total_page)
            writeString(stage_status)
        }
    }

    companion object CREATOR : Parcelable.Creator<Learning> {
        override fun createFromParcel(parcel: Parcel): Learning {
            return Learning(parcel)
        }

        override fun newArray(size: Int): Array<Learning?> {
            return arrayOfNulls(size)
        }
    }
}
