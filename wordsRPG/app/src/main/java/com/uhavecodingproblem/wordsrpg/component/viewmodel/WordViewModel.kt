package com.uhavecodingproblem.wordsrpg.component.viewmodel

import androidx.lifecycle.ViewModel
import com.uhavecodingproblem.wordsrpg.data.PackageInformation
import com.uhavecodingproblem.wordsrpg.data.mockdata.WordMockData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * wordsrpg
 * Class: LibraryItemViewModel
 * Created by pyg10.
 * Created On 2020-09-27.
 * Description:
 */
class WordViewModel : ViewModel() {

    // val allWord: MutableList<WordType> get() = _allWord

    private val allWord: MutableList<PackageInformation> = WordMockData.wordMockData

    init {
        getAllWord()
    }

    private fun getAllWord() {
        CoroutineScope(Dispatchers.IO).launch {

        }
    }

    fun getByLevelWord(): MutableList<PackageInformation> {
        val byLevelList = mutableListOf<PackageInformation>()
        for (i in allWord.indices) {
            if (allWord[i].type == "수준별")
                byLevelList.add(allWord[i])
        }
        return byLevelList
    }

    fun getByTestWord(): MutableList<PackageInformation> {
        val byTestList = mutableListOf<PackageInformation>()
        for (i in allWord.indices) {
            if (allWord[i].type == "시험별")
                byTestList.add(allWord[i])
        }
        return byTestList
    }

    fun getByCategoryWord() {

    }

}