package com.uhavecodingproblem.wordsrpg.ui.dialog

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.recyclerviewadapter.StageDialogRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.PackageObserveViewModel
import com.uhavecodingproblem.wordsrpg.data.model.Learning
import com.uhavecodingproblem.wordsrpg.data.model.PackageWithStage
import com.uhavecodingproblem.wordsrpg.databinding.DialogCustomSnackbarBinding
import com.uhavecodingproblem.wordsrpg.databinding.DialogStageBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.library.TestActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
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
    private val packageObserveViewModel by viewModels<PackageObserveViewModel>({ requireParentFragment() })
    private lateinit var currentPackage: PackageWithStage
    private var stage = mutableListOf<Learning>()
    private var snackbar: Snackbar? = null
    private var isScrolling = false
    private var isFirstCreate = false

    companion object {
        fun newInstance(packageWithStage: PackageWithStage): StageDialogFragment {
            val dialogFragment = StageDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable("packageWithStage", packageWithStage)
            dialogFragment.arguments = bundle
            return dialogFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getParcelable<PackageWithStage>("packageWithStage")?.let {
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
        stage = packageObserveViewModel.filterStage(currentPackage.p_id)
        if (!isFirstCreate) {
            var position = 0
            for (i in stage.indices) {
                if (stage[i].stage_status != "3") {
                    position = i
                    break
                }
            }
            stageDialogRecyclerViewAdapter.setUpFirstFocus(position)
        }
        isFirstCreate = true
        stageDialogRecyclerViewAdapter.submitList(stage)
    }


    private fun setInit() {
        binding.apply {
            stage = this@StageDialogFragment
            this.packageWithStage = currentPackage

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
            layoutManager = LinearLayoutManager(requireContext())
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
        for (i in stage.indices) {
            if (stage[i].stage_status == "1") {
                snackbar = customSnackBar(binding.layoutParent, "STAGE ${i + 1}의 테스트를 아직 보지않으셨네요. \n지금보러갈까요?")
                snackbar?.show()
                break
            } else if (stage[i].stage_status == "2") {
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
        for (i in stage.indices) {
            if (stage[i].stage_status == "1" || stage[i].stage_status == "2") {
                moveStudyOrTest(stage[i])
                return
            }
        }
    }

    private fun moveStudyOrTest(learning: Learning) {
        when (learning.stage_status) {
            "1" -> {
                Intent(requireContext(), TestActivity::class.java).also {
                    it.putExtra("packageWithStage", currentPackage)
                    it.putExtra("test", learning)
                    requireActivity().startActivity(it)
                    dismiss()
                }
            }
            "2" -> {
                Intent(requireContext(), TestActivity::class.java).also {
                    it.putExtra("packageWithStage", currentPackage)
                    it.putExtra("test", learning)
                    requireActivity().startActivity(it)
                    dismiss()
                }
            }
            else -> throw IllegalStateException("StageStatus Strange")
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
        val dialogFragment = StageSelectionDialogFragment.newInstance(currentPackage, stage[position])
        dialogFragment.show(childFragmentManager, "OptionDialog")
        snackbar?.dismiss()
    }

}