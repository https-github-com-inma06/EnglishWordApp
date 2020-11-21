package com.uhavecodingproblem.wordsrpg.component.battle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ItemBattleRankingBinding

class BattleRankingListAdapter(
    val imageList: MutableList<String> = mutableListOf(),
    val nameList: MutableList<String> = mutableListOf(),
    val battleScoreList: MutableList<Int> = mutableListOf()
) : RecyclerView.Adapter<BattleRankingListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemBattleRankingBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        DataBindingUtil.inflate<ItemBattleRankingBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_battle_ranking, parent, false
        ).also {
            return ViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.also {
            if (position == imageList.size + 1) {
                it.image = ""
                it.ranking = 0
                it.userName = ""
                it.score = 0
                it.layoutRootView.visibility = View.GONE
            }
        }

    }

    override fun getItemCount() = imageList.size + 1

}