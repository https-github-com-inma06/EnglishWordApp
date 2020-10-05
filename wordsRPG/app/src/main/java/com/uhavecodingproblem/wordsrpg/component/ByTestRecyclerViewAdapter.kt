package com.uhavecodingproblem.wordsrpg.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.data.WordType
import com.uhavecodingproblem.wordsrpg.databinding.BasicPackageByTestItemBinding

/**
 * wordsrpg
 * Class: ByTestRecyclerViewAdapter
 * Created by pyg10.
 * Created On 2020-09-27.
 * Description:
 */
class ByTestRecyclerViewAdapter(val item: MutableList<WordType>,val listener: ByTestGridItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ByTestGridItemClickListener{
        fun onByTestItemClick(view: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(BasicPackageByTestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(item[position])
    }

    override fun getItemCount(): Int {
        return if (item.isNullOrEmpty()) 0 else item.size
    }

    inner class ItemViewHolder(val binding: BasicPackageByTestItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: WordType){
            binding.data = data

            binding.root.setOnClickListener {
                listener.onByTestItemClick(it, adapterPosition)
            }
        }
    }
}