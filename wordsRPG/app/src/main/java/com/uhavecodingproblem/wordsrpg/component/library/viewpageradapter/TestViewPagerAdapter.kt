package com.uhavecodingproblem.wordsrpg.component.library.viewpageradapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.WordsRead
import com.uhavecodingproblem.wordsrpg.databinding.ItemTestBinding
import com.uhavecodingproblem.wordsrpg.util.Logger

/**
 * wordsrpg
 * Class: TestViewPagerAdapter
 * Created by pyg10.
 * Created On 2021-03-23.
 * Description:
 */
class TestViewPagerAdapter(private val questionItem: MutableList<WordsRead>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    = ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_test, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder)
            holder.bind()
    }

    override fun getItemCount(): Int = if (questionItem.isNullOrEmpty()) 0 else questionItem.size

    inner class ItemViewHolder(private val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root){
        fun bind() = with(binding as ItemTestBinding){

            binding.word = questionItem[adapterPosition].word

            binding.radioGroupAnswer.setOnCheckedChangeListener { _, checkedId ->
                when(checkedId){
                    R.id.radio_btn_answer_num_one ->{
                        Logger.d(binding.radioBtnAnswerNumOne.text.toString())
                    }
                    R.id.radio_btn_answer_num_two->{
                        Logger.d(binding.radioBtnAnswerNumOne.text.toString())
                    }
                    R.id.radio_btn_answer_num_three->{
                        Logger.d(binding.radioBtnAnswerNumOne.text.toString())
                    }
                    R.id.radio_btn_answer_num_four->{
                        Logger.d(binding.radioBtnAnswerNumOne.text.toString())
                    }
                    R.id.radio_btn_answer_num_five->{
                        Logger.d(binding.radioBtnAnswerNumOne.text.toString())
                    }
                }
            }
        }
    }
}