package com.uhavecodingproblem.wordsrpg.ui.fragment.battle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.ext.loadImage
import kotlinx.android.synthetic.main.activity_battle_ready.*
import androidx.lifecycle.ViewModelProvider
import com.uhavecodingproblem.wordsrpg.viewmodel.BattleViewModel

class BattleReadyActivity : AppCompatActivity() {

    private val battleViewModel by lazy{
        ViewModelProvider(this).get(BattleViewModel::class.java)
    }
    var userToken:Boolean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battle_ready)

        val intent = intent
        val playerProfileImageUrl = intent.getStringExtra("profileImage")
        val playerId = intent.getStringExtra("playerId")
        val playerLevel = intent.getStringExtra("playerLevel")
        playerProfileImageUrl?.let { iv_players_profile_image.loadImage(it) }
        tv_players_id.text = playerId
        tv_players_level.text = "LEVEL $playerLevel"

        btn_start.setOnClickListener {

            if (!userToken!! || userToken == null)
                return@setOnClickListener

            battleViewModel.sendToToken(userToken!!)
            val intent = Intent(this, BattlePlayActivity::class.java)
            intent.putExtra("profileImage", playerProfileImageUrl)
            intent.putExtra("playerId", playerId)
            intent.putExtra("playerLevel", playerLevel)
            intent.putExtra("userToken", userToken!!)
        }


    }

    override fun onResume() {
        userToken = true
        super.onResume()
    }

    override fun onStop() {
        userToken = false
        super.onStop()
    }



}