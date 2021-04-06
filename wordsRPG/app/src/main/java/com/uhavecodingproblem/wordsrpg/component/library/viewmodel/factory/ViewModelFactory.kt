package com.uhavecodingproblem.wordsrpg.component.library.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.BasicPackageTabObserveViewModel
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.BasicPackageViewModel

/**
 * wordsrpg
 * Class: LibraryItemViewModelFactory
 * Created by pyg10.
 * Created On 2020-09-27.
 * Description:
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val userId: String, private val tabName: List<String>?) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(BasicPackageTabObserveViewModel::class.java)->{
                return BasicPackageTabObserveViewModel(tabName) as T
            }
            modelClass.isAssignableFrom(BasicPackageViewModel::class.java)->{
                return BasicPackageViewModel(userId) as T
            }
        }
        throw IllegalStateException("Unknown ViewModel Class")
    }
}