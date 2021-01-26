package com.uhavecodingproblem.wordsrpg.component.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uhavecodingproblem.wordsrpg.component.viewmodel.WordViewModel

/**
 * wordsrpg
 * Class: LibraryItemViewModelFactory
 * Created by pyg10.
 * Created On 2020-09-27.
 * Description:
 */
class WordViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = WordViewModel() as T
}