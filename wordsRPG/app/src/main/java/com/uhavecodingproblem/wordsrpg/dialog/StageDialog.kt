package com.uhavecodingproblem.wordsrpg.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.recyclerviewadpter.StageDialogRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.data.WordType
import com.uhavecodingproblem.wordsrpg.databinding.DialogCustomSnackbarBinding
import com.uhavecodingproblem.wordsrpg.databinding.DialogStageBinding
import com.uhavecodingproblem.wordsrpg.util.Logger
import kotlin.math.ceil

/**
 * wordsrpg
 * Class: BasicPackageDialog
 * Created by pyg10.
 * Created On 2020-10-23.
 * Description:
 * 스테이지를 가진 다이얼로그
 */
class StageDialog(context: Context, private val wordType: WordType) : Dialog(context),
    StageDialogRecyclerViewAdapter.ItemClickListener {

    private lateinit var binding: DialogStageBinding
    private var snackBar: Snackbar? = null
    private var isScrolling: Boolean = false

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

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_stage, null, false)
        binding.run {
            information = wordType
            basicdialog = this@StageDialog
        }
        setContentView(binding.root)
        binding.layoutDialog.clipToOutline = true
        checkStageStatus()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.recyclerviewStage.apply {
            adapter = StageDialogRecyclerViewAdapter(wordType.stage, this@StageDialog)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setFocus(this)
        }
        binding.recyclerviewStage.addOnScrollListener(scrolledListener)
    }

    private fun setFocus(recyclerView: RecyclerView) {
        if (recyclerView.adapter!!.itemCount > 2) {
            recyclerView.scrollToPosition(recyclerView.adapter!!.itemCount - 2)
        } else {
            recyclerView.scrollToPosition(0)
        }
    }

    private fun checkStageStatus() {
        var isStudying = false
        var isTestFail = false
        var studying: String? = null
        var testFail: String? = null
        for (i in wordType.stage.indices) {
            if (wordType.stage[i].stageStatus == 1) {
                studying = "STAGE ${i + 1}의 TEST 를 아직보지않으셨네요.\n 지금보러갈까요?"
                isStudying = true
                break
            } else if (wordType.stage[i].stageStatus == 2) {
                testFail = "STAGE ${i + 1}의 TEST 를 통과 하지못하셨네요. \n 다시보러갈까요?"
                isTestFail = true
                break
            }
        }



        if (isStudying) {
            snackBar = customSnackBar(binding.layoutParent, studying!!)
            snackBar?.show()
        } else if (isTestFail) {
            snackBar = customSnackBar(binding.layoutParent, testFail!!)
            snackBar?.show()
        }
    }

    private fun customSnackBar(coordinatorLayout: CoordinatorLayout, snackBarContent: String): Snackbar {

        val snackBar = Snackbar.make(coordinatorLayout, "", Snackbar.LENGTH_INDEFINITE)

        val customSnackBarViewBinding = DialogCustomSnackbarBinding.inflate(LayoutInflater.from(context))
        customSnackBarViewBinding.tvSnackBarContent.text = snackBarContent

        customSnackBarViewBinding.ivSnackBarExit.setOnClickListener {
            snackBar.dismiss()
        }

        customSnackBarViewBinding.tvSnackBarContent.setOnClickListener {
            Toast.makeText(context, "테스트 보기", Toast.LENGTH_SHORT).show()
        }

        val parentLayout = snackBar.view as Snackbar.SnackbarLayout
        val params = parentLayout.layoutParams as CoordinatorLayout.LayoutParams
        parentLayout.setPadding(0,0,0,0)
        snackBar.view.background = null

        params.width = CoordinatorLayout.LayoutParams.MATCH_PARENT
        params.setMargins(0,0,0,0)

        parentLayout.addView(customSnackBarViewBinding.root)
        return snackBar
    }

    fun exit(v: View) {
        cancel()
    }

    override fun onMoveSelectionWindow(v: View, position: Int) {
        val dialog = StageSelectionDialog(context, wordType.stage[position], wordType.name, wordType.thumbnailImage)
        dialog.show()
        dialogResize(dialog)
        snackBar?.dismiss()
    }

    override fun onFoldButtonSelect(v: View, isExpand: Boolean) {
        snackBar?.dismiss()
    }

    private val scrolledListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            isScrolling = newState == RecyclerView.SCROLL_STATE_DRAGGING
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val recyclerViewItemSizeToDouble = (recyclerView.adapter?.itemCount!! -1).toDouble()
            val dismissSnackBar = ceil(recyclerViewItemSizeToDouble * 2 / 3).toInt()
            val lastVisiblePosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

            if (dismissSnackBar >= lastVisiblePosition && isScrolling) { // RecyclerView 최초 위치에서 Scroll 1/3
                snackBar?.dismiss()
            }
        }
    }

    private fun dialogResize(dialog: Dialog) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        if (Build.VERSION.SDK_INT < 30) {

            val display = windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val window = dialog.window

            val x = (size.x * 0.95f).toInt()
            val y = (size.y * 0.5f).toInt()
            window?.setLayout(x, y)

        } else {

            val rect = windowManager.currentWindowMetrics.bounds

            val window = dialog.window

            val x = (rect.width() * 0.95f).toInt()
            val y = (rect.height() * 0.5f).toInt()

            window?.setLayout(x, y)
        }
    }

    override fun cancel() {
        super.cancel()
        binding.recyclerviewStage.removeOnScrollListener(scrolledListener)
    }

    override fun dismiss() {
        super.dismiss()
        binding.recyclerviewStage.removeOnScrollListener(scrolledListener)
    }
}