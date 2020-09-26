package com.uhavecodingproblem.wordsrpg.ui.fragment.battle

import android.content.Intent
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityBattleReadyBinding
import com.uhavecodingproblem.wordsrpg.ext.loadImage
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity

class BattleReadyActivity : BaseActivity<ActivityBattleReadyBinding>(R.layout.activity_battle_ready) {
    
    override fun ActivityBattleReadyBinding.onCreate() {

        //상대방의 프로필, 아이디, 레벨
        val getIntent = intent
        val playerProfileImageUrl = getIntent.getStringExtra("profileImage")
        val playerId = getIntent.getStringExtra("playerId")
        val playerLevel = getIntent.getStringExtra("playerLevel")
        playerProfileImageUrl?.let { ivPlayersProfileImage.loadImage(it) }
        tvPlayersId.text = playerId
        tvPlayersLevel.text = "LEVEL $playerLevel"

        btnStart.setOnClickListener {
            val intent = Intent(this@BattleReadyActivity, BattlePlayActivity::class.java)
            intent.putExtra("profileImage", playerProfileImageUrl)
            intent.putExtra("playerId", playerId)
            intent.putExtra("playerLevel", playerLevel)
            startActivity(intent)
            finish()
        }
    }

}