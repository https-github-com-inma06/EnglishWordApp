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
import com.uhavecodingproblem.wordsrpg.databinding.ItemMainBattleSimilarScoreUsersBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.battle.BattleGameActivity
import com.uhavecodingproblem.wordsrpg.ui.dialog.BattleDialog

//class MainBattleSimilarScoreUsersAdapter(
//    private val user: User,
//    private val setClickEvent: () -> Unit
//) :
//    RecyclerView.Adapter<MainBattleSimilarScoreUsersAdapter.ViewHolder>() {
//
//    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
//        val binding = DataBindingUtil.bind<ItemMainBattleSimilarScoreUsersBinding>(v)
//
//        init {
//            binding?.ivUserImage?.setOnClickListener {
//                if (adapterPosition == 0)
//                    setClickEvent()
//                else {
//                    binding.ivUserImage.setOnClickListener {
//                        val dialog = BattleDialog<DialogBattleChallengeBinding>(
//                            binding.root.context,
//                            R.layout.dialog_battle_challenge
//                        ).apply {
//                            user.friendList?.sortedBy { it.score }
//
//                            this.binding.image = user.friendList!![adapterPosition].profileImage
//                            this.binding.userName = user.friendList!![adapterPosition].userName
//                            this.binding.currentCase = "님에게 배틀 신청"
//                            this.binding.btnGameStart.setOnClickListener {
//                                Intent(binding.root.context, BattleGameActivity::class.java).also {
//                                    binding.root.context.startActivity(it)
//                                }
//                                dismiss()
//                            }
//                        }
//                        dialog.show()
//
//                    }
//                }
//            }
//        }
//
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
//        ViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.item_main_battle_similar_score_users, parent, false)
//        )
//
//    override fun getItemCount() = user.friendList!!.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        holder.binding?.apply {
//            if (position == 0) {
//                image = root.context.resources.getString(R.string.imagePath).format("ic_baseline_add_32")
//                name = "더보기"
//                return@apply
//            }
//
//            user.friendList?.sortedBy { it.score }
//            image = user.friendList?.get(position)!!.profileImage
//            name = user.friendList?.get(position)!!.userName
//        }
//    }
//}