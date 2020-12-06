package com.uhavecodingproblem.wordsrpg.dialog

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.recyclerviewadpter.StageDialogRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.data.PackageInformation
import com.uhavecodingproblem.wordsrpg.databinding.DialogCustomSnackbarBinding
import com.uhavecodingproblem.wordsrpg.databinding.DialogStageBinding
import com.uhavecodingproblem.wordsrpg.util.dialogResize
import kotlin.math.ceil

/**
 * wordsrpg
 * Class: BasicPackageDialog
 * Created by pyg10.
 * Created On 2020-10-23.
 * Description:
 * 스테이지를 가진 다이얼로그
 */
class StageDialog(context: Context, private val packageInformation: PackageInformation) : Dialog(context),
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
            packageinfo = packageInformation
            basicdialog = this@StageDialog
        }
        setContentView(binding.root)
        binding.layoutDialog.clipToOutline = true
        checkStageStatus()
        setRecyclerView()
        LocalBroadcastManager.getInstance(context).registerReceiver(localBroadcast, IntentFilter("refresh_dialog"))
    }

    private val localBroadcast: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(receiveContext: Context?, receiveIntent: Intent?) {
            if ("refresh_dialog" == receiveIntent?.action) {
                (binding.recyclerviewStage.adapter as StageDialogRecyclerViewAdapter).updateData(packageInformation.stageList)
                checkStageStatus()
            }
        }
    }

    private fun setRecyclerView() {
        binding.recyclerviewStage.apply {
            adapter = StageDialogRecyclerViewAdapter(packageInformation.stageList, this@StageDialog)
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
        for (i in packageInformation.stageList.indices) {
            if (packageInformation.stageList[i].stageStatus == 1) {
                studying = "STAGE ${i + 1}의 TEST 를 아직보지않으셨네요.\n 지금보러갈까요?"
                isStudying = true
                break
            } else if (packageInformation.stageList[i].stageStatus == 2) {
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
            findTestStage()
            snackBar.dismiss()
        }

        val parentLayout = snackBar.view as Snackbar.SnackbarLayout
        val params = parentLayout.layoutParams as CoordinatorLayout.LayoutParams
        parentLayout.setPadding(0, 0, 0, 0)
        snackBar.view.background = null

        params.width = CoordinatorLayout.LayoutParams.MATCH_PARENT
        params.setMargins(0, 0, 0, 0)

        parentLayout.addView(customSnackBarViewBinding.root)
        return snackBar
    }

    private fun findTestStage() {
        var position = 0
        for (i in packageInformation.stageList.indices) {
            if (packageInformation.stageList[i].stageStatus != 0 && packageInformation.stageList[i].stageStatus != 3) {
                position = i
                break
            }
        }

        Toast.makeText(context, "${packageInformation.stageList[position].stageNum}", Toast.LENGTH_SHORT).show()
    }

    fun exit(v: View) {
        cancel()
    }

    override fun onMoveSelectionWindow(v: View, position: Int) {
        val dialog = StageSelectionDialog(
            context,
            packageInformation.stageList[position],
            packageInformation.name,
            packageInformation.thumbnailImage
        )
        dialog.show()
        context.dialogResize(dialog, 0.95f, 0.5f)
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
            val recyclerViewItemSizeToDouble = (recyclerView.adapter?.itemCount!! - 1).toDouble()
            val dismissSnackBar = ceil(recyclerViewItemSizeToDouble * 2 / 3).toInt()
            val lastVisiblePosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

            if (dismissSnackBar >= lastVisiblePosition && isScrolling) { // RecyclerView 최초 위치에서 Scroll 1/3
                snackBar?.dismiss()
            }
        }
    }

    override fun cancel() {
        super.cancel()
        binding.recyclerviewStage.removeOnScrollListener(scrolledListener)
        LocalBroadcastManager.getInstance(context).unregisterReceiver(localBroadcast)
    }

    override fun dismiss() {
        super.dismiss()
        binding.recyclerviewStage.removeOnScrollListener(scrolledListener)
        LocalBroadcastManager.getInstance(context).unregisterReceiver(localBroadcast)
    }
}