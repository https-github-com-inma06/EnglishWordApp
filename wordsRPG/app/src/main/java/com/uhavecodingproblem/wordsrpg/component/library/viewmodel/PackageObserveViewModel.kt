package com.uhavecodingproblem.wordsrpg.component.library.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.*
import com.uhavecodingproblem.wordsrpg.data.model.Learning
import com.uhavecodingproblem.wordsrpg.data.model.PackageRead
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    // 전체 기본 패키지들
    val packageData get() = _packageData
    val stageData get() = _stageData
    val currentStage get() = _currentStage

    private val _packageData = MutableLiveData<MutableList<PackageRead>>()
    private val _stageData = MutableLiveData<MutableList<Learning>>()
    private val _currentStage = MutableLiveData<MutableList<Learning>>() // 선택된 패키지의 스테이지

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = firebaseDatabase.reference

    init {
        loadPackage()
    }

    private fun loadPackage() = viewModelScope.launch(Dispatchers.IO) {

        databaseReference.child("Package").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allPackageItems = mutableListOf<PackageRead>()

                for (childSnapShot in snapshot.children) {
                    childSnapShot?.let {
                        Log.e("snapshot : ", it.toString())
                        val data = it.getValue(PackageRead::class.java)
                        data?.let { packageInformation ->
                            allPackageItems.add(packageInformation)
                        }
                    }
                }

                _packageData.postValue(allPackageItems)
                loadStage(userId)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Load Package Error", error.message)
                _isLoading.postValue(false)
            }
        })
    }

    private fun loadStage(u_id: String) = viewModelScope.launch(Dispatchers.IO){
        databaseReference.child("Learning").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val stageItems = mutableListOf<Learning>()
                for (childSnapShot in snapshot.children){
                    childSnapShot?.let {
                        val data = it.getValue(Learning::class.java)
                        data?.let { stage->
                            stageItems.add(stage)
                        }
                    }
                }

                _stageData.postValue(stageItems.filter { Learning -> Learning.u_id == u_id}.toMutableList())
                _isLoading.postValue(false)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error:", "Load Stage Error")
                _isLoading.postValue(false)
            }
        })
    }

    fun selectedPackage(p_id: String){
        _stageData.value?.let {
            _currentStage.postValue(it.filter { Learning -> Learning.p_id == p_id }.toMutableList())
        }
    }

}