package com.uhavecodingproblem.wordsrpg.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.Package
import com.uhavecodingproblem.wordsrpg.databinding.DialogMyCustomPackageAddFristBinding

class MyCustomPackageAddFirstDialogFragment:BottomSheetDialogFragment() {
    lateinit var binding:DialogMyCustomPackageAddFristBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_my_custom_package_add_frist, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val packageData = arguments?.getParcelable<Package>("package")
        binding.btnPackageModify.setOnClickListener {
            val packageDialog = MyCustomPackageAddDialogFragment()
            packageDialog.arguments = bundleOf("package" to packageData)
            packageDialog.show(parentFragmentManager,"MyCustomPackageAddDialogFragment")
            dismiss()
        }
        binding.btnWordAdd.setOnClickListener {

        }

    }


}