package com.uhavecodingproblem.wordsrpg.ui.dialog

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.ResponseBasicPackage
import com.uhavecodingproblem.wordsrpg.databinding.DialogStageSelectionNoneBinding
import com.uhavecodingproblem.wordsrpg.databinding.DialogStageSelectionOtherBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.library.StudyActivity
import com.uhavecodingproblem.wordsrpg.ui.activity.library.TestActivity
import com.uhavecodingproblem.wordsrpg.util.dialogResize

/**
 * wordsrpg
 * Class: StageSelectionDialogFragment
 * Created by pyg10.
 * Created On 2020-12-27.
 * Description:
 */
class StageSelectionDialogFragment : DialogFragment() {

    private lateinit var stageItem: ResponseBasicPackage.Stage
    private lateinit var currentPackage: ResponseBasicPackage.BasicPackage
    private val binding get() = _binding
    private var _binding: ViewBinding? = null

    companion object {
        fun newInstance(
            basicPackage: ResponseBasicPackage.BasicPackage,
            stage: ResponseBasicPackage.Stage
        ): StageSelectionDialogFragment {
            val fragment = StageSelectionDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable("currentBasicPackage", basicPackage)
            bundle.putParcelable("selectStage", stage)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            bundle.getParcelable<ResponseBasicPackage.BasicPackage>("currentBasicPackage")?.let {
                currentPackage = it
            }
            bundle.getParcelable<ResponseBasicPackage.Stage>("selectStage")?.let {
                stageItem = it
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = if (stageItem.stageTestStatus == "none" && stageItem.stageLearningStatus == "not_learning")
            DataBindingUtil.inflate(inflater, R.layout.dialog_stage_selection_none, container, false)
        else
            DataBindingUtil.inflate(inflater, R.layout.dialog_stage_selection_other, container, false)
        return binding?.root
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
        binding?.let {
            it.apply {
                if (stageItem.stageTestStatus == "none" && stageItem.stageLearningStatus == "not_learning") {
                    val bind = this as DialogStageSelectionNoneBinding
                    bind.apply {
                        basicPackge = currentPackage
                        stage = stageItem
                        dialog = this@StageSelectionDialogFragment

                        layoutSelectionDialog.clipToOutline = true
                    }
                } else {
                    val bind = this as DialogStageSelectionOtherBinding
                    bind.apply {
                        basicPackge = currentPackage
                        stage = stageItem
                        dialog = this@StageSelectionDialogFragment

                        layoutSelectionDialog.clipToOutline = true
                    }
                }
            }
        }
    }

    fun exit() {
        val stageSelectionDialogFragment = parentFragmentManager.findFragmentByTag("OptionDialog")
        if (stageSelectionDialogFragment != null)
            (stageSelectionDialogFragment as StageSelectionDialogFragment).dismiss()
    }

    fun moveStudy() {
        Intent(requireContext(), StudyActivity::class.java).also {
            it.putExtra("currentBasicPackage", currentPackage)
            it.putExtra("selectStage", stageItem)
            requireContext().startActivity(it)
            dismiss()
        }
    }

    fun moveTest() {
        Intent(requireContext(), TestActivity::class.java).also {
            it.putExtra("currentBasicPackage", currentPackage)
            it.putExtra("selectStage", stageItem)
            requireActivity().startActivity(it)
            dismiss()
        }
    }

    override fun onResume() {
        super.onResume()

        requireContext().dialogResize(this@StageSelectionDialogFragment, 0.9f, 0.45f)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}