package com.uhavecodingproblem.wordsrpg.ui.fragment

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMainLibraryBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment


/**
 * wordsrpg
 * Class: MainLibraryFragment.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 * 라이브러리 프레그먼트입니다.
 */
class MainLibraryFragment : BaseFragment<FragmentMainLibraryBinding>(R.layout.fragment_main_library) {

    override fun FragmentMainLibraryBinding.onCreateView() {
        Logger.v("실행")
    }

}