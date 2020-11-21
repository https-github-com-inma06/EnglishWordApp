package com.uhavecodingproblem.wordsrpg.util

import androidx.recyclerview.widget.DiffUtil

/**
 * wordsrpg
 * Class: BaseDiffUtil
 * Created by pyg10.
 * Created On 2020-11-21.
 * Description:
 */
class BaseDiffUtil<T>(private val oldData: MutableList<T>, private val newData: MutableList<T>): DiffUtil.Callback(){

    override fun getOldListSize(): Int = if (oldData.isNullOrEmpty()) 0 else oldData.size

    override fun getNewListSize(): Int = if (newData.isNullOrEmpty()) 0 else newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldData[oldItemPosition] == newData[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldData[oldItemPosition] == newData[newItemPosition]
}