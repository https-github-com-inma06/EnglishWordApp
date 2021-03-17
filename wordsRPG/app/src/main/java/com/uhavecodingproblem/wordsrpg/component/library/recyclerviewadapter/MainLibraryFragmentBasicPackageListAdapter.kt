package com.uhavecodingproblem.wordsrpg.component.library.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.PackageWithStage
import com.uhavecodingproblem.wordsrpg.databinding.ItemMainlibrayFragmentBasicPackageRecyclerviewBinding
import com.uhavecodingproblem.wordsrpg.util.BaseDiffUtil

/**
 * wordsrpg
 * Class: MainLibraryFragmentBasicPackageListAdapter
 * Created by pyg10.
 * Created On 2021-01-17.
 * Description:
 */
class MainLibraryFragmentBasicPackageListAdapter(private val listener: BasicPackageGridItemClickListener) :
    ListAdapter<PackageWithStage, RecyclerView.ViewHolder>(BaseDiffUtil<PackageWithStage>()) {

    interface BasicPackageGridItemClickListener {
        fun onItemClick(selectedItem: PackageWithStage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_mainlibray_fragment_basic_package_recyclerview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind()
        }
    }

    inner class ItemViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() = with(binding as ItemMainlibrayFragmentBasicPackageRecyclerviewBinding) {
            packageWithStage = getItem(adapterPosition)
            basicPackageLayout.clipToOutline = true
            root.setOnClickListener {
                listener.onItemClick(getItem(adapterPosition))
            }
            binding.executePendingBindings()
        }
    }
}