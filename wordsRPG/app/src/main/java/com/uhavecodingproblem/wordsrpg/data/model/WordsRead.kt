package com.uhavecodingproblem.wordsrpg.data.model

import com.google.gson.annotations.SerializedName

/**
 * wordsrpg
 * Class: WordsRead
 * Created by pyg10.
 * Created On 2021-01-29.
 * Description:
 */

data class WordsRead(
                     @SerializedName("w_id") val wId: String,
                     @SerializedName("word") val word: String,
                     @SerializedName("mean") val mean: String,
                     @SerializedName("example") val example: String?,
                     @SerializedName("difficulty") val difficulty: String?)
