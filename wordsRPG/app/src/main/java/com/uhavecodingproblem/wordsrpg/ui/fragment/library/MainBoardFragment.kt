package com.uhavecodingproblem.wordsrpg.ui.fragment.library

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMainBoardBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility

/**
 * wordsrpg
 *
 * Class: MainBoardFragment.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 * 게시판 프레그먼트입니다.
 */
class MainBoardFragment : BaseUtility.BaseFragment<FragmentMainBoardBinding>(R.layout.fragment_main_board) {

    override fun FragmentMainBoardBinding.onCreateView() {
        Logger.d("실행")
    }

}