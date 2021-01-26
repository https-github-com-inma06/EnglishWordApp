package com.uhavecodingproblem.wordsrpg.component.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.uhavecodingproblem.wordsrpg.api.ServerApi
import com.uhavecodingproblem.wordsrpg.data.PackageInformation
import com.uhavecodingproblem.wordsrpg.data.mockdata.WordMockData
import com.uhavecodingproblem.wordsrpg.util.Logger
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * wordsrpg
 * Class: LibraryItemViewModel
 * Created by pyg10.
 * Created On 2020-09-27.
 * Description:
 */
class PackageObserveViewModel(private val userId: String) : ViewModel() {

    val isLoading: MutableLiveData<Boolean> get() = _isLoading
    val typePackage: MutableLiveData<MutableList<PackageInformation>> get() = _typePackage

    private val allPackage: MutableList<PackageInformation> = WordMockData.wordMockData
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    private val _typePackage: MutableLiveData<MutableList<PackageInformation>> = MutableLiveData()
    private val _type: MutableLiveData<String> = MutableLiveData("수준별")

    init {
        loadPackage()
        observeType()
    }

    private fun loadPackage() {
        Logger.v("ViewModel loadPackage $userId")
        ServerApi.requestWordData(userId).enqueue(object : Callback<List<PackageInformation>> {
            override fun onResponse(call: Call<List<PackageInformation>>, response: Response<List<PackageInformation>>) {
                _isLoading.postValue(false)
            }

            override fun onFailure(call: Call<List<PackageInformation>>, t: Throwable) {

            }
        })
    }

    fun setType(type: String) {
        _type.postValue(type)
    }

    private fun observeType() {
        viewModelScope.launch {
            _type.asFlow().collect {
                _typePackage.postValue(allPackage.filter { packageInformation -> packageInformation.type == it }.toMutableList())
            }
        }
    }

    fun requestUpdatePackage() {
        loadPackage()
    }


    fun getPackage(name: String): PackageInformation {
        allPackage.find { it.name == name }?.let {
            return it
        }
        throw IllegalStateException("Not Found PackageName equals name")
    }

}