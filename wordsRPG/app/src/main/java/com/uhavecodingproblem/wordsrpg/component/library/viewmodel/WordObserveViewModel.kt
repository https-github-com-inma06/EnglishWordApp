package com.uhavecodingproblem.wordsrpg.component.library.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.uhavecodingproblem.wordsrpg.api.ServerApi
import com.uhavecodingproblem.wordsrpg.data.model.Learning
import com.uhavecodingproblem.wordsrpg.data.model.PackageWord
import com.uhavecodingproblem.wordsrpg.data.model.RequestTest
import com.uhavecodingproblem.wordsrpg.data.model.WordsRead
import com.uhavecodingproblem.wordsrpg.util.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

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

    private var compositeDisposable: CompositeDisposable? = null

    init {
        compositeDisposable = CompositeDisposable()
    }


    fun loadWordLink(p_id: String, s_id: String, isTest: Boolean) {
        databaseReference.child("PackageWord").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val packageWordItem = mutableListOf<PackageWord>()

                for (childSnapshot in snapshot.children) {
                    childSnapshot?.let {
                        val data = it.getValue(PackageWord::class.java)
                        data?.let { packageWord ->
                            if (packageWord.p_id == p_id && packageWord.s_id == s_id)
                                packageWordItem.add(packageWord)
                        }
                    }
                }
                loadWords(packageWordItem, isTest)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("loadWordLink Error", error.message)
                _loading.postValue(false)
            }
        })
    }

    private fun loadWords(packageWordItem: MutableList<PackageWord>, isTest: Boolean) {
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

                if (isTest) {
                    val requestTestItem = mutableListOf<RequestTest>()
                    val pId = packageWordItem[0].p_id
                    wordItem.forEach {
                        requestTestItem.add(
                            RequestTest(
                                if (requestTestItem.isNullOrEmpty()) {
                                    "1" // 파라미터['idx'] String 변경
                                } else {
                                    (requestTestItem.size + 1).toString() // 파라미터['idx'] String 변경
                                },
                                pId,
                                it.w_id
                            )
                        )
                    }
                    requestTest(requestTestItem)
                } else
                    _loading.postValue(false)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("loadWords Error", error.message)
                _loading.postValue(false)
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

    private fun requestTest(requestTest: List<RequestTest>) {


        requestTest.forEach {
            Logger.d("${it.idx} ${it.idx} ${it.w_id}")
        }
        compositeDisposable?.let {
            it.add(
                //io Thread 에서 requestTest 실행 = subscribeOn
                //doOnTerminate 는 subscribe 결과가 오면 해당 스코프 실행
                //통신 결과는 main Thread 에서 실행 = observeOn
                //subscribe 결과는 성공과 실패로 오게됨 성공시 success 실패시 error
                ServerApi.requestTest(requestTest).subscribeOn(Schedulers.io()).doOnTerminate { _loading.postValue(false) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ success ->
                        //success = ResponseTest
                        success.test.forEach { test ->
                            //test = mutableList<Example>
                            test.example.forEach { example ->
                                Logger.d("${example.example_word} ${example.example_mean}")
                            }
                        }

                    }, { error ->
                        Logger.d("error 에러 :: $error")
                    })
            )
        }


    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable = null
    }
}