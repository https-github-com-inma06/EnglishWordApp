package com.uhavecodingproblem.wordsrpg.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.BasicPackageHeaderBinding
import com.uhavecodingproblem.wordsrpg.databinding.BasicPackageItemBinding
import com.uhavecodingproblem.wordsrpg.util.Logger

/**
 * wordsrpg
 * Class: BasicRecyclerViewAdapter
 * Created by pyg10.
 * Created On 2020-09-27.
 * Description:
 */
const val ITEM_HEADER_TYPE = 0
const val ITEM_GIRD_TYPE = 1

class ByLevelRecyclerViewAdapter(val item: List<String>, val listener: GridItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var headerCount = 0

    interface GridItemClickListener{
        fun onItemClick(view: View, position: Int)
    }

    fun isHeader(position: Int): Boolean{
        return position == 0 || position == 7 || position == 11
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == ITEM_HEADER_TYPE){
            headerCount++
            HeaderViewHolder(BasicPackageHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }else{
            ItemViewHolder(BasicPackageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_HEADER_TYPE )
            (holder as HeaderViewHolder).bind()
        else
            (holder as ItemViewHolder).bind()
    }

    override fun getItemViewType(position: Int): Int {
        return if (isHeader(position)) ITEM_HEADER_TYPE else ITEM_GIRD_TYPE
    }

    override fun getItemCount(): Int {
        return if (item.isNullOrEmpty()) 0 else item.size + 3
    }

    inner class HeaderViewHolder(private val binding: BasicPackageHeaderBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(){
            when(headerCount){
                1->binding.tvBasicPackageHeader.text = "초등"
                2->binding.tvBasicPackageHeader.text = "중등"
                3->binding.tvBasicPackageHeader.text = "고등"
            }

        }
    }

    inner class ItemViewHolder(private val binding: BasicPackageItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
            Logger.v("현재위치 : ${adapterPosition-headerCount} 전체크기 : ${item.size}")
            binding.basicPackageLayout.setBackgroundResource(R.drawable.ic_launcher_background)
            binding.tvPackageTitle.text = item[adapterPosition-headerCount]
            binding.tvPackageDescription.text = item[adapterPosition-headerCount]

            binding.root.setOnClickListener{
                Logger.v("클릭됐음")
                listener.onItemClick(it, adapterPosition-headerCount)
            }
        }
    }



}