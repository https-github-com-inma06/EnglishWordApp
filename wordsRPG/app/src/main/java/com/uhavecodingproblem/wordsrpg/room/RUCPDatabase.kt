package com.uhavecodingproblem.wordsrpg.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uhavecodingproblem.wordsrpg.data.model.ResponseBasicPackage

/**
 * wordsrpg
 * Class: RUCPDatabase
 * Created by pyg10.
 * Created On 2021-04-11.
 * Description:
 */

@Database(entities = [ResponseBasicPackage.BasicPackage::class, ResponseBasicPackage.Stage::class, ResponseBasicPackage.Word::class], version = 1, exportSchema = false)
@TypeConverters(RUCPTypeConverters.BasicStageTypeConverters::class, RUCPTypeConverters.BasicWordTypeConverters::class)
abstract class RUCPDatabase : RoomDatabase() {

    abstract fun rucpDao(): RUCPDao

    companion object {
        @Volatile
        private var INSTANCE: RUCPDatabase? = null
        fun getInstance(context: Context): RUCPDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, RUCPDatabase::class.java, "RUCP").build()
                INSTANCE = instance
                instance
            }
        }
    }


}