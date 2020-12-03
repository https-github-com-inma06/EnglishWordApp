package com.uhavecodingproblem.wordsrpg.component.battle

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.User
import com.uhavecodingproblem.wordsrpg.databinding.DialogBattleChallengeBinding
import com.uhavecodingproblem.wordsrpg.databinding.ItemBattleRankingBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.battle.BattleGameActivity
import com.uhavecodingproblem.wordsrpg.ui.dialog.BattleDialog

class BattleRankingListAdapter(
    private val friendAndMeUserList:List<User>,
    private val myRanking: Int
) : RecyclerView.Adapter<BattleRankingListAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemBattleRankingBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val dialog = BattleDialog<DialogBattleChallengeBinding>(binding.root.context, R.layout.dialog_battle_challenge)

                dialog.binding.apply {
                    this.image = friendAndMeUserList[adapterPosition].profileImage
                    this.userName = friendAndMeUserList[adapterPosition].userName
                    this.currentCase = "님에게 배틀 신청"
                    this.btnGameStart.setOnClickListener {
                        Intent(binding.root.context, BattleGameActivity::class.java).also {
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
            if (position == friendAndMeUserList.size) {
                it.image = ""
                it.ranking = 0
                it.userName = ""
                it.score = 0
                it.layoutRootView.visibility = View.INVISIBLE
            } else {
                it.image = friendAndMeUserList[position].profileImage
                it.score = friendAndMeUserList[position].score!!.toInt()
                it.userName = friendAndMeUserList[position].userName
                if (position + 1 >= myRanking)
                    it.ranking = position + 2
                else
                    it.ranking = position + 1
            }
        }
    }

    override fun getItemCount() = friendAndMeUserList.size + 1

}