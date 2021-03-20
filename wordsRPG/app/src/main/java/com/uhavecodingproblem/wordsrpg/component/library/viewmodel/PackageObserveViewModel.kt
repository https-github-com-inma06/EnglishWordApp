package com.uhavecodingproblem.wordsrpg.component.library.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.uhavecodingproblem.wordsrpg.data.model.Learning
import com.uhavecodingproblem.wordsrpg.data.model.Package
import com.uhavecodingproblem.wordsrpg.data.model.PackageWithStage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * wordsrpg
 * Class: LibraryItemViewModel
 * Created by pyg10.
 * Created On 2020-09-27.
 * Description:
 */
class PackageObserveViewModel(private val u_id: String) : ViewModel() {

    private val firebaseDatabase= FirebaseDatabase.getInstance()
    private val firebaseStorage = FirebaseStorage.getInstance()

    val filteredBasicPackage get() = _filteredBasicPackage
    val loading get() = _loading

    private val _filteredBasicPackage = MutableLiveData<MutableList<PackageWithStage>>()
    private val _loading = MutableLiveData<Boolean>()

    private val basicPackageItem = mutableListOf<Package>()
    private val userLearningItem = mutableListOf<Learning>()

    init {
        _loading.postValue(true)
        loadBasicPackage()
    }

    private fun loadBasicPackage(){
        firebaseDatabase.reference.child("Package").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                basicPackageItem.clear()

                if (!isLoading())
                    _loading.postValue(true)

                for (childSnapshot in snapshot.children){
                    val data = childSnapshot.getValue(Package::class.java)
                    data?.let {packageData ->
                        if (packageData.customCheck == "0")
                            basicPackageItem.add(packageData)
                    }
                }
                loadLearning()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("packageLoadError", error.message)
                _loading.postValue(false)
            }
        })

    }

    private fun loadLearning(){
        firebaseDatabase.reference.child("Learning").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userLearningItem.clear()

                if (!isLoading())
                    _loading.postValue(true)

                for (childSnapshot in snapshot.children){
                    val data = childSnapshot.getValue(Learning::class.java)
                    data?.let {learning ->
                        if (learning.u_id == u_id)
                            userLearningItem.add(learning)
                    }
                }
                filterBasicPackage(basicPackageItem, userLearningItem)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("LearningLoadError", error.message)
                _loading.postValue(false)
            }
        })
    }

    private fun filterBasicPackage(basicPackageItem: MutableList<Package>, learningItem: MutableList<Learning>){

        val filter = mutableListOf<PackageWithStage>()

        basicPackageItem.forEach { basic ->
            val totalPackageCount = learningItem.filter { learning -> learning.p_id == basic.p_id}.size.toString()
            val clearPackageCount = learningItem.filter { learning -> learning.p_id == basic.p_id && learning.stage_status == "3"}.size.toString()
            filter.add(PackageWithStage(basic.p_id, basic.package_name, basic.package_thumbnail, totalPackageCount, clearPackageCount))
        }

        _filteredBasicPackage.postValue(filter)
        _loading.postValue(false)
    }

    fun filterStage(p_id: String) : MutableList<Learning>{
        return userLearningItem.filter { it.p_id == p_id }.toMutableList()
    }

    private fun isLoading(): Boolean{
        _loading.value?.let {
            return it
        }
        throw NullPointerException("Loading Value Null")
    }

}