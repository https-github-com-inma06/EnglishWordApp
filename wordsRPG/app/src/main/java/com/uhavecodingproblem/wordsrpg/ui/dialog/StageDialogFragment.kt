package com.uhavecodingproblem.wordsrpg.ui.dialog

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.recyclerviewadapter.StageDialogRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.data.model.ResponseBasicPackage
import com.uhavecodingproblem.wordsrpg.databinding.DialogCustomSnackbarBinding
import com.uhavecodingproblem.wordsrpg.databinding.DialogStageBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.library.TestActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import com.uhavecodingproblem.wordsrpg.util.LinearLayoutWrapper
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.util.dialogResize
import java.util.*
import kotlin.math.ceil

/**
 * wordsrpg
 * Class: StageDialogFragment
 * Created by pyg10.
 * Created On 2020-12-26.
 * Description:
 */
class StageDialogFragment : BaseUtility.BaseDialogFragment<DialogStageBinding>(R.layout.dialog_stage),
    StageDialogRecyclerViewAdapter.ItemClickListener {

    private lateinit var stageDialogRecyclerViewAdapter: StageDialogRecyclerViewAdapter
    private lateinit var currentPackage: ResponseBasicPackage.BasicPackage
    private var stageItem = mutableListOf<ResponseBasicPackage.Stage>()
    private var snackbar: Snackbar? = null
    private var isScrolling = false
    private var isFirstCreate = false

    companion object {
        fun newInstance(basicPackage: ResponseBasicPackage.BasicPackage): StageDialogFragment {
            val dialogFragment = StageDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable("basicPackage", basicPackage)
            dialogFragment.arguments = bundle
            return dialogFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getParcelable<ResponseBasicPackage.BasicPackage>("basicPackage")?.let {
            currentPackage = it
        }
    }

    override fun DialogStageBinding.onCreateView() {
    }

    override fun DialogStageBinding.onViewCreated() {
        val layoutParams = WindowManager.LayoutParams().apply {
            flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            dimAmount = 0.8f
        }

        dialog?.window?.apply {
            attributes = layoutParams
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestFeature(Window.FEATURE_NO_TITLE)
        }

        setInit()
    }

    private fun setUpRecyclerData() {
        stageItem.clear()
        stageItem.addAll(currentPackage.stageList)
        stageDialogRecyclerViewAdapter.submitList(stageItem)
    }


    private fun setInit() {
        binding.apply {
            stage = this@StageDialogFragment
            this.basicPackage = currentPackage

            layoutDialog.clipToOutline = true

            setRecyclerView()
            stageStatus()
        }
    }

    private fun setRecyclerView() {
        binding.recyclerviewStage.apply {
            stageDialogRecyclerViewAdapter =
                StageDialogRecyclerViewAdapter(this@StageDialogFragment)
            adapter = stageDialogRecyclerViewAdapter
            layoutManager = LinearLayoutWrapper(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        setUpRecyclerData()
        setFocus()
    }

    private fun setFocus() {
        binding.recyclerviewStage.apply {
            adapter?.let {
                if (it.itemCount > 2)
                    this.scrollToPosition(it.itemCount - 2)
                else
                    this.scrollToPosition(0)
            }
        }
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
                snackbar?.dismiss()
            }
        }
    }

    private fun stageStatus() {
        for (i in stageItem.indices) {
            if (stageItem[i].stageLockStatus == "unlock" && stageItem[i].stageTestStatus == "none") {
                snackbar = customSnackBar(binding.layoutParent, "STAGE ${i + 1}의 테스트를 아직 보지않으셨네요. \n지금보러갈까요?")
                snackbar?.show()
                break
            } else if (stageItem[i].stageLockStatus == "unlock" && stageItem[i].stageTestStatus == "fail") {
                snackbar = customSnackBar(binding.layoutParent, "STAGE ${i + 1}의 테스트를 통과하지 못하셨네요. \n다시보러갈까요?")
                snackbar?.show()
                break
            }
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
            findOtherStatus()
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

    private fun findOtherStatus() {
        for (i in stageItem.indices) {
            if (stageItem[i].stageLockStatus == "unlock" && stageItem[i].stageTestStatus != "pass") {
                moveStudyOrTest(stageItem[i])
                return
            }
        }
    }

    private fun moveStudyOrTest(stage: ResponseBasicPackage.Stage) {
        Intent(requireContext(), TestActivity::class.java).also {
            it.putExtra("currentBasicPackage", currentPackage)
            it.putExtra("selectStage", stage)
            requireActivity().startActivity(it)
        }
    }

    fun exit() {
        Logger.d("Exit")
        val stageDialogFragment = parentFragmentManager.findFragmentByTag("StageDialog")
        if (stageDialogFragment != null) {
            (stageDialogFragment as StageDialogFragment).dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        setUpRecyclerData()
        context?.dialogResize(this@StageDialogFragment, 0.9f, 0.9f)
        binding.recyclerviewStage.addOnScrollListener(scrolledListener)
    }

    override fun onPause() {
        super.onPause()
        binding.recyclerviewStage.removeOnScrollListener(scrolledListener)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        binding.recyclerviewStage.removeOnScrollListener(scrolledListener)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        binding.recyclerviewStage.removeOnScrollListener(scrolledListener)
    }

    override fun onItemClick() {
        snackbar?.dismiss()
    }

    override fun onMoveOption(position: Int) {
        val dialogFragment = StageSelectionDialogFragment.newInstance(currentPackage, stageItem[position])
        dialogFragment.show(childFragmentManager, "OptionDialog")
        snackbar?.dismiss()
    }

}