package com.uhavecodingproblem.wordsrpg.ui.fragment.battle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.BattlePlayersItem
import com.uhavecodingproblem.wordsrpg.databinding.ActivityBattlePlayBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import androidx.lifecycle.ViewModelProvider
import com.uhavecodingproblem.wordsrpg.viewmodel.BattleViewModel

class BattlePlayActivity : BaseActivity<ActivityBattlePlayBinding>(R.layout.activity_battle_play) {

    private val battleViewModel by lazy { ViewModelProvider(this).get(BattleViewModel::class.java) }
    private var progressBarVisible = false
    override fun ActivityBattlePlayBinding.onCreate() {

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