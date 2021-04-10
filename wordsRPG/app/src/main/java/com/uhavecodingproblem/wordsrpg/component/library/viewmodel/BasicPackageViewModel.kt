package com.uhavecodingproblem.wordsrpg.component.library.viewmodel

import androidx.lifecycle.*
import com.uhavecodingproblem.wordsrpg.api.ServerApi
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.repository.RUCPRepository
import com.uhavecodingproblem.wordsrpg.data.model.ResponseBasicPackage
import com.uhavecodingproblem.wordsrpg.util.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * wordsrpg
 * Class: BasicPackageViewModel
 * Created by pyg10.
 * Created On 2021-04-05.
 * Description:
 */
class BasicPackageViewModel(private val user_id: String, private val repository: RUCPRepository) : ViewModel() {

    private var compositeDisposable: CompositeDisposable? = null

    val loading: LiveData<Boolean> get() = _loading
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()

    val selectBasicPackage: LiveData<List<ResponseBasicPackage.BasicPackage>> get() = _selectBasicPackage
    private val _selectBasicPackage: MutableLiveData<List<ResponseBasicPackage.BasicPackage>> = MutableLiveData()

    private val _basicPackage: MutableLiveData<ResponseBasicPackage.ResponseBasic> = MutableLiveData()

    init {
        compositeDisposable = CompositeDisposable()
        loadBasicPackage()
        selectBasicPackage()
    }

    private fun loadBasicPackage() {
        compositeDisposable?.let {
            it.add(
                ServerApi.requestBasicPackage(user_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        _loading.postValue(true)
                    }.subscribe({ success ->
                        _basicPackage.postValue(success)
                    }, { throwable ->
                        Logger.d("$throwable")
                        _loading.postValue(false)
                    })
            )
        }
    }

    private fun selectBasicPackage() {
        viewModelScope.launch(Dispatchers.IO) {
            _basicPackage.asFlow().collect {
                _selectBasicPackage.postValue(it.basicPackage)
                _loading.postValue(false)
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.dispose()
        compositeDisposable = null
    }
}