package com.uhavecodingproblem.wordsrpg.component.library.viewpageradapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.data.mockdata.WordInformation
import com.uhavecodingproblem.wordsrpg.data.model.WordsRead
import com.uhavecodingproblem.wordsrpg.databinding.ItemStudyAcitivyViewpagerBinding

/**
 * wordsrpg
 * Class: StudyViewPagerAdapter
 * Created by pyg10.
 * Created On 2020-09-21.
 * Description:
 *
 * study ViewPager2 Adapter
 * no fragment swipe
 * image swipe ViewPager2
 *
 */
class StudyActivityViewPagerAdapter(
    private var word: MutableList<WordsRead>,
    private val listener: ItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val wordSparseBooleanArray = SparseBooleanArray()
    private val meanSparseBooleanArray = SparseBooleanArray()
    private var currentPosition: Int = 0

    private var hideWordCheck = false
    private var hideMeanCheck = false

    interface ItemClickListener {
        fun onMicClick(v: View, position: Int)
        fun onVideoClick(v: View, position: Int)
        fun onNextBtnClick(v: View, position: Int)
        fun onPreviousBtnClick(v: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val studyItemBinding =
            ItemStudyAcitivyViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(studyItemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pagerHolder = holder as PagerViewHolder
        pagerHolder.bind()
    }

    override fun getItemCount(): Int {
        return if (word.isNullOrEmpty()) 0 else word.size
    }

    inner class PagerViewHolder(private val binding: ItemStudyAcitivyViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.wordRead = word[adapterPosition]
            binding.wordarray = wordSparseBooleanArray
            binding.meanarray = meanSparseBooleanArray
            binding.position = adapterPosition
            binding.maxsize = itemCount - 1

            val count = "${adapterPosition + 1} / ${word.size}"
            binding.wordCount.text = count

            binding.word.setOnClickListener {
                if (!wordSparseBooleanArray.get(adapterPosition))
                    wordSparseBooleanArray.put(adapterPosition, true)
                else
                    wordSparseBooleanArray.put(adapterPosition, false)
                // 아이템 변경 애니메이션 제거를 위해 Unit을 전달
                notifyItemChanged(adapterPosition, Unit)
            }

            binding.mean.setOnClickListener {
                if (!meanSparseBooleanArray.get(adapterPosition))
                    meanSparseBooleanArray.put(adapterPosition, true)
                else
                    meanSparseBooleanArray.put(adapterPosition, false)
                notifyItemChanged(adapterPosition, Unit)
            }

            binding.wordMic.setOnClickListener {
                listener.onMicClick(it, adapterPosition)
            }

            binding.btnPrevious.setOnClickListener {
                listener.onPreviousBtnClick(it, adapterPosition)
            }

            binding.btnNext.setOnClickListener {
                listener.onNextBtnClick(it, adapterPosition)
            }

            binding.imvVideoPlay.setOnClickListener {
                listener.onVideoClick(it, adapterPosition)
            }
        }

    }

    fun hideWord() {
        hideWordCheck = !hideWordCheck

        for (i in word.indices) {
            if (hideWordCheck)
                wordSparseBooleanArray.put(i, true)
            else
                wordSparseBooleanArray.put(i, false)
        }
        notifyDataSetChanged()
    }

    fun hideMean() {
        hideMeanCheck = !hideMeanCheck

        for (i in word.indices) {
            if (hideMeanCheck)
                meanSparseBooleanArray.put(i, true)
            else
                meanSparseBooleanArray.put(i, false)
        }
        notifyDataSetChanged()
    }

    fun setCurrentPosition(position: Int) {
        currentPosition = position
    }
}