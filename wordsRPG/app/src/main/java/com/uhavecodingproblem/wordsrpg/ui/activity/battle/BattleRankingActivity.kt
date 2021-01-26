package com.uhavecodingproblem.wordsrpg.ui.activity.battle

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.battle.BattleRankingListAdapter
import com.uhavecodingproblem.wordsrpg.data.model.User
import com.uhavecodingproblem.wordsrpg.databinding.ActivityBattleRankingBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import org.koin.android.ext.android.bind


class BattleRankingActivity : BaseActivity<ActivityBattleRankingBinding>(R.layout.activity_battle_ranking) {

    /** todo: 서버 완성후 모데롤 묶어서 심플하게 변경할 에정. */


    override fun ActivityBattleRankingBinding.onCreate() {
        val user = setMuckData()
        val myRank = setAdapter(user!!)
        thisUser = user
        binding.myRanking = myRank
    }

   
    private fun setMuckData() = intent.getParcelableExtra<User>("currentUser")

    private fun setAdapter(user: User): Int {
        val friendAndMeList = mutableListOf<User>()
        user.friendList?.let { friendAndMeList.addAll(it) }
        friendAndMeList.add(user)
        friendAndMeList.sortBy { it.score }
        friendAndMeList.reverse()
        val myRank = friendAndMeList.indexOf(user) + 1
        friendAndMeList.remove(user)

        binding.apply {
            rvRankingList.adapter = BattleRankingListAdapter(
                friendAndMeList, myRank
            )
        }
        return myRank
    }

}