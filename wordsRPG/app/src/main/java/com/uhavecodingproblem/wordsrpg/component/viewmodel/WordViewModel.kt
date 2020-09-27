package com.uhavecodingproblem.wordsrpg.component.viewmodel

import androidx.lifecycle.ViewModel
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
class WordViewModel: ViewModel() {

    private val basicWord = listOf("Apple", "Banana", "Circle")

    /**
     * val basicWord: LiveData<List<Word>> get() = _basicWord
     * private val _basicWord: MutableLiveData<List<Word>> = MutableLiveData()
     */

    init {
        getAllWord()
    }

    private fun getAllWord(){
        CoroutineScope(Dispatchers.IO).launch {

        }
    }


    fun getByLevelWord(): List<String>{
        return basicWord
    }

    fun getByTestWord(){

    }

    fun getByCategoryWord(){

    }

}