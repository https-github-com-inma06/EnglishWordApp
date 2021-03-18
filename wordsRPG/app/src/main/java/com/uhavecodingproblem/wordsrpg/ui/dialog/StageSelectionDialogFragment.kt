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
import com.uhavecodingproblem.wordsrpg.data.model.Learning
import com.uhavecodingproblem.wordsrpg.data.model.PackageWithStage
import com.uhavecodingproblem.wordsrpg.databinding.DialogStageSelectionNoneBinding
import com.uhavecodingproblem.wordsrpg.databinding.DialogStageSelectionOtherBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.library.StudyActivity
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.util.dialogResize

/**
 * wordsrpg
 * Class: StageSelectionDialogFragment
 * Created by pyg10.
 * Created On 2020-12-27.
 * Description:
 */
class StageSelectionDialogFragment : DialogFragment() {

    private lateinit var stage: Learning
    private lateinit var currentPackage: PackageWithStage
    private lateinit var binding: ViewBinding

    companion object {
        fun newInstance(packageWithStage: PackageWithStage, stage: Learning): StageSelectionDialogFragment {
            val fragment = StageSelectionDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable("packageWithStage", packageWithStage)
            bundle.putParcelable("stage", stage)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            bundle.getParcelable<PackageWithStage>("packageWithStage")?.let {
                currentPackage = it
            }
            bundle.getParcelable<Learning>("stage")?.let {
                stage = it
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = if (stage.stage_status == "0")
            DataBindingUtil.inflate(inflater, R.layout.dialog_stage_selection_none, container, false)
        else
            DataBindingUtil.inflate(inflater, R.layout.dialog_stage_selection_other, container, false)
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
            if (stage.stage_status == "0"){
                val bind = this as DialogStageSelectionNoneBinding
                bind.apply {
                    name = currentPackage.package_name
                    thumbnailuri = currentPackage.package_thumbnail
                    learning = stage
                    dialog = this@StageSelectionDialogFragment

                    layoutSelectionDialog.clipToOutline = true
                }
            } else {
                val bind = this as DialogStageSelectionOtherBinding
                bind.apply {
                    name = currentPackage.package_name
                    thumbnailuri = currentPackage.package_thumbnail
                    learning = stage
                    dialog = this@StageSelectionDialogFragment

                    layoutSelectionDialog.clipToOutline = true
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
            it.putExtra("packageWithStage", currentPackage)
            it.putExtra("stage", stage)
            requireContext().startActivity(it)
            dismiss()
        }
    }

    fun moveTest() {
        Logger.d("MoveTest")
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
}