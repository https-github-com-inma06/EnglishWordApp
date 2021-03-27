package com.uhavecodingproblem.wordsrpg.ui.dialog

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.uhavecodingproblem.wordsrpg.R

class MyCustomPackageModifyDialogFragment:BottomSheetDialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)

    }


}