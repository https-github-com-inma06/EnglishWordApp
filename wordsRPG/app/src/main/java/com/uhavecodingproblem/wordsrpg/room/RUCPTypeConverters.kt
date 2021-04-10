package com.uhavecodingproblem.wordsrpg.room

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.uhavecodingproblem.wordsrpg.data.model.ResponseBasicPackage

/**
 * wordsrpg
 * Class: RUCPTypeConverters
 * Created by pyg10.
 * Created On 2021-04-11.
 * Description:
 */
object RUCPTypeConverters {

    class BasicStageTypeConverters{

        @TypeConverter
        fun stageListToJson(value: List<ResponseBasicPackage.Stage>?): String?{
            return value?.let { GsonBuilder().create().toJson(value) }
        }

        @TypeConverter
        fun stageJsonToList(value: String?): List<ResponseBasicPackage.Stage>?{
            return value?.let {
                GsonBuilder().create().fromJson(value, Array<ResponseBasicPackage.Stage>::class.java).toList()
            }
        }

    }

    class BasicWordTypeConverters{
        @TypeConverter
        fun wordListToJson(value: List<ResponseBasicPackage.Word>?): String?{
            return value?.let { GsonBuilder().create().toJson(value) }
        }

        @TypeConverter
        fun wordJsonToList(value: String?): List<ResponseBasicPackage.Word>?{
            return value?.let {
                GsonBuilder().create().fromJson(value, Array<ResponseBasicPackage.Word>::class.java).toList()
            }
        }
    }

}