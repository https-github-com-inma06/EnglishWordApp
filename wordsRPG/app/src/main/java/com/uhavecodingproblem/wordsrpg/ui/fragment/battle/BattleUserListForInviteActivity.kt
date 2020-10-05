package com.uhavecodingproblem.wordsrpg.ui.fragment.battle

import android.os.Bundle
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.BattlePlayersItem
import com.uhavecodingproblem.wordsrpg.databinding.ActivityBattleUserListForInviteBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import com.uhavecodingproblem.wordsrpg.ui.fragment.battle.adapter.BattlePlayersAdapter

class BattleUserListForInviteActivity :
    BaseActivity<ActivityBattleUserListForInviteBinding>(R.layout.activity_battle_user_list_for_invite) {

    private val battlePlayersAdapter = BattlePlayersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRecyclerView()

        battlePlayersAdapter.itemClickListener = {
            val dialog = BattleStartDialogFragment.newInstance("로너", "존잘")
            dialog?.show(supportFragmentManager, "dialog")

        }
    }

    private fun setRecyclerView() {
        binding.rvPlayersAll.adapter = battlePlayersAdapter
        binding.rvPlayersAll.setHasFixedSize(true)

        battlePlayersAdapter.setData(textList)
    }

    private val textList = arrayListOf(
        BattlePlayersItem("https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg", "나는피구왕", "7"),
        BattlePlayersItem("https://taegon.kim/wp-content/uploads/2018/05/image-5.png", "나는유희왕", "11"),
        BattlePlayersItem(
            "https://vignette1.wikia.nocookie.net/bloodbrothersgame/images/e/e1/Boo-Dog-HD-Wallpaper.jpg/revision/latest?cb=20130426234637",
            "나는왕왕왕",
            "56"
        ),
        BattlePlayersItem("https://taegon.kim/wp-content/uploads/2018/05/image-5.png", "나는배틀왕", "99")
    )
}