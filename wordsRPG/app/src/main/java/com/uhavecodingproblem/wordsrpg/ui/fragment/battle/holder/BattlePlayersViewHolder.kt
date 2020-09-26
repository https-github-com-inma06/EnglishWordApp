package com.uhavecodingproblem.wordsrpg.ui.fragment.battle.holder

import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.data.model.BattlePlayersItem
import com.uhavecodingproblem.wordsrpg.databinding.ItemBattlePlayersBinding

class BattlePlayersViewHolder(private val binding: ItemBattlePlayersBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(battlePlayersItem: BattlePlayersItem) {
        binding.battle = battlePlayersItem
        binding.executePendingBindings()
    }
}