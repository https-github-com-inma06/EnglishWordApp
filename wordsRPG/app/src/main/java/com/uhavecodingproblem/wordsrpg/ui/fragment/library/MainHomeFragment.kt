package com.uhavecodingproblem.wordsrpg.ui.fragment.library

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMainHomeBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment


/**
 * wordsrpg
 * Class: MainHomeFragment.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 * 홈 프레그먼트입니다.
 */
class MainHomeFragment: BaseFragment<FragmentMainHomeBinding>(R.layout.fragment_main_home) {

    override fun FragmentMainHomeBinding.onCreateView() {
        Logger.v("실행")
    }

}
