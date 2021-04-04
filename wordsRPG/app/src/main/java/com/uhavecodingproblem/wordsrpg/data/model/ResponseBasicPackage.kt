package com.uhavecodingproblem.wordsrpg.data.model

import com.google.gson.annotations.SerializedName

/**
 * wordsrpg
 * Class: BasicPackage
 * Created by pyg10.
 * Created On 2021-04-05.
 * Description:
 */
object ResponseBasicPackage {

    data class ResponseBasic(
        val success: Boolean,
        @SerializedName("package") var basicPackage: MutableList<BasicPackage>)

    data class BasicPackage(
        @SerializedName("p_id") val packageID: Int,
        @SerializedName("package_name") val packageName : String,
        @SerializedName("package_thumbnail") val packageThumbNail : String,
        @SerializedName("stage") val stageList: MutableList<Stage>
    )

    data class Stage(
        @SerializedName("s_id") val stageID : Int,
        @SerializedName("status") var stageStatus: String,
        @SerializedName("currentPage") var stageCurrentPage: Int,
        @SerializedName("score") var stageScore: Int,
        @SerializedName("word") val wordList: MutableList<Word>
    )

    data class Word(
        @SerializedName("w_id") val wordID: Int,
        @SerializedName("word") val word: String,
        @SerializedName("mean") val mean: String,
        @SerializedName("example") val wordExample: String?,
        @SerializedName("videoUri") val wordVideoUri: String?
    )
}