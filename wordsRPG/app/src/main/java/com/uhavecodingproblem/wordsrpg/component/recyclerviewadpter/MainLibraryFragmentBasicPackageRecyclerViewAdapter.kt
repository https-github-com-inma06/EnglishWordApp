package com.uhavecodingproblem.wordsrpg.component.recyclerviewadpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.data.WordType
import com.uhavecodingproblem.wordsrpg.databinding.ItemMainlibrayFragmentBasicPackageRecyclerviewBinding

/**
 * wordsrpg
 * Class: ByTestRecyclerViewAdapter
 * Created by pyg10.
 * Created On 2020-09-27.
 * Description:
 */
class MainLibraryFragmentBasicPackageRecyclerViewAdapter(val item: MutableList<WordType>, val listener: BasicPackageGridItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface BasicPackageGridItemClickListener {
        fun onItemClick(view: View, position: Int, isByLevel: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(ItemMainlibrayFragmentBasicPackageRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(item[position])
    }

    override fun getItemCount(): Int {
        return if (item.isNullOrEmpty()) 0 else item.size
    }

    inner class ItemViewHolder(val binding: ItemMainlibrayFragmentBasicPackageRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: WordType) {
            binding.data = data

            binding.basicPackageLayout.clipToOutline = true
            binding.root.setOnClickListener {
                if (data.type == "수준별")
                    listener.onItemClick(it, adapterPosition, true)
                else
                    listener.onItemClick(it, adapterPosition, false)
            }
        }
    }
}