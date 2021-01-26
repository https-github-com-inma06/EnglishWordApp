package com.uhavecodingproblem.wordsrpg.component.library.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.PackageObserveViewModel

/**
 * wordsrpg
 * Class: LibraryItemViewModelFactory
 * Created by pyg10.
 * Created On 2020-09-27.
 * Description:
 */
class PackageObserveViewModelFactory(private val userId: String) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = PackageObserveViewModel(userId) as T
}