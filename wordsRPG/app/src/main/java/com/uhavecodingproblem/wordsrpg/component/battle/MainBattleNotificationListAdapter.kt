package com.uhavecodingproblem.wordsrpg.component.battle

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.User
import com.uhavecodingproblem.wordsrpg.databinding.DialogBattleChallengeBinding
import com.uhavecodingproblem.wordsrpg.databinding.DialogBattleResultBinding
import com.uhavecodingproblem.wordsrpg.databinding.ItemMainBattleNotificationBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.battle.BattleGameActivity
import com.uhavecodingproblem.wordsrpg.ui.dialog.BattleDialog
import com.uhavecodingproblem.wordsrpg.ui.fragment.battle.MainBattleFragment.Companion.BATTLE_RESULT_LOSE
import com.uhavecodingproblem.wordsrpg.ui.fragment.battle.MainBattleFragment.Companion.BATTLE_RESULT_REQUEST
import com.uhavecodingproblem.wordsrpg.ui.fragment.battle.MainBattleFragment.Companion.BATTLE_RESULT_TIE
import com.uhavecodingproblem.wordsrpg.ui.fragment.battle.MainBattleFragment.Companion.BATTLE_RESULT_WIN


//class MainBattleNotificationListAdapter(val currentUser: User) :
//    RecyclerView.Adapter<MainBattleNotificationListAdapter.ViewHolder>() {
//
//    inner class ViewHolder(val binding: ItemMainBattleNotificationBinding?) : RecyclerView.ViewHolder(binding!!.root) {
//        init {
//            currentUser.friendList?.sortedBy { it.score }
//            bind()
//        }
//
//        private fun bind() {
//            binding?.apply {
//                ivUserCurrentImage.setOnClickListener {
//                    val notiPostion = currentUser.notificationList?.get(adapterPosition)!!.userIdx.toInt()
//
//                    if (ivBattleAlarmMode.isSelected) {
//                        val dialogChallenge = BattleDialog<DialogBattleChallengeBinding>(
//                            root.context,
//                            R.layout.dialog_battle_challenge
//                        )
//
//                        dialogChallenge.binding.apply {
//
//                            this.image = currentUser.friendList?.get(notiPostion)!!.profileImage
//                            this.currentCase = "배틀 신청이 걸려왔습니다."
//                            this.userName = currentUser.friendList!![notiPostion].userName
//                            this.btnGameStart.setOnClickListener {
//                                Intent(binding.root.context, BattleGameActivity::class.java).also {
//                                    root.context.startActivity(it)
//                                }
//                                dialogChallenge.dismiss()
//                            }
//                        }
//                        dialogChallenge.show()
//                    } else {
//                        val dialogResult = BattleDialog<DialogBattleResultBinding>(
//                            root.context,
//                            R.layout.dialog_battle_result
//                        ).apply {
//                            this.binding.image = currentUser.friendList?.get(notiPostion)!!.profileImage
//                            this.binding.userName = currentUser.friendList!![notiPostion].userName
//                            this.binding.result = "값 아직 없음"
//                            this.binding.currentScore = currentUser.score!!.toInt()
//                            this.binding.plusScore = 100
//                            this.binding.newScore = currentUser.score!!.toInt() + 100
//                            this.binding.btnGameStart.setOnClickListener {
//
//                                Intent(binding.root.context, BattleGameActivity::class.java).also {
//                                    root.context.startActivity(it)
//                                }
//
//                            }
//                            this.binding.tvClose.setOnClickListener {
//                                onBackPressed()
//                                dismiss()
//                            }
//                        }
//                        dialogResult.show()
//                    }
//                }
//            }
//        }
//
//        fun notifyTypeCheck(type: Int): Boolean {
//            return when (type) {
//                BATTLE_RESULT_REQUEST -> true
//                else -> false
//            }
//        }
//
//        fun notifyTypeToString(type: Int): String {
//            return when (type) {
//                BATTLE_RESULT_REQUEST -> "님이 배틀을 신청하셨습니다."
//                else -> "님과의 배틀 결과를 확인해보세요"
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//
//        DataBindingUtil.inflate<ItemMainBattleNotificationBinding>(
//            LayoutInflater.from(parent.context),
//            R.layout.item_main_battle_notification, parent, false
//        ).also {
//            return ViewHolder(it)
//        }
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.binding?.also {
//            currentUser.friendList?.sortedBy { user -> user.score }
//            val notiPostion = currentUser.notificationList?.get(position)!!.userIdx.toInt()
//            it.user = currentUser.friendList?.get(notiPostion)
//            it.date = currentUser.notificationList!![position].date
//            it.battleAlarmDescribe = holder.notifyTypeToString(currentUser.notificationList?.get(position)!!.notificationType!!)
//            it.ivBattleAlarmMode.isSelected = holder.notifyTypeCheck(currentUser.notificationList?.get(position)!!.notificationType!!)
//        }
//    }
//
//    override fun getItemCount() = currentUser.notificationList!!.size
//}