package com.uhavecodingproblem.wordsrpg.dialog

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.recyclerviewadpter.StageDialogRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.PackageObserveViewModel
import com.uhavecodingproblem.wordsrpg.data.PackageInformation
import com.uhavecodingproblem.wordsrpg.data.StageInformation
import com.uhavecodingproblem.wordsrpg.databinding.DialogCustomSnackbarBinding
import com.uhavecodingproblem.wordsrpg.databinding.DialogStageBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.library.StudyActivity
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
class StageDialogFragment : DialogFragment(), StageDialogRecyclerViewAdapter.ItemClickListener {

    private lateinit var binding: DialogStageBinding
    private lateinit var stageDialogRecyclerViewAdapter: StageDialogRecyclerViewAdapter
    private val packageObserveViewModel by viewModels<PackageObserveViewModel>({requireParentFragment()})
    private lateinit var packageInformation: PackageInformation
    private var snackbar: Snackbar? = null
    private var isScrolling = false

    companion object {
        fun newInstance(packageName: String): StageDialogFragment {
            val dialogFragment = StageDialogFragment()
            val bundle = Bundle()
            bundle.putString("name", packageName)
            dialogFragment.arguments = bundle
            return dialogFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        packageInformation = arguments?.getString("name")?.let { packageObserveViewModel.getPackage(it) }!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_stage, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    private fun setInit() {
        binding.apply {
            stage = this@StageDialogFragment
            packageinfo = packageInformation

            layoutDialog.clipToOutline = true

            setRecyclerView()
            stageStatus()
        }
    }

    private fun setRecyclerView() {
        binding.recyclerviewStage.apply {
            stageDialogRecyclerViewAdapter =
                StageDialogRecyclerViewAdapter(packageInformation.stageList, this@StageDialogFragment)
            adapter = stageDialogRecyclerViewAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        setFocus()
    }

    private fun setFocus(){
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
        for (i in packageInformation.stageList.indices) {
            if (packageInformation.stageList[i].stageStatus == 1) {
                snackbar = customSnackBar(binding.layoutParent, "STAGE ${i + 1}의 테스트를 아직 보지않으셨네요. \n지금보러갈까요?")
                snackbar?.show()
                break
            } else if (packageInformation.stageList[i].stageStatus == 2) {
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
        for (i in packageInformation.stageList.indices) {
            if (packageInformation.stageList[i].stageStatus != 0) {
                moveStudyOrTest(packageInformation.stageList[i])
                return
            }
        }
    }

    private fun moveStudyOrTest(stageInformation: StageInformation) {
        when (stageInformation.stageNum) {
            1 -> {
                Intent(requireContext(), StudyActivity::class.java).also {
                    it.putExtra("PackageName", packageInformation.name)
                    it.putExtra("StudyWord", stageInformation)
                    startActivity(it)
                }
            }
            2 -> {
                Logger.v("Move Test")
            }
            3 -> {
                Logger.v("Move Test")
            }
            else -> throw IllegalStateException("StageStatus Strange")
        }

    }

    fun exit() {
        Logger.v("Exit")
        val stageDialogFragment = parentFragmentManager.findFragmentByTag("StageDialog")
        if (stageDialogFragment != null) {
            (stageDialogFragment as StageDialogFragment).dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        packageInformation = arguments?.getString("name")?.let { packageObserveViewModel.getPackage(it) }!!
        context?.dialogResize(this@StageDialogFragment, 0.9f, 0.9f)
        binding.recyclerviewStage.addOnScrollListener(scrolledListener)
        Logger.v("StageDialogFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        Logger.v("StageDialogFragment onPause")
        binding.recyclerviewStage.removeOnScrollListener(scrolledListener)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Logger.v("StageDialogFragment onCancel")
        binding.recyclerviewStage.removeOnScrollListener(scrolledListener)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Logger.v("StageDialogFragment onDismiss")
        binding.recyclerviewStage.removeOnScrollListener(scrolledListener)
    }

    override fun onMoveSelectionWindow(v: View, position: Int) {
        Logger.v("MoveSelectionWindow")
        val dialogFragment = StageSelectionDialogFragment.newInstance(
            packageInformation.stageList[position],
            packageInformation.name,
            packageInformation.thumbnailImage
        )
        dialogFragment.show(childFragmentManager, "SelectionDialog")
        snackbar?.dismiss()
    }

    override fun onFoldButtonSelect(v: View, isExpand: Boolean) {
        snackbar?.dismiss()
    }
}