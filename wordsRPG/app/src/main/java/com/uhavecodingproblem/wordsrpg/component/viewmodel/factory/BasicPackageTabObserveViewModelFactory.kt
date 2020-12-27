package com.uhavecodingproblem.wordsrpg.component.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uhavecodingproblem.wordsrpg.component.viewmodel.BasicPackageTabObserveViewModel

/**
 * wordsrpg
 * Class: ViewModelFactory
 * Created by pyg10.
 * Created On 2020-09-25.
 * Description:
 * ViewModel 매개변수 넘기기 위해서 사용
 */

class BasicPackageTabObserveViewModelFactory(private val tabName: List<String>): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = BasicPackageTabObserveViewModel(tabName) as T
}