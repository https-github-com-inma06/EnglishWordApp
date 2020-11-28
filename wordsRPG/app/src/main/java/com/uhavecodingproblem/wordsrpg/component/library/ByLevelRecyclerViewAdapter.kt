package com.uhavecodingproblem.wordsrpg.component.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.data.WordType
import com.uhavecodingproblem.wordsrpg.databinding.*
import com.uhavecodingproblem.wordsrpg.util.ITEM_GIRD_TYPE
import com.uhavecodingproblem.wordsrpg.util.ITEM_HEADER_TYPE

/**
 * wordsrpg
 * Class: BasicRecyclerViewAdapter
 * Created by pyg10.
 * Created On 2020-09-27.
 * Description:
 */


class ByLevelRecyclerViewAdapter(val item: MutableList<WordType>, val listener: ByLevelGridItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ByLevelGridItemClickListener{
        fun onByLevelItemClick(view: View, position: Int)
    }

    fun isHeader(position: Int): Boolean{
        return position == 0 || position == 7 || position == 11
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == ITEM_HEADER_TYPE){
            HeaderViewHolder(BasicPackageByLevelHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }else{
            ItemViewHolder(BasicPackageByLevelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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

    class HeaderViewHolder(private val binding: BasicPackageByLevelHeaderBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(){
            when(adapterPosition){
                0->binding.tvBasicPackageHeader.text = "초등학교"
                7->binding.tvBasicPackageHeader.text = "중학교"
                11->binding.tvBasicPackageHeader.text = "고등학교"
            }

        }
    }

    inner class ItemViewHolder(private val binding: BasicPackageByLevelItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(){
            binding.data = item[adapterPosition-getHeaderCount()]

            binding.basicPackageLayout.clipToOutline = true
            binding.root.setOnClickListener{
                listener.onByLevelItemClick(it,adapterPosition-getHeaderCount())
            }
        }
        private fun getHeaderCount() : Int{
            return when(adapterPosition){
                0 -> 0
                in 1..6 -> 1
                in 7..10 -> 2
                else -> 3
            }
        }
    }
}