package com.uhavecodingproblem.wordsrpg.ui.fragment.battle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.BattlePlayersItem
import com.uhavecodingproblem.wordsrpg.ui.fragment.battle.holder.BattlePlayersViewHolder

class BattlePlayersAdapter : RecyclerView.Adapter<BattlePlayersViewHolder>() {

    private val playersItems = mutableListOf<BattlePlayersItem>()
    lateinit var itemClickListener: (String) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BattlePlayersViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_battle_players, parent, false)
        )
            .apply {
                itemView.setOnClickListener {
                    itemClickListener.invoke(playersItems[adapterPosition].playersProfileImage)

                }
            }

    override fun onBindViewHolder(holder: BattlePlayersViewHolder, position: Int) {
        holder.onBind(playersItems[position])
//        holder.itemView.setOnClickListener {
//            Toast.makeText(holder.itemView.context, "아이템 눌림", Toast.LENGTH_LONG).show()
//            val intent = Intent(holder.itemView.context, BattleReadyActivity::class.java)
//            intent.putExtra("profileImage", playersItems[position].playersProfileImage)
//            intent.putExtra("playerLevel", playersItems[position].playersLv)
//            intent.putExtra("playerId", playersItems[position].playersId)
//            holder.itemView.context.startActivity(intent)
//        }

    }

    override fun getItemCount() = playersItems.size

    fun setData(items: List<BattlePlayersItem>) {
        playersItems.addAll(items)
        notifyDataSetChanged()
    }

}


