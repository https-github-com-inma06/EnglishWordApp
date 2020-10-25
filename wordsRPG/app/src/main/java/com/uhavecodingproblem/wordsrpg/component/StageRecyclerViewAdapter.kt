package com.uhavecodingproblem.wordsrpg.component

import android.content.Context
import android.graphics.Color
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
            if (item[i].stageStatus == 0)
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

    inner class BasicPackageStageItemViewHolder(val binding: BasicPackageStageDialogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Stage) {

            binding.stageinformation = data
            binding.position = adapterPosition
            binding.endposition = itemCount - 1
            binding.morestage = showMoreStage
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

            setLayout(adapterPosition)

            binding.layoutStageCollapse.setOnClickListener {
                if (!selectedItem.get(adapterPosition)){
                    selectedItem.delete(preSelectPosition)
                    selectedItem.put(adapterPosition, true)
                }else{
                    selectedItem.delete(adapterPosition)
                }

                notifyItemChanged(preSelectPosition)
                notifyItemChanged(adapterPosition)
                preSelectPosition = adapterPosition
            }

            binding.btnMoveSelectionWindowDialog.setOnClickListener {
                listener.onMoveSelectionWindow(it, adapterPosition)
            }

        }

        private fun setLayout(position: Int){
            if (position != setFirstFocus()){
                if (selectedItem.get(position)){
                    binding.layoutStageExpand.setBackgroundColor(context!!.resources!!.getColor(R.color.basic_dialog_expand_color, null))
                    binding.layoutStageCollapse.visibility = View.GONE
                    binding.layoutStageExpand.visibility = View.VISIBLE
                }else{
                    binding.layoutStageCollapse.visibility = View.VISIBLE
                    binding.layoutStageExpand.visibility = View.GONE
                }
            }else{
                if (selectedItem.get(position)){
                    binding.layoutStageExpand.setBackgroundColor(context!!.resources!!.getColor(R.color.basic_dialog_text_color, null))
                    binding.layoutStageCollapse.visibility = View.GONE
                    binding.layoutStageExpand.visibility = View.VISIBLE
                    binding.imgBtnStageExpand.apply {
                        setImageResource(R.drawable.img_vector_white_arrow_drop_up)
                        scaleType = ImageView.ScaleType.FIT_CENTER
                    }
                    binding.ratingBarResult.visibility = View.GONE
                    binding.tvStageExpandName.setTextColor(Color.WHITE)
                    binding.tvStageExpandDescription.visibility = View.VISIBLE
                    binding.tvStageExpandDescription.text = "학습할 차례입니다."
                }else{
                    binding.layoutStageCollapse.setBackgroundColor(context!!.resources!!.getColor(R.color.basic_dialog_text_color, null))
                    binding.layoutStageCollapse.visibility = View.VISIBLE
                    binding.layoutStageExpand.visibility = View.GONE
                }
            }
        }

    }
}