package com.uhavecodingproblem.wordsrpg.component.library.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.uhavecodingproblem.wordsrpg.api.ServerApi
import com.uhavecodingproblem.wordsrpg.data.PackageInformation
import com.uhavecodingproblem.wordsrpg.data.PackageRead
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

    //Loading 여부 true 로딩중 false 로딩 x
    val isLoading: MutableLiveData<Boolean> get() = _isLoading

    // 타입별로 분류된 패키지들(수준 or 시험)
    val typePackage: MutableLiveData<MutableList<PackageInformation>> get() = _typePackage

    // 전체 기본 패키지들
    private val allPackage: MutableList<PackageInformation> = WordMockData.wordMockData


    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    private val _typePackage: MutableLiveData<MutableList<PackageInformation>> = MutableLiveData()
    private val _type: MutableLiveData<String> = MutableLiveData("수준별")

    init {
        loadPackage()
        observeType()
    }

    private fun loadPackage() {
        _isLoading.postValue(false)
        val package_name = "테스트패키지네임"
        ServerApi.requestBasicPackage(package_name).enqueue(object : Callback<PackageRead> {
            override fun onResponse(call: Call<PackageRead>, response: Response<PackageRead>) {
                _isLoading.postValue(false)
                response.body()?.let {
                    Log.e("Read ::", it.toString())
                }
            }

            override fun onFailure(call: Call<PackageRead>, t: Throwable) {

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