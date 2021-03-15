package com.uhavecodingproblem.wordsrpg.component.library.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.*
import com.uhavecodingproblem.wordsrpg.data.model.Learning
import com.uhavecodingproblem.wordsrpg.data.model.Package
import com.uhavecodingproblem.wordsrpg.data.model.PackageWithStage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * wordsrpg
 * Class: LibraryItemViewModel
 * Created by pyg10.
 * Created On 2020-09-27.
 * Description:
 */
class PackageObserveViewModel(private val userId: String?) : ViewModel() {

    //Loading 여부 true 로딩중 false 로딩 x
    val isLoading: MutableLiveData<Boolean> get() = _isLoading

    // 전체 기본 패키지들
    val basicPackageInformation get() = _basicPackageInformation

    private val basicPackageData = mutableListOf<Package>()
    private val stageData = mutableListOf<Learning>()
    private val currentStage = mutableListOf<Learning>() // 선택된 패키지의 스테이지
    private val _basicPackageInformation = MutableLiveData<MutableList<PackageWithStage>>()

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)


    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = firebaseDatabase.reference

    init {
        loadPackage()
    }

    private fun loadPackage() = viewModelScope.launch(Dispatchers.IO) {

        databaseReference.child("Package").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                basicPackageData.clear()

                for (childSnapShot in snapshot.children) {
                    childSnapShot?.let {
                        val data = it.getValue(Package::class.java)
                        data?.let { packageInformation ->
                            if (packageInformation.customCheck == "0")
                                basicPackageData.add(packageInformation)
                        }
                    }
                }

                userId?.let {
                    loadStage(it)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Load Package Error", error.message)
                _isLoading.postValue(false)
            }
        })
    }

    private fun loadStage(u_id: String) = viewModelScope.launch(Dispatchers.IO) {
        databaseReference.child("Learning").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val basicPackageWithStage = mutableListOf<PackageWithStage>()
                stageData.clear()

                for (childSnapShot in snapshot.children) {
                    childSnapShot?.let {
                        val data = it.getValue(Learning::class.java)
                        data?.let { stage ->
                            if (stage.u_id == u_id)
                                stageData.add(stage)
                        }
                    }
                }

                for (i in basicPackageData.indices) {
                    val totalStage = stageData.filter { it.p_id == basicPackageData[i].p_id }.toMutableList().size.toString()
                    val clearStage = stageData.filter { it.p_id == basicPackageData[i].p_id && it.stage_status == "3" }
                        .toMutableList().size.toString()
                    basicPackageWithStage.add(
                        PackageWithStage(
                            basicPackageData[i].p_id,
                            basicPackageData[i].package_name,
                            basicPackageData[i].package_thumbnail,
                            totalStage,
                            clearStage
                        )
                    )
                }

                _basicPackageInformation.postValue(basicPackageWithStage)
                _isLoading.postValue(false)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error:", "Load Stage Error")
                _isLoading.postValue(false)
            }
        })
    }

    fun selectedPackage(p_id: String): MutableList<Learning> {
        currentStage.clear()
        stageData.forEach { if (it.p_id == p_id) currentStage.add(it) }
        return currentStage
    }

}