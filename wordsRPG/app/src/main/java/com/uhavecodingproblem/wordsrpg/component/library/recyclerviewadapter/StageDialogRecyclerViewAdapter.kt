package com.uhavecodingproblem.wordsrpg.component.library.recyclerviewadapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.Learning
import com.uhavecodingproblem.wordsrpg.databinding.ItemStageDialogBinding
import com.uhavecodingproblem.wordsrpg.util.*

/**
 * wordsrpg
 * Class: StageDialogRecyclerViewAdapter
 * Created by pyg10.
 * Created On 2020-10-03.
 * Description:
 */
class StageDialogRecyclerViewAdapter(
    private val listener: ItemClickListener
) : ListAdapter<Learning, RecyclerView.ViewHolder>(BaseDiffUtil<Learning>()) {

    private var preSelectPosition = 0
    private val sparseBooleanArray = SparseBooleanArray()
    private var totalItemSize = 0
    private var calculatorItemSize = 0

    interface ItemClickListener {
        fun onItemClick()
        fun onMoveOption(position: Int)
    }

    override fun getItemCount(): Int = calculatorSize()

    override fun submitList(list: MutableList<Learning>?) {
        super.submitList(list)
        list?.let { totalItemSize = it.size }
    }

    private fun calculatorSize(): Int {
        var size = 0
        var count = 0
        for (i in currentList.indices) {
            if (currentList[i].stage_status != "3")
                count++

            size++
            if (count > 1)
                break
        }
        calculatorItemSize = size
        return size
    }

    fun setUpFirstFocus(position: Int){
        sparseBooleanArray.clear()
        sparseBooleanArray.put(position, true)
        preSelectPosition = position
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).stage_status) {
            "0" -> STAGE_NONE
            "1" -> STAGE_STUDYING
            "2" -> STAGE_TEST_FAIL
            else -> STAGE_TEST_CLEAR
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_stage_dialog, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder)
            holder.bind()
    }

    inner class ItemViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() = with(binding as ItemStageDialogBinding) {

            initBinding()

            if (adapterposition == itemCount - 1){
                layoutStageExpand.isEnabled = false
                layoutStageCollapse.isEnabled = false
            }


            layoutStageCollapse.setOnClickListener {
                changeSparseArray()
                Logger.d("collapse adapterPosition : $adapterposition prePosition : $preSelectPosition")
                listener.onItemClick()
            }
            layoutStageExpand.setOnClickListener {
                changeSparseArray()
                Logger.d("expand adapterPosition : $adapterposition prePosition : $preSelectPosition")
                listener.onItemClick()
            }

            btnMoveSelectionWindowDialog.setOnClickListener {
                listener.onMoveOption(adapterPosition)
            }

            openCloseStage()
        }

        private fun initBinding() = with(binding as ItemStageDialogBinding) {
            learning = getItem(adapterPosition)
            adapterposition = adapterPosition
            bottomposition = if (itemCount > 1) itemCount - 1 else 0
            morestage = moreStageStatus()
            layoutDialogItem.clipToOutline = true
        }

        private fun moreStageStatus(): String {
            return if (itemCount == currentList.size)
                "STAGE ${currentList.size}"
            else
                "STAGE $itemCount ~ ${currentList.size}"
        }

        private fun changeSparseArray() {
            if (!sparseBooleanArray[adapterPosition]) {
                Logger.d("1 trigger ${sparseBooleanArray[adapterPosition]}")
                sparseBooleanArray.delete(preSelectPosition)
                sparseBooleanArray.put(adapterPosition, true)
            } else {
                Logger.d("2 trigger ${sparseBooleanArray[adapterPosition]}")
                sparseBooleanArray.delete(adapterPosition)
                Logger.d("3 trigger ${sparseBooleanArray[adapterPosition]}")
            }
            notifyItemChanged(preSelectPosition)
            notifyItemChanged(adapterPosition)
            preSelectPosition = adapterPosition
        }

        private fun openCloseStage() = with(binding as ItemStageDialogBinding) {
            if (sparseBooleanArray[adapterPosition]) {
                layoutStageCollapse.visibility = View.GONE
                layoutStageExpand.visibility = View.VISIBLE
                if (adapterposition != if (itemCount > 2) itemCount - 2 else 0) {
                    tvResult.visibility = View.VISIBLE
                    tvStageExpandDescription.visibility = View.GONE
                } else {
                    tvResult.visibility = View.GONE
                    tvStageExpandDescription.visibility = View.VISIBLE
                }
            } else {
                layoutStageExpand.visibility = View.GONE
                layoutStageCollapse.visibility = View.VISIBLE
            }
        }
    }
}