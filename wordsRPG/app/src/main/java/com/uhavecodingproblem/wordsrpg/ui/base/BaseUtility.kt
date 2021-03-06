package com.uhavecodingproblem.wordsrpg.ui.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

/**
 * wordsrpg
 * Class: BaseUtility
 * Created by pyg10.
 * Created On 2021-01-27.
 * Description:
 */
sealed class BaseUtility {

    open class BaseActivity<VDB : ViewDataBinding>(@LayoutRes val layoutRes: Int) : AppCompatActivity() {

        lateinit var binding: VDB

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = DataBindingUtil.setContentView(this, layoutRes)
            binding.onCreate()
        }

        open fun VDB.onCreate() = Unit

    }

    open class BaseFragment<VDB : ViewDataBinding>(@LayoutRes val layoutRes: Int) : Fragment() {

        lateinit var binding: VDB
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = DataBindingUtil.inflate<VDB>(inflater, layoutRes, container, false)
            binding.onCreateView()
            return binding.root
        }

        open fun VDB.onCreateView() = Unit

    }

    open class BaseDialogFragment<VDB: ViewDataBinding>(@LayoutRes val layoutRes: Int): DialogFragment(){

        lateinit var binding: VDB

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
            binding.onCreateView()
            binding.onViewCreated()
            return binding.root
        }

        open fun VDB.onCreateView() = Unit
        open fun VDB.onViewCreated() = Unit
    }

    //TODO : 테스트안해봄
    open class BaseDialog<VDB: ViewDataBinding>(@LayoutRes val layoutRes: Int, private val dialogContext: Context): Dialog(dialogContext){

        lateinit var binding: VDB
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = DataBindingUtil.inflate(LayoutInflater.from(dialogContext), layoutRes, null, false)
            setContentView(binding.root)

            binding.onCreate()
        }

        open fun VDB.onCreate() = Unit
    }

}
