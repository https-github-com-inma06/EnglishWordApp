package com.uhavecodingproblem.wordsrpg.component.battle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ItemBattleRankingBinding

class BattleRankingListAdapter(
    private val imageList: MutableList<String> = mutableListOf(),
    private val nameList: MutableList<String> = mutableListOf(),
    private val battleScoreList: MutableList<Int> = mutableListOf(),
    private val myRanking: Int
) : RecyclerView.Adapter<BattleRankingListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemBattleRankingBinding) : RecyclerView.ViewHolder(binding.root)

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
            if (position == imageList.size) {
                it.image = ""
                it.ranking = 0
                it.userName = ""
                it.score = 0
                it.layoutRootView.visibility = View.INVISIBLE
            } else {
                it.image = imageList[position]
                it.score = battleScoreList[position]
                it.userName = nameList[position]
                if(position+1 >= myRanking)
                    it.ranking = position + 2
                else
                it.ranking = position + 1
            }
        }

    }

    override fun getItemCount() = imageList.size + 1

}