package com.uhavecodingproblem.wordsrpg.component.library.viewpageradapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.Test
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
class TestViewPagerAdapter(private val questionType: MutableList<Int>, private val questionItem: MutableList<Test>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val checkBoxClickStatus = SparseBooleanArray()


    interface OnItemClickListener{
        fun onNextBtnClickEvent(type: Int, answer: String)
        fun onResultPage(type: Int, answer: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    = ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_test, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder)
            holder.bind()
    }

    override fun getItemCount(): Int = if (questionItem.isNullOrEmpty()) 0 else questionItem.size

    inner class ItemViewHolder(private val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root){
        fun bind() = with(binding as ItemTestBinding){

            init()

            checkboxOne.setOnClickListener {
                setUpCheckBoxStatus(0)
                notifyItemChanged(adapterPosition, Unit)
            }
            checkboxTwo.setOnClickListener {
                setUpCheckBoxStatus(1)
                notifyItemChanged(adapterPosition, Unit)
            }
            checkboxThree.setOnClickListener {
                setUpCheckBoxStatus(2)
                notifyItemChanged(adapterPosition, Unit)
            }
            checkboxFour.setOnClickListener {
                setUpCheckBoxStatus(3)
                notifyItemChanged(adapterPosition, Unit)
            }
            checkboxFive.setOnClickListener {
                setUpCheckBoxStatus(4)
                notifyItemChanged(adapterPosition, Unit)
            }

            if (findIndexCheckedCheckbox() == -1)
                btnNext.visibility = View.INVISIBLE
            else
                btnNext.visibility = View.VISIBLE

        }

        fun init() = with(binding as ItemTestBinding){
            if (questionType[adapterPosition] % 2 == 0){
                word = questionItem[adapterPosition].question.question_word

                checkboxOne.text = questionItem[adapterPosition].example[0].example_mean
                checkboxTwo.text = questionItem[adapterPosition].example[1].example_mean
                checkboxThree.text = questionItem[adapterPosition].example[2].example_mean
                checkboxFour.text = questionItem[adapterPosition].example[3].example_mean
                checkboxFive.text = questionItem[adapterPosition].example[4].example_mean
            }else{
                word = questionItem[adapterPosition].question.question_mean

                checkboxOne.text = questionItem[adapterPosition].example[0].example_word
                checkboxTwo.text = questionItem[adapterPosition].example[1].example_word
                checkboxThree.text = questionItem[adapterPosition].example[2].example_word
                checkboxFour.text = questionItem[adapterPosition].example[3].example_word
                checkboxFive.text = questionItem[adapterPosition].example[4].example_word
            }

            totalPosition = questionItem.size
            position = adapterPosition

            btnNext.setOnClickListener {
                if(adapterPosition == questionItem.size - 1)
                    itemClickListener.onResultPage(questionType[adapterPosition], answer())
                else {
                    itemClickListener.onNextBtnClickEvent(questionType[adapterPosition], answer())
                    initCheckBoxStatus()
                    //최초 1회는 클리어가안됨 왜인지는모름 ㅠㅠ
                    notifyDataSetChanged()
                }
            }

            checkboxOne.isChecked = checkBoxClickStatus[0]
            checkboxTwo.isChecked = checkBoxClickStatus[1]
            checkboxThree.isChecked = checkBoxClickStatus[2]
            checkboxFour.isChecked = checkBoxClickStatus[3]
            checkboxFive.isChecked = checkBoxClickStatus[4]
        }

        private fun setUpCheckBoxStatus(position: Int){
            if (findIndexCheckedCheckbox() != -1){
                if(!checkBoxClickStatus[position]){
                    checkBoxClickStatus.put(findIndexCheckedCheckbox(), false)
                    checkBoxClickStatus.put(position, true)
                }else
                    checkBoxClickStatus.put(position, false)
            }else{
                if (!checkBoxClickStatus[position])
                    checkBoxClickStatus.put(position, true)
                else
                    checkBoxClickStatus.put(position, false)
            }
        }

        private fun findIndexCheckedCheckbox(): Int{
            return when {
                checkBoxClickStatus[0] -> 0
                checkBoxClickStatus[1] -> 1
                checkBoxClickStatus[2] -> 2
                checkBoxClickStatus[3] -> 3
                checkBoxClickStatus[4] -> 4
                else -> -1
            }
        }

        private fun answer() : String = with(binding as ItemTestBinding){
            return when{
                checkBoxClickStatus[0] -> checkboxOne.text.toString()
                checkBoxClickStatus[1] -> checkboxTwo.text.toString()
                checkBoxClickStatus[2] -> checkboxThree.text.toString()
                checkBoxClickStatus[3] -> checkboxFour.text.toString()
                checkBoxClickStatus[4] -> checkboxFive.text.toString()
                else -> ""
            }
        }

        private fun initCheckBoxStatus(){
            checkBoxClickStatus.clear()
        }
    }

}