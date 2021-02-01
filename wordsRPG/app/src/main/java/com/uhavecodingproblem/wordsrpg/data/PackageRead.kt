package com.uhavecodingproblem.wordsrpg.data

import com.google.gson.annotations.SerializedName

/**
 * wordsrpg
 * Class: PackageRead
 * Created by pyg10.
 * Created On 2021-01-29.
 * Description:
 */
data class PackageRead(@SerializedName("success") val success: Boolean,
                       @SerializedName("p_id") val pId: String,
                       @SerializedName("package_name") val packageName: String,
                       @SerializedName("package_difficulty") val packageDifficulty: String,
                       @SerializedName("package_thumbNail") val packageThumbNail: String,
                       @SerializedName("total_word") val totalWord: String?,
                       @SerializedName("is_custom") val isCustom: String?,
                       @SerializedName("customer_id") val customerId: String?,
                       @SerializedName("words") val words: List<WordsRead>)
