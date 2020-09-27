package com.uhavecodingproblem.wordsrpg.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
class MemorizationViewPagerAdapter(private var word: List<String>, private val listener: ItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ItemClickListener{
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

        fun bind(alphabet: String) {
            val count = "${adapterPosition+1} / ${word.size}"
            binding.wordCount.text = count
            binding.word.text = alphabet
            binding.mean.text = alphabet

            binding.wordMic.setOnClickListener {
                listener.micClick(it, adapterPosition)
            }
        }
    }
}