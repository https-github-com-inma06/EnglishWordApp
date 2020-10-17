package com.uhavecodingproblem.wordsrpg.ui.fragment.battle

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.BattlePlayersItem
import com.uhavecodingproblem.wordsrpg.databinding.FragmentBattleTabAllPlayersBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.ui.fragment.battle.adapter.BattlePlayersAdapter

class TabAllPlayers :
    BaseFragment<FragmentBattleTabAllPlayersBinding>(R.layout.fragment_battle_tab_all_players) {

    private val battlePlayersAdapter = BattlePlayersAdapter()

    override fun FragmentBattleTabAllPlayersBinding.onCreateView() {
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.rvPlayersAll.adapter = battlePlayersAdapter
        binding.rvPlayersAll.setHasFixedSize(true)

        battlePlayersAdapter.setData(textList)
    }

    private val textList = arrayListOf(
        BattlePlayersItem("https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg", "나는피구왕", "7"),
        BattlePlayersItem("https://taegon.kim/wp-content/uploads/2018/05/image-5.png", "나는유희왕", "11"),
        BattlePlayersItem("https://vignette1.wikia.nocookie.net/bloodbrothersgame/images/e/e1/Boo-Dog-HD-Wallpaper.jpg/revision/latest?cb=20130426234637", "나는왕왕왕", "56"),
        BattlePlayersItem("https://taegon.kim/wp-content/uploads/2018/05/image-5.png", "나는배틀왕", "99")
    )
}