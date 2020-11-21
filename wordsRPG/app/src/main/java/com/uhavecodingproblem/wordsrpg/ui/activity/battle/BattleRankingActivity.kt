package com.uhavecodingproblem.wordsrpg.ui.activity.battle

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityBattleRankingBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import java.text.SimpleDateFormat
import java.util.*


class BattleRankingActivity : BaseActivity<ActivityBattleRankingBinding>(R.layout.activity_battle_ranking) {

    /** todo: 서버 완성후 모데롤 묶어서 심플하게 변경할 에정. */

    private val muckImageList = mutableListOf<String>()
    private val muckNameList = mutableListOf<String>()
    private val muckBattleScoreList = mutableListOf<Int>()

    override fun ActivityBattleRankingBinding.onCreate() {
        setMuckData()
    }

    private fun setMuckData() {
        /** todo: 서버 완성후 모데롤 묶어서 심플하게 변경할 에정. */
        var score = 0

        repeat(5) {
            score += 100
            if (it % 2 == 0) {
                muckImageList.add("https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg")
                muckNameList.add("초보임돵")
                muckBattleScoreList.add(score)
            } else {
                muckImageList.add("https://img.huffingtonpost.com/asset/5d814d8e3b00002b88d66359.jpeg?ops=scalefit_630_noupscale")
                muckNameList.add("재이")
                muckBattleScoreList.add(score)
            }
        }



        binding.apply {

            val muckMyScore = 456
            friendCount = 5
            image = "https://img.huffingtonpost.com/asset/5d814d8e3b00002b88d66359.jpeg?ops=scalefit_630_noupscale"
            userName = "Loner"
            myScore = muckMyScore
            muckBattleScoreList.add(muckMyScore)
            muckBattleScoreList.sortBy { it }
            myRanking = muckBattleScoreList.indexOf(muckMyScore)
            muckBattleScoreList.remove(muckMyScore)

        }

    }
}