package com.uhavecodingproblem.wordsrpg.component.library.viewmodel.repository

import androidx.annotation.WorkerThread
import com.uhavecodingproblem.wordsrpg.data.model.ResponseBasicPackage
import com.uhavecodingproblem.wordsrpg.room.RUCPDao

/**
 * wordsrpg
 * Class: RUCPRepository
 * Created by pyg10.
 * Created On 2021-04-11.
 * Description:
 */
class RUCPRepository(private val rucpDao: RUCPDao) {

    val selectBasicPackage = rucpDao.selectBasicPackage()

    @WorkerThread
    suspend fun insertBasicPackage(basicPackage: List<ResponseBasicPackage.BasicPackage>){
        rucpDao.insertBasicPackage(basicPackage)
    }

}