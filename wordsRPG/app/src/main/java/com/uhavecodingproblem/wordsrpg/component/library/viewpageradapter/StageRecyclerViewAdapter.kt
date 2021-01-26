package com.uhavecodingproblem.wordsrpg.component.library.viewpageradapter

import android.content.Context
import android.graphics.Color
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.Stage
import com.uhavecodingproblem.wordsrpg.databinding.BasicPackageStageDialogItemBinding
import com.uhavecodingproblem.wordsrpg.util.*

/**
 * wordsrpg
 * Class: LibraryActivityRecyclerViewAdapter
 * Created by pyg10.
 * Created On 2020-10-03.
 * Description:
 */
class StageRecyclerViewAdapter(
    val item: MutableList<Stage>,
    private val listener: ItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var context: Context? = null
    private val selectedItem = SparseBooleanArray()
    private var preSelectPosition: Int
    private var showStageData = mutableListOf<Stage>()
    private var showMoreStage: String? = null // 마지막부분 출력해줄 내용
    private val studyStage = "학습 할 차례입니다."

    init {
        setShowItem()
        if (showStageData.size == item.size) showMoreStage =  "STAGE ${showStageData.size}"
        else if (showStageData.size < item.size) showMoreStage =  "STAGE ${showStageData.size} ~ STAGE ${item.size - 1}"
        preSelectPosition = setFirstFocus()
        selectedItem.put(preSelectPosition, true)
    }

    private fun setFirstFocus(): Int { // 최초 포커스를 가지고있는 포지션
        //TODO : 아마 스테이지는 항상 2개이상이여서 조건문 필요없을듯?
        return if (itemCount > 2)
            itemCount - 2
        else
            0
    }

    private fun setShowItem() { // 스테이지 보여질 갯수 + 1 (마지막부분을 출력해주어야하기때문)
        var count = 0
        for (i in item.indices) {
            if (item[i].stageStatus != 3)
                count++

            showStageData.add(item[i])

            if (count > 1)
                break
        }
    }

    interface ItemClickListener {
        fun onMoveSelectionWindow(v: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return BasicPackageStageItemViewHolder(
            BasicPackageStageDialogItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BasicPackageStageItemViewHolder).bind(item[position])
    }

    override fun getItemCount(): Int {
        return if (showStageData.isNullOrEmpty()) 0 else showStageData.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (item[position].stageStatus) {
            0 -> STAGE_NONE
            1 -> STAGE_STUDYING
            2 -> STAGE_TEST_FAIL
            else -> STAGE_TEST_CLEAR
        }
    }

    inner class BasicPackageStageItemViewHolder(val binding: BasicPackageStageDialogItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private var collapseView: View? = binding.layoutStageCollapse
        private var expandView: View? = binding.layoutStageExpand
        private var ratingBar: RatingBar? = binding.ratingBarResult
        private var textResult: TextView? = binding.tvStageExpandDescription

        fun bind(data: Stage) {

            binding.stageinformation = data
            binding.position = adapterPosition
            binding.endposition = itemCount - 1
            binding.morestage = showMoreStage
            binding.study = studyStage
            binding.layoutDialogItem.clipToOutline = true

            if (adapterPosition == itemCount - 1) {
                binding.layoutStageCollapse.setBackgroundColor(Color.GRAY)
                binding.layoutStageCollapse.isEnabled = false
                binding.tvStageCollapseName.setTextColor(Color.WHITE)
                binding.imgBtnStageCollapse.apply {
                    setImageResource(R.drawable.img_vector_white_lock)
                    scaleType = ImageView.ScaleType.CENTER
                }
            }

            binding.imgBtnStageCollapse.setOnClickListener {
                changeSparseBooleanArray(adapterPosition)

                //openCloseStage(binding.layoutStageExpand)
            }
            binding.imgBtnStageExpand.setOnClickListener {
                //openCloseStage(binding.layoutStageCollapse)
                changeSparseBooleanArray(adapterPosition)
            }

            binding.btnMoveSelectionWindowDialog.setOnClickListener {
                listener.onMoveSelectionWindow(it, adapterPosition)
            }

            openCloseStage(adapterPosition)
        }

        private fun changeSparseBooleanArray(position: Int){
            if (!selectedItem.get(position)){
                selectedItem.delete(preSelectPosition)
                selectedItem.put(position, true)
            }else{
                selectedItem.delete(position)
            }

            notifyItemChanged(preSelectPosition)
            notifyItemChanged(position)
            preSelectPosition = position
        }

        private fun openCloseStage(position: Int) {
            if (selectedItem.get(position)){
                collapseView?.visibility = View.GONE
                expandView?.visibility = View.VISIBLE
                if (position == setFirstFocus()) {
                    expandView?.setBackgroundColor(context!!.resources!!.getColor(R.color.basic_dialog_text_color, null))
                    ratingBar?.visibility = View.GONE
                    textResult?.visibility = View.VISIBLE
                }
            }else{
                expandView?.visibility = View.GONE
                collapseView?.visibility = View.VISIBLE
                if (position == setFirstFocus()){
                    collapseView?.setBackgroundColor(context!!.resources!!.getColor(R.color.basic_dialog_text_color,null))
                }
            }
        }

        //재이님이 주신방법 접었다 폈다 가능하지만, 이전에 펼쳐진 것을 접을 수 가없음.

//        private var openedStage: View? = binding.layoutStageCollapse
//        private fun openCloseStage(currStage: View) {
//            var currStage: View? = currStage
//            if (openedStage === currStage) {
//                currStage = null
//            }
//            if (openedStage != null) {
//                openedStage!!.visibility = View.GONE
//                openedStage = null
//            }
//            if (currStage != null) {
//                currStage.visibility = View.VISIBLE
//                openedStage = currStage
//            }
//        }

    }
}