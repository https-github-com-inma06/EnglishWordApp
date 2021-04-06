package com.uhavecodingproblem.wordsrpg.component.library.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uhavecodingproblem.wordsrpg.api.ServerApi
import com.uhavecodingproblem.wordsrpg.data.model.ResponseBasicPackage
import com.uhavecodingproblem.wordsrpg.util.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * wordsrpg
 * Class: BasicPackageViewModel
 * Created by pyg10.
 * Created On 2021-04-05.
 * Description:
 */
class BasicPackageViewModel(private val user_id: String) : ViewModel() {

    private var compositeDisposable: CompositeDisposable? = null

    val loading: LiveData<Boolean> get() = _loading
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()

    val basicPackage: LiveData<ResponseBasicPackage.ResponseBasic> get() = _basicPackage
    private val _basicPackage: MutableLiveData<ResponseBasicPackage.ResponseBasic> = MutableLiveData()

    init {
        compositeDisposable = CompositeDisposable()
        loadBasicPackage()
    }

    private fun loadBasicPackage() {
        compositeDisposable?.let {
            it.add(
                ServerApi.requestBasicPackage(user_id).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        _loading.postValue(true)
                    }.doOnTerminate {
                        _loading.postValue(false)
                    }
                    .subscribe({success->
                        _basicPackage.postValue(success)
                    }, {throwable->
                        Logger.d("$throwable")
                    })
            )
        }
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable = null
    }
}