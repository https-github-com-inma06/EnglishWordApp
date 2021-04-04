package com.uhavecodingproblem.wordsrpg.component.library.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.uhavecodingproblem.wordsrpg.api.ServerApi
import com.uhavecodingproblem.wordsrpg.data.model.Learning
import com.uhavecodingproblem.wordsrpg.data.model.Package
import com.uhavecodingproblem.wordsrpg.data.model.PackageWithStage
import com.uhavecodingproblem.wordsrpg.util.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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

    private var compositeDisposable : CompositeDisposable? = null

    init {
        _loading.postValue(true)
        loadBasicPackage()
        test()
    }

    private fun test(){
        compositeDisposable = CompositeDisposable()
        compositeDisposable?.let {
            it.add(ServerApi.requestTest("1").observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe ({response->
                response.basicPackage.forEach {
                        basic -> basic.stageList.forEach {
                        stage-> stage.wordList.forEach {
                        word-> Logger.d("${response.success} ${basic.packageID} ${stage.stageID} ${word.word}") } } }
            },{error->
                Logger.d(error.toString())
            }))
        }

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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable = null
    }
}