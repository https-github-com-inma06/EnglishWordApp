package com.uhavecodingproblem.wordsrpg.ui.fragment.battle

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMainBattleBinding
import com.uhavecodingproblem.wordsrpg.ext.startActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment

/**
 * wordsrpg
 * Class: MainBattleFragment.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 * 배틀 프레그먼트입니다.
 *
 * DataBindingSetting by Loner
 *  Created On 2020-09-17.
 */

class MainBattleFragment : BaseFragment<FragmentMainBattleBinding>(R.layout.fragment_main_battle) {

    override fun FragmentMainBattleBinding.onCreateView() {

        btnPlayBattle.setOnClickListener {
            context?.startActivity(BattleUserListForInviteActivity::class.java)
        }

    }

    //BattlePlayActivity에서 배틀이 끝나고 온후
    //배틀이 진행중일때와 끝났을때를 분기하여 ui 처리 - 탭을 만들거나, 솔로런처럼 한 화면에 나누거나

    companion object {

        //기본은 진행중, 상대방의 결과가 나오면 서버에서 결과 보내줌
        const val WAITING = true
        const val FINISHED = true
    }
}







