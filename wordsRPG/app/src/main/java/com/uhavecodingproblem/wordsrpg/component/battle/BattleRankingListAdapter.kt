package com.uhavecodingproblem.wordsrpg.component.battle

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.DialogBattleChallengeBinding
import com.uhavecodingproblem.wordsrpg.databinding.ItemBattleRankingBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.battle.BattleGameActivity
import com.uhavecodingproblem.wordsrpg.ui.dialog.BattleDialog

class BattleRankingListAdapter(
    private val imageList: MutableList<String> = mutableListOf(),
    private val nameList: MutableList<String> = mutableListOf(),
    private val battleScoreList: MutableList<Int> = mutableListOf(),
    private val myRanking: Int
) : RecyclerView.Adapter<BattleRankingListAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemBattleRankingBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val dialog = BattleDialog<DialogBattleChallengeBinding>(binding.root.context, R.layout.dialog_battle_challenge)

                dialog.binding.apply {
                    this.image = imageList[adapterPosition]
                    this.userName = nameList[adapterPosition]
                    this.currentCase = "님에게 배틀 신청"
                    this.btnGameStart.setOnClickListener {
                        Intent(binding.root.context,BattleGameActivity::class.java).also {
                            root.context.startActivity(it)
                        }
                        dialog.dismiss()
                    }
                }
                dialog.show()
            }
        }
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
                if (position + 1 >= myRanking)
                    it.ranking = position + 2
                else
                    it.ranking = position + 1
            }
        }
    }

    override fun getItemCount() = imageList.size + 1

}