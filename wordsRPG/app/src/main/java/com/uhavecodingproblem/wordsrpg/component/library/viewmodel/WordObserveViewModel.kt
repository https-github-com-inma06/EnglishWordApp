package com.uhavecodingproblem.wordsrpg.component.library.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.uhavecodingproblem.wordsrpg.data.model.Learning
import com.uhavecodingproblem.wordsrpg.data.model.PackageWord
import com.uhavecodingproblem.wordsrpg.data.model.WordsRead
import com.uhavecodingproblem.wordsrpg.util.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * wordsrpg
 * Class: WordObserveViewModel
 * Created by pyg10.
 * Created On 2021-03-15.
 * Description:
 */
class WordObserveViewModel : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference

    val loading get() = _loading
    val wordList get() = _wordList

    private val _loading = MutableLiveData<Boolean>(true)
    private val _wordList = MutableLiveData<MutableList<WordsRead>>()

    fun load(p_id: String, s_id: String) = viewModelScope.launch(Dispatchers.IO){

        val packageWordItem = loadWordLink(p_id, s_id)
        loadWords(packageWordItem)

        _loading.postValue(false)
    }

    private suspend fun loadWordLink(p_id: String, s_id: String): MutableList<PackageWord> = suspendCancellableCoroutine {
        val packageWordItem = mutableListOf<PackageWord>()
        databaseReference.child("PackageWord").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children) {
                    childSnapshot?.let {
                        val data = it.getValue(PackageWord::class.java)
                        data?.let { packageWord ->
                            if (packageWord.p_id == p_id && packageWord.s_id == s_id)
                                packageWordItem.add(packageWord)
                        }
                    }
                }
                it.resume(packageWordItem)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("loadWordLink Error", error.message)
                _loading.postValue(false)
                it.cancel()
            }
        })
    }

    private suspend fun loadWords(packageWordItem: MutableList<PackageWord>) = suspendCancellableCoroutine<Unit> {
        databaseReference.child("Word").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val wordItem = mutableListOf<WordsRead>()
                for (childSnapshot in snapshot.children) {
                    childSnapshot?.let {
                        val data = it.getValue(WordsRead::class.java)
                        data?.let { item ->
                            packageWordItem.find { link -> link.w_id == item.w_id }?.let {
                                wordItem.add(item)
                            }
                        }
                    }
                }
                _wordList.postValue(wordItem)
                it.resume(Unit)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("loadWords Error", error.message)
                _loading.postValue(false)
                it.cancel()
            }
        })
    }

    fun updateLearning(l_id: String, learning: Learning, currentPage: Int) {
        databaseReference.child("Learning").orderByChild("l_id").equalTo(l_id)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (childSnapshot in snapshot.children) {
                        childSnapshot?.key?.let {
                            val param = HashMap<String, String>()
                            param["l_id"] = learning.l_id
                            param["s_id"] = learning.s_id
                            param["u_id"] = learning.u_id
                            param["p_id"] = learning.p_id
                            param["total_page"] = learning.total_page
                            param["current_page"] = currentPage.toString()
                            if (learning.stage_status == "0") {
                                param["stage_status"] = "1"
                            } else {
                                param["stage_status"] = learning.stage_status
                            }
                            databaseReference.child("Learning").child(it).setValue(param).addOnSuccessListener {
                                Logger.d("updateLearning Success")
                            }.addOnFailureListener { exception ->
                                Logger.d("updateLearning Failure : $exception")
                            }
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Logger.d(error.message)
                }
            })
    }

}