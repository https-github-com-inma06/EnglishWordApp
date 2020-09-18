package com.uhavecodingproblem.wordsrpg.ui.fragment

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMyCustomPackageBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.util.Logger

/**
 * wordsrpg
 * Class: MyPackageFragment
 * Created by pyg10.
 * Created On 2020-09-18.
 * Description:
 */
class MyCustomPackageFragment: BaseFragment<FragmentMyCustomPackageBinding>(R.layout.fragment_my_custom_package) {

    override fun FragmentMyCustomPackageBinding.onCreateView() {
        Logger.v("실행")
    }
}