package com.uhavecodingproblem.wordsrpg.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * wordsrpg
 * Class: PackageRead
 * Created by pyg10.
 * Created On 2021-01-29.
 * Description:
 */
//패키지 테이블
data class Package(
    val p_id: String = "",
    val package_name: String = "",
    val package_difficulty: String = "", //
    val package_thumbnail: String = "",
    val total_word: String = "",
    val customCheck: String = "", //1일시 커스텀 패키지
    val customer_id: String? = "",
    val hashTagList: MutableList<String>? = null
):Serializable