package com.uhavecodingproblem.wordsrpg.component.battle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ItemMainBattleNotificationBinding


class MainBattleNotificationListAdapter(
    private val imageList: List<String> = listOf(),
    private val userNameList: List<String> = listOf(),
    private val battleAlarmDescribeList: List<String> = listOf(),
    private val alarmModeList: List<Boolean> = listOf(),
    private val battleDateList: List<String> = listOf()
) : RecyclerView.Adapter<MainBattleNotificationListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMainBattleNotificationBinding?) : RecyclerView.ViewHolder(binding!!.root) {

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