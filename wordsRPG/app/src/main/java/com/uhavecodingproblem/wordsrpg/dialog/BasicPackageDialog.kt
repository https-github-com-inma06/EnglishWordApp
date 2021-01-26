package com.uhavecodingproblem.wordsrpg.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.StageRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.data.WordType
import com.uhavecodingproblem.wordsrpg.databinding.BasicPackageDialogBinding

/**
 * wordsrpg
 * Class: BasicPackageDialog
 * Created by pyg10.
 * Created On 2020-10-23.
 * Description:
 * 스테이지를 가진 다이얼로그
 */
class BasicPackageDialog(context: Context, private val wordType: WordType): Dialog(context), StageRecyclerViewAdapter.ItemClickListener {

    private lateinit var binding: BasicPackageDialogBinding
    private val studying: String = "학습중인 스테이지가 있네요.\n 지금보러갈까요?"
    private val testFail: String = "테스트통과를 하지못하셨네요. \n 다시보러갈까요?"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutParam = WindowManager.LayoutParams().apply {
            flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            dimAmount = 0.8f
        }
        //setCancelable(false)
        window?.let {
            it.attributes = layoutParam
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.requestFeature(Window.FEATURE_NO_TITLE)
        }

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.basic_package_dialog, null, false)
        binding.run {
            information = wordType
            basicdialog = this@BasicPackageDialog
        }
        setContentView(binding.root)
        binding.layoutDialog.clipToOutline = true
        binding.layoutRemindStageInformation.clipToOutline = true
        checkStageStatus()
        setRecyclerView()
    }

    private fun setRecyclerView(){
        binding.recyclerviewStage.apply {
            adapter = StageRecyclerViewAdapter(wordType.stage, this@BasicPackageDialog)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setFocus(this)
        }
    }

    private fun setFocus(recyclerView: RecyclerView){
        if (recyclerView.adapter!!.itemCount > 2){
            recyclerView.scrollToPosition(recyclerView.adapter!!.itemCount - 2)
        }else{
            recyclerView.scrollToPosition(0)
        }
    }

    private fun checkStageStatus(){
        var checking = false
        for (i in wordType.stage.indices){
            if (wordType.stage[i].stageStatus == 1 || wordType.stage[i].stageStatus == 2) {
                checking = true
                break
            }
        }

        if (checking) {
            binding.layoutRemindStageInformation.visibility = View.VISIBLE
            for (i in wordType.stage.indices) {
                if (wordType.stage[i].stageStatus == 1)
                    binding.tvRemindStageInformation.text = studying
                else if (wordType.stage[i].stageStatus == 2)
                    binding.tvRemindStageInformation.text = testFail
            }
        }
    }

    fun exit(v: View){
        cancel()
    }

    fun moveText(v: View){
        binding.layoutRemindStageInformation.visibility = View.INVISIBLE
        Toast.makeText(context, "테스트보러가기", Toast.LENGTH_SHORT).show()
    }

    fun remindExit(v: View){
        binding.layoutRemindStageInformation.visibility = View.INVISIBLE
    }

    override fun onMoveSelectionWindow(v: View, position: Int) {
        val dialog = BasicPackageSelectionDialog(context, wordType.stage[position], wordType.name, wordType.thumbnailImage)
        dialog.show()
        dialogResize(dialog)
    }

    private fun dialogResize(dialog: Dialog) {

        if (Build.VERSION.SDK_INT < 30) {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val window = dialog.window

            val x = (size.x * 0.95f).toInt()
            val y = (size.y * 0.6f).toInt()
            window?.setLayout(x, y)

        }else{
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

            val rect = windowManager.currentWindowMetrics.bounds

            val window = dialog.window

            val x = (rect.width() * 0.95f).toInt()
            val y = (rect.height() * 0.6f).toInt()

            window?.setLayout(x, y)
        }
    }
}