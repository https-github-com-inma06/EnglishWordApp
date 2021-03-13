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
import com.uhavecodingproblem.wordsrpg.data.mockdata.StageInformation
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

    private lateinit var stageInformation: StageInformation
    private var packageName = ""
    private var thumbnailUri = ""
    private lateinit var binding: ViewBinding

    companion object {
        fun newInstance(stageInformation: StageInformation, packageName: String, thumbnailUrl: String): StageSelectionDialogFragment {
            val fragment = StageSelectionDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable("stage", stageInformation)
            bundle.putString("packageName", packageName)
            bundle.putString("thumbnailUrl", thumbnailUrl)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        stageInformation = arguments?.getParcelable("stage")!!
        packageName = arguments?.getString("packageName", "unKnown Title")!!
        thumbnailUri = arguments?.getString("thumbnailUrl", "unKnown ThumbNailUri")!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = if (stageInformation.stageStatus == 0)
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

    private fun setInit(){
        binding.apply {
            if (stageInformation.stageStatus == 0){
                val bind = this as DialogStageSelectionNoneBinding
                bind.apply {
                    name = packageName
                    thumbnailuri = thumbnailUri
                    stage = stageInformation
                    dialog = this@StageSelectionDialogFragment

                    layoutSelectionDialog.clipToOutline = true
                }
            }else{
                val bind = this as DialogStageSelectionOtherBinding
                bind.apply {
                    name = packageName
                    thumbnailuri = thumbnailUri
                    stage = stageInformation
                    dialog = this@StageSelectionDialogFragment

                    layoutSelectionDialog.clipToOutline = true
                }
            }


        }

    }

    fun exit(){
        Logger.v("Exit")
        val stageSelectionDialogFragment = parentFragmentManager.findFragmentByTag("SelectionDialog")
        if (stageSelectionDialogFragment != null)
            (stageSelectionDialogFragment as StageSelectionDialogFragment).dismiss()
    }

    fun moveStudy(){
        Logger.v("Move")
        Intent(requireContext(), StudyActivity::class.java).also {
            it.putExtra("PackageName", packageName)
            it.putExtra("StudyWord", stageInformation)
            requireContext().startActivity(it)
            dismiss()
        }
    }

    fun moveTest(){
        Logger.v("MoveTest")
    }

    override fun onResume() {
        super.onResume()

        requireContext().dialogResize(this@StageSelectionDialogFragment, 0.9f, 0.45f)
        Logger.v("StageSelectionDialogFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        Logger.v("StageSelectionDialogFragment onPause")
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Logger.v("StageSelectionDialogFragment onCancel")
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Logger.v("StageSelectionDialogFragment onDismiss")
    }
}