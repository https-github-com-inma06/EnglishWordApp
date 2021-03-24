package com.uhavecodingproblem.wordsrpg.ui.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.recyclerviewadapter.MyCustomPackageAddWordRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.data.model.Package
import com.uhavecodingproblem.wordsrpg.data.model.WordsRead
import com.uhavecodingproblem.wordsrpg.databinding.DialogMyCustomPackageAddWordBinding


class MyCustomPackageAddWordDialogFragment : DialogFragment() {
    lateinit var binding: DialogMyCustomPackageAddWordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.myCustomAddWordDialog_bg)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.dialog_my_custom_package_add_word, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val packageData = arguments?.getParcelable<Package>("package")
            val wordList = arguments?.getParcelableArrayList<WordsRead>("wordList")
                ?: mutableListOf<WordsRead>()

            rvWordList.adapter = MyCustomPackageAddWordRecyclerViewAdapter(wordList)
            btnAdd.setOnClickListener {
                val word = etWord.text.toString()
                val meaning = etMeaning.text.toString()
                if (word == "" || meaning == "")
                    Toast.makeText(requireActivity(), "추가할 단어나 뜻을 적어주세요", Toast.LENGTH_SHORT).show()
                else if (!word.matches("^[a-zA-Z]*$".toRegex()))
                    Toast.makeText(requireActivity(), "단어나 뜻을 영어로 적어주세요", Toast.LENGTH_SHORT).show()
                else {
                    val item = WordsRead("", word, meaning, "", "0")
                    (rvWordList.adapter as MyCustomPackageAddWordRecyclerViewAdapter).apply {
                        list.add(item)
                        notifyDataSetChanged()
                    }
                }
            }
            ivGoBack.setOnClickListener {
                val wordDialog = MyCustomPackageAddCompleteDialogFragment()
                wordDialog.arguments = bundleOf(
                    "package" to packageData,
                    "wordList" to (rvWordList.adapter as MyCustomPackageAddWordRecyclerViewAdapter).list
                )
                wordDialog.show(parentFragmentManager, "MyCustomPackageAddFirstDialogFragment")
                dismiss()
            }

            btnWordAddComplete.setOnClickListener {
                val wordDialog = MyCustomPackageAddCompleteDialogFragment()
                wordDialog.arguments = bundleOf(
                    "package" to packageData,
                    "wordList" to (rvWordList.adapter as MyCustomPackageAddWordRecyclerViewAdapter).list
                )
                wordDialog.show(parentFragmentManager, "MyCustomPackageAddFirstDialogFragment")
                dismiss()

            }
        }


    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }


}