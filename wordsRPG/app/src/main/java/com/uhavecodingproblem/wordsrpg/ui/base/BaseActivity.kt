package com.uhavecodingproblem.wordsrpg.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BaseActivity<VDB:ViewDataBinding>(@LayoutRes val layoutRes:Int):AppCompatActivity() {

    lateinit var binding:VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,layoutRes)
        binding.onCreate()
    }
    open fun VDB.onCreate() = Unit

}