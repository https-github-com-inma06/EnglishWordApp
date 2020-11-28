package com.uhavecodingproblem.wordsrpg.component.battle

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.DialogBattleChallengeBinding
import com.uhavecodingproblem.wordsrpg.databinding.DialogBattleResultBinding
import com.uhavecodingproblem.wordsrpg.databinding.ItemMainBattleNotificationBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.battle.BattleGameActivity
import com.uhavecodingproblem.wordsrpg.ui.dialog.BattleDialog


class MainBattleNotificationListAdapter(
    private val imageList: List<String> = listOf(),
    private val userNameList: List<String> = listOf(),
    private val battleAlarmDescribeList: List<String> = listOf(),
    private val alarmModeList: List<Boolean> = listOf(),
    private val battleDateList: List<String> = listOf()
) : RecyclerView.Adapter<MainBattleNotificationListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMainBattleNotificationBinding?) : RecyclerView.ViewHolder(binding!!.root) {
        init {
            binding?.apply {
                ivUserCurrentImage.setOnClickListener {
                    if (ivBattleAlarmMode.isSelected) {
                        val dialogChallenge = BattleDialog<DialogBattleChallengeBinding>(
                            root.context,
                            R.layout.dialog_battle_challenge
                        )

                        dialogChallenge.binding.apply {
                            this.image = imageList[adapterPosition]
                            this.currentCase = "배틀 신청이 걸려왔습니다."
                            this.userName = userNameList[adapterPosition]
                            this.btnGameStart.setOnClickListener {
                                Intent(binding.root.context, BattleGameActivity::class.java).also {
                                    root.context.startActivity(it)
                                }
                                dialogChallenge.dismiss()
                            }
                        }
                        dialogChallenge.show()
                    } else {
                        val dialogResult = BattleDialog<DialogBattleResultBinding>(
                            root.context,
                            R.layout.dialog_battle_result).apply {
                            this.binding.image = imageList[adapterPosition]
                            this.binding.userName = userNameList[adapterPosition]
                            this.binding.result = "값 아직 없음"
                            this.binding.currentScore = 5555555
                            this.binding.plusScore = 100
                            this.binding.newScore = 5555555 + 100
                            this.binding.btnGameStart.setOnClickListener {
                                Intent(binding.root.context,BattleGameActivity::class.java).also {
                                    root.context.startActivity(it)
                                }
                            }
                            this.binding.tvClose.setOnClickListener {
                                onBackPressed()
                                dismiss()
                            }
                        }
                        dialogResult.show()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        DataBindingUtil.inflate<ItemMainBattleNotificationBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_main_battle_notification, parent, false
        ).also {
            return ViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.also {

            it.image = imageList[position]
            it.date = battleDateList[position]
            it.userName = userNameList[position]
            it.battleAlarmDescribe = battleAlarmDescribeList[position]
            it.ivBattleAlarmMode.isSelected = alarmModeList[position]
        }
    }

    override fun getItemCount() = imageList.size
}