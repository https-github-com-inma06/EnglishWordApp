package com.uhavecodingproblem.wordsrpg.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ViewholderMyroomRecyclerviewBinding
import com.uhavecodingproblem.wordsrpg.util.imageUrl
import com.uhavecodingproblem.wordsrpg.util.imageUrlReSize

class MyRoomRecyclerViewAdapter(var imageList: MutableList<String> = mutableListOf()) :
    RecyclerView.Adapter<MyRoomRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ViewholderMyroomRecyclerviewBinding>(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_myroom_recyclerview, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.apply {
            imageUrlReSize(ivMyRoomItem,imageList[position],86,86)
        }
    }

    override fun getItemCount() = imageList.size
}