package com.uhavecodingproblem.wordsrpg.component.library.recyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.WordsRead
import com.uhavecodingproblem.wordsrpg.databinding.ItemWordListBinding

class MyCustomPackageAddWordRecyclerViewAdapter(
    var list:MutableList<WordsRead> = mutableListOf()
) :
    RecyclerView.Adapter<MyCustomPackageAddWordRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding = DataBindingUtil.bind<ItemWordListBinding>(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_word_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.apply {
            word = list[position].word
            meaning = list[position].mean
        }
    }

    override fun getItemCount() = list.size


}