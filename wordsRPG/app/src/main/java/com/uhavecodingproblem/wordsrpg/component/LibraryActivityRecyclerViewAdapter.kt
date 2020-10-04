package com.uhavecodingproblem.wordsrpg.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.data.WordData
import com.uhavecodingproblem.wordsrpg.data.WordType
import com.uhavecodingproblem.wordsrpg.databinding.LibraryActivityItemBinding

/**
 * wordsrpg
 * Class: LibraryActivityRecyclerViewAdapter
 * Created by pyg10.
 * Created On 2020-10-03.
 * Description:
 */
class LibraryActivityRecyclerViewAdapter(val item: MutableList<WordType>, private val listener: GridItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface GridItemClickListener{
        fun onItemClick(v: View, clickItem: MutableList<WordData>, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(LibraryActivityItemBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(item[position])
    }

    override fun getItemCount(): Int {
        return if (item.isNullOrEmpty()) 0 else item.size
    }

    inner class ItemViewHolder(val binding: LibraryActivityItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data: WordType){

            binding.data = data

            binding.root.setOnClickListener {
                listener.onItemClick(it, item[adapterPosition].words, adapterPosition)
            }
        }

    }
}