package com.uhavecodingproblem.wordsrpg.component.battle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ItemMainBattleSimilarScoreUsersBinding

class MainBattleSimilarScoreUsersAdapter(
    private val imageList: List<String> = listOf(),
    private val nameList: List<String> = listOf(),
    private val setClickEvent: () -> Unit
) :
    RecyclerView.Adapter<MainBattleSimilarScoreUsersAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding = DataBindingUtil.bind<ItemMainBattleSimilarScoreUsersBinding>(v)
        init {
                binding?.ivUserImage?.setOnClickListener {
                    if (adapterPosition == 0)
                    setClickEvent()

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_battle_similar_score_users, parent, false)
        )

    override fun getItemCount() = imageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding?.apply {
            if(position == 0) {
                image = imageList[position]
                name = "더보기"
                return@apply
            }
                image = imageList[position]
                name = nameList[position]
        }
    }
}