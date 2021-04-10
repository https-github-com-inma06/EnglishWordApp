package com.uhavecodingproblem.wordsrpg.room

import androidx.room.*
import com.uhavecodingproblem.wordsrpg.data.model.ResponseBasicPackage
import kotlinx.coroutines.flow.Flow

/**
 * wordsrpg
 * Class: RUCPDao
 * Created by pyg10.
 * Created On 2021-04-11.
 * Description:
 */

@Dao
interface RUCPDao {

    @Transaction
    @Query("SELECT * FROM BasicPackage")
    fun selectBasicPackage() : Flow<List<ResponseBasicPackage.BasicPackage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBasicPackage(basicPackage: List<ResponseBasicPackage.BasicPackage>)

}