package com.uhavecodingproblem.wordsrpg.component.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.tabs.TabItem

/**
 * wordsrpg
 * Class: MainLibraryViewModel
 * Created by pyg10.
 * Created On 2020-09-25.
 * Description:
 *
 * TabLayout ViewModel
 * MainLibrary use TabLayout
 * BasicLibrary use TabLayout?
 *
 */

class LibraryViewModel(tabName: List<String>) : ViewModel(){

    val tabItem: LiveData<List<String>> get() = _tabItem
    val position: LiveData<Int> get() = _position

    private val _tabItem: MutableLiveData<List<String>> = MutableLiveData()
    private val _position: MutableLiveData<Int> = MutableLiveData()

    private val TAB_ITEM = tabName

    init {
        _tabItem.postValue(TAB_ITEM)
    }

    fun selectPosition(position: Int){
        _position.postValue(position)
    }

}