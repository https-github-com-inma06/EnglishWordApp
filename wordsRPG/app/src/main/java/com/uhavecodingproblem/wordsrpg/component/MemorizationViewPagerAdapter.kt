package com.uhavecodingproblem.wordsrpg.component

import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.data.WordData
import com.uhavecodingproblem.wordsrpg.databinding.MemorizationItemBinding

/**
 * wordsrpg
 * Class: MemorizationViewPagerAdapter
 * Created by pyg10.
 * Created On 2020-09-21.
 * Description:
 *
 * Memorization ViewPager2 Adapter
 * no fragment swipe
 * image swipe ViewPager2
 *
 */
class MemorizationViewPagerAdapter(
    private var word: MutableList<WordData>,
    private val listener: ItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
        설정저장이 필요한가?

     */


//    private var wordSparseBooleanArray = SparseBooleanArray()
//    private var meanSparseBooleanArray = SparseBooleanArray()

    var hideWordCheck = false
    var hideMeanCheck = false

    interface ItemClickListener {
        fun micClick(v: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val memorizationItemBinding =
            MemorizationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(memorizationItemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pagerHolder = holder as PagerViewHolder
        pagerHolder.bind(word[position])
    }

    override fun getItemCount(): Int {
        return if (word.isNullOrEmpty()) 0 else word.size
    }

    inner class PagerViewHolder(private val binding: MemorizationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WordData) {
            binding.adapter = this@MemorizationViewPagerAdapter
            binding.data = item

            val count = "${adapterPosition + 1} / ${word.size}"
            binding.wordCount.text = count

            binding.word.setOnClickListener{
                hideShowText(view = it, position = adapterPosition, isWord = true)
            }

            binding.mean.setOnClickListener {
                hideShowText(view = it, position = adapterPosition, isWord = false)
            }

            binding.wordMic.setOnClickListener {
                listener.micClick(it, adapterPosition)
            }
        }

        private fun hideShowText(view: View, position: Int, isWord: Boolean){
            val textView = view as TextView
            if (textView.text.isNullOrEmpty()){
                if (isWord) {
                    textView.text = word[position].word
                }
                else {
                    textView.text = word[position].mean
                }
            }else{
                textView.text = null
            }
        }
    }

    fun hideWord() {
        hideWordCheck = !hideWordCheck
        notifyDataSetChanged()
    }

    fun hideMean() {
        hideMeanCheck = !hideMeanCheck
        notifyDataSetChanged()
    }
}