package com.uhavecodingproblem.wordsrpg.ui.activity.library

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.ActivityWrongAnswerNoteBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity

/**
 * wordsrpg
 * Class: WrongAnswerActivity.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 * 오답노트 엑티비티 입니다.
 */
class WrongAnswerActivity:BaseActivity<ActivityWrongAnswerNoteBinding>(R.layout.activity_wrong_answer_note) {
    override fun ActivityWrongAnswerNoteBinding.onCreate() {
        Logger.v("실행")
    }

}