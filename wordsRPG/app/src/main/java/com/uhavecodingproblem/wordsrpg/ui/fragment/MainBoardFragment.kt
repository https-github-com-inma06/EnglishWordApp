package com.uhavecodingproblem.wordsrpg.ui.fragment

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMainBoardBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment

/**
 * wordsrpg
 *
 * Class: MainBoardFragment.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 * 게시판 프레그먼트입니다.
 */
class MainBoardFragment : BaseFragment<FragmentMainBoardBinding>(R.layout.fragment_main_board) {

    override fun FragmentMainBoardBinding.onCreateView() {
        Logger.v("실행")
    }

}