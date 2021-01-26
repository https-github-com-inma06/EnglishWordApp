package com.uhavecodingproblem.wordsrpg.util

import androidx.recyclerview.widget.DiffUtil

/**
 * wordsrpg
 * Class: BaseDiffUtil
 * Created by pyg10.
 * Created On 2020-11-21.
 * Description:
 */
class BaseDiffUtil<T>: DiffUtil.ItemCallback<T>(){

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem.hashCode() == newItem.hashCode()
}