package com.uhavecodingproblem.wordsrpg.component.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uhavecodingproblem.wordsrpg.api.ServerApi
import com.uhavecodingproblem.wordsrpg.data.PackageInformation
import com.uhavecodingproblem.wordsrpg.data.StageInformation
import com.uhavecodingproblem.wordsrpg.data.mockdata.WordMockData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.lang.IllegalStateException

/**
 * wordsrpg
 * Class: LibraryItemViewModel
 * Created by pyg10.
 * Created On 2020-09-27.
 * Description:
 */
class PackageObserveViewModel(private val userId: String) : ViewModel() {

    // val allWord: MutableList<WordType> get() = _allWord
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    private val allPackage: MutableList<PackageInformation> = WordMockData.wordMockData
    private val _isLoading: MutableLiveData<Boolean> get() = isLoading

    init {
        _isLoading.postValue(true)
        loadPackage()
    }

    private fun loadPackage() {
        ServerApi.requestWordData(userId).enqueue(object : Callback<PackageInformation> {
            override fun onResponse(call: Call<PackageInformation>, response: Response<PackageInformation>) {

            }

            override fun onFailure(call: Call<PackageInformation>, t: Throwable) {

            }
        })
    }

    fun requestUpdatePackage(){
        loadPackage()
    }

    fun byLevelPackage(): MutableList<PackageInformation> {
        val byLevelList = mutableListOf<PackageInformation>()
        for (i in allPackage.indices) {
            if (allPackage[i].type == "수준별")
                byLevelList.add(allPackage[i])
        }
        return byLevelList
    }

    fun byTestPackage(): MutableList<PackageInformation> {
        val byTestList = mutableListOf<PackageInformation>()
        for (i in allPackage.indices) {
            if (allPackage[i].type == "시험별")
                byTestList.add(allPackage[i])
        }
        return byTestList
    }

    fun getPackage(name: String): PackageInformation{
        for (i in allPackage.indices){
            if (allPackage[i].name == name)
                return allPackage[i]
        }
        throw IllegalStateException("Not Found PackageName equals name")
    }

}