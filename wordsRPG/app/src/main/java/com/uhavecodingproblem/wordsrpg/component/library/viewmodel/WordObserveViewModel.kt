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
import kotlinx.coroutines.*

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

    val isLoading get() = _isLoading
    val wordList get() = _wordList

    private val _isLoading = MutableLiveData<Boolean>(true)
    private val _wordList = MutableLiveData<MutableList<WordsRead>>()

    private val wordLink = mutableListOf<PackageWord>()

    fun loadWordLink(p_id: String, s_id: String) = viewModelScope.launch(Dispatchers.IO) {
        databaseReference.child("PackageWord").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                wordLink.clear()
                for (childSnapshot in snapshot.children) {
                    childSnapshot?.let {
                        val data = it.getValue(PackageWord::class.java)
                        data?.let { packageWord ->
                            if (packageWord.p_id == p_id && packageWord.s_id == s_id)
                                wordLink.add(packageWord)
                        }
                    }
                }

                loadWords()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("loadWordLink Error", error.message)
                _isLoading.postValue(false)
            }
        })
    }

    private fun loadWords() = viewModelScope.launch(Dispatchers.IO) {
        databaseReference.child("Word").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val wordItem = mutableListOf<WordsRead>()
                for (childSnapshot in snapshot.children) {
                    childSnapshot?.let {
                        val data = it.getValue(WordsRead::class.java)
                        data?.let { item ->
                            wordLink.find { link -> link.w_id == item.w_id }?.let {
                                wordItem.add(item)
                            }
                        }
                    }
                }

                _wordList.postValue(wordItem)

                _isLoading.postValue(false)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("loadWords Error", error.message)
                _isLoading.postValue(false)
            }
        })
    }

    fun updateLearning(l_id: String, learning: Learning, currentPage: Int) {
        databaseReference.child("Learning").orderByChild("l_id").equalTo(l_id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children) {
                    childSnapshot?.key?.let {
                        Log.e("key", it)
                        val param = hashMapOf(
                            "l_id" to learning.l_id,
                            "s_id" to learning.s_id,
                            "u_id" to learning.u_id,
                            "p_id" to learning.p_id,
                            "total_page" to learning.total_page,
                            "stage_status" to "1",
                            "current_page" to currentPage.toString()
                        )
                        databaseReference.child("Learning").child(it).setValue(param).addOnSuccessListener {
                            Logger.e("updateLearning Success")
                        }.addOnFailureListener { exception ->
                            Logger.e("updateLearning Failure : $exception")
                        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Logger.e(error.message)
            }
        })
    }

}