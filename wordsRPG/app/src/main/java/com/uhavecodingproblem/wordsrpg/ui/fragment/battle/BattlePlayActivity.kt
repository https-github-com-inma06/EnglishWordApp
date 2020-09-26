package com.uhavecodingproblem.wordsrpg.ui.fragment.battle

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityBattlePlayBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity

class BattlePlayActivity : BaseActivity<ActivityBattlePlayBinding>(R.layout.activity_battle_play) {

    //현재 문제 Position
    private var currentQuiz = 0

    //현재 나의 정답 개수
    private var correctPoint = 0

    //서버에서 받아온 정답
    private var correct = ""

    //문제수가 총 5개라고 가정
    private var quizCount = 5


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

    }
}