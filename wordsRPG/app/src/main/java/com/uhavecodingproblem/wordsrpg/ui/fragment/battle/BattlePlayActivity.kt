package com.uhavecodingproblem.wordsrpg.ui.fragment.battle

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.BattlePlayersItem
import com.uhavecodingproblem.wordsrpg.databinding.ActivityBattlePlayBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import com.uhavecodingproblem.wordsrpg.viewmodel.BattleViewModel

class BattlePlayActivity : BaseActivity<ActivityBattlePlayBinding>(R.layout.activity_battle_play) {

    //현재 문제 Position
    private var currentQuiz = 0

    //현재 나의 정답 개수
    private var correctPoint = 0

    //서버에서 받아온 정답
    private var correct = ""

    //문제수가 총 5개라고 가정
    private var quizCount = 5


    private val battleViewModel by lazy { ViewModelProvider(this).get(BattleViewModel::class.java) }
    private var progressBarVisible = false
    override fun ActivityBattlePlayBinding.onCreate() {


        if (correct.equals(etCorrect.text)) {
            correctPoint++
            currentQuiz++
        } else {
            currentQuiz++
        }

        if (currentQuiz == quizCount) {
            //나의 점수 서버로 전송 후
            //MainBattleFragment 로 이동
            //finish
            //나의 정답수를 intent data로 전달
        } else {
            //다음 문제 전환
        }

        battleViewModel.sendToToken(intent.getBooleanExtra("userToken", false)) {
            progressBarVisible = true
        } // 토큰 값 서버로 넘기고 프로그세브바 visible 킴

        battleViewModel.getOpponentUserUserData() // db 값 가져와서 패러미터로 넘김
        battleViewModel.getSystemData() // db 값 가져와서 패러미터로 넘김

        val myUserData = listOf(
            intent.getStringExtra("profileImage")!!,
            intent.getStringExtra("playerId")!!,
            intent.getStringExtra("playerLevel")!!

        )

        this.myUserData = BattlePlayersItem(myUserData[0], myUserData[1], myUserData[2])
        battleViewModel.opponentUser.observe(this@BattlePlayActivity, Observer {
            this.opponentUserData = it
        })

        battleViewModel.getSystemData.observe(this@BattlePlayActivity, Observer {
            this.battleSystemData = it
        })

    }
}
