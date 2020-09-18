package com.uhavecodingproblem.wordsrpg.ui.fragment

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.FragmentSubscribePackageBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.util.Logger

/**
 * wordsrpg
 * Class: SubscribePackageFragment
 * Created by pyg10.
 * Created On 2020-09-18.
 * Description:
 */
class SubscribePackageFragment: BaseFragment<FragmentSubscribePackageBinding>(R.layout.fragment_subscribe_package) {

    override fun FragmentSubscribePackageBinding.onCreateView() {
        Logger.v("실행")
    }
}