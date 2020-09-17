package com.uhavecodingproblem.wordsrpg.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * wordsrpg
 * Class: BaseFragment.
 * Created by leedonghun.
 * Created On 2020-09-17.
 * Description:
 */
open class BaseFragment<VDB:ViewDataBinding>(@LayoutRes val layoutRes:Int): Fragment() {

 lateinit var binding: VDB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =DataBindingUtil.inflate<VDB>(inflater,layoutRes,container,false).run{
        binding = this
        onCreateView()

        root
    }

    open fun VDB.onCreateView() = Unit


}