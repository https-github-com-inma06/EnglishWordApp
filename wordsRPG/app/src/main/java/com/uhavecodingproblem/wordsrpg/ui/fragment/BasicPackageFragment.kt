package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.content.Intent
import android.util.Log
import android.view.View
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.FragmentBasicPackageBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.LibraryActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.util.Logger

/**
 * wordsrpg
 * Class: BasicPackageFragment
 * Created by pyg10.
 * Created On 2020-09-18.
 * Description:
 */
class BasicPackageFragment : BaseFragment<FragmentBasicPackageBinding>(R.layout.fragment_basic_package) {

    override fun FragmentBasicPackageBinding.onCreateView() {
        Logger.v("실행")
        binding.basicFragment = this@BasicPackageFragment
    }

    fun moveLibrary(v: View){
        Intent(requireActivity(), LibraryActivity::class.java).also{
            startActivity(it)
        }
    }
}