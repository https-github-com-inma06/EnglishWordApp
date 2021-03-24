package com.uhavecodingproblem.wordsrpg.ui.dialog

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.recyclerviewadapter.MyCustomPackageAddWordRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.data.model.Package
import com.uhavecodingproblem.wordsrpg.data.model.WordsRead
import com.uhavecodingproblem.wordsrpg.databinding.DialogMyCustomPackageAddCompleteBinding
import com.uhavecodingproblem.wordsrpg.util.FIREBASE_STORAGE_IMAGE
import kotlin.random.Random


class MyCustomPackageAddCompleteDialogFragment : BottomSheetDialogFragment() {
    lateinit var binding: DialogMyCustomPackageAddCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_my_custom_package_add_complete, container, false
        )
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val packageData = arguments?.getParcelable<Package>("package")
        val wordList = arguments?.getParcelableArrayList<WordsRead>("wordList") ?: mutableListOf<WordsRead>()

        if (wordList.isNotEmpty()) {
            binding.apply {
                tvDescribe.text = "현재 단어:${wordList.size}개"
                tvNotWord.visibility = View.GONE
                btnAddpackage.visibility = View.VISIBLE
                /**테스트*/
                rvWordList.visibility = View.VISIBLE
                rvWordList.adapter = MyCustomPackageAddWordRecyclerViewAdapter(wordList)
//                tvPackageInfo.visibility = View.VISIBLE
//                tvGetPackageName.visibility = View.VISIBLE
//                ivMyPackageImage.visibility = View.VISIBLE
//                tvTagList.visibility = View.VISIBLE
//                llTagList.visibility = View.VISIBLE
//                packageName = packageData!!.package_name
//                image = packageData.package_thumbnail

//                packageData.hashTagList?.forEach { tag->
//                    val tv = TextView(context)
//                    val lp = LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT)
//                    val dp = resources.displayMetrics.density
//                    lp.setMargins((4*dp).toInt(),0,0,0)
//                    tv.text = tag
//                    tv.setTypeface(null ,Typeface.BOLD)
//                    tv.setBackgroundResource(R.drawable.dialog_my_custom_package_add_view_bg)
//                    llTagList.addView(tv)
//                }
            }

        }

        binding.btnPackageModify.setOnClickListener {
            val packageDialog = MyCustomPackageAddDialogFragment()
            packageDialog.arguments = bundleOf(
                "package" to packageData, "wordList" to wordList
            )
            packageDialog.show(parentFragmentManager, "MyCustomPackageAddDialogFragment")
            dismiss()
        }
        binding.btnWordAdd.setOnClickListener {
            val packageAddWordDialog = MyCustomPackageAddWordDialogFragment()
            packageAddWordDialog.arguments = bundleOf(
                "package" to packageData, "wordList" to wordList
            )
            packageAddWordDialog.show(parentFragmentManager, "MyCustomPackageAddWordDialogFragment")
            dismiss()
        }

        binding.ivBack.setOnClickListener {
            dismiss()
        }
        binding.btnAddpackage.setOnClickListener {
            val db = FirebaseDatabase.getInstance().reference.child("Package")
            val path = Random.nextInt().toString() + "packageImage.jpg"
            val storageRef = FirebaseStorage.getInstance().reference
            storageRef.child("images/").child(path)
                .putFile(Uri.parse(packageData?.package_thumbnail)).addOnSuccessListener {
                    storageRef.child(FIREBASE_STORAGE_IMAGE + path).downloadUrl.addOnSuccessListener {
                        packageData?.package_thumbnail = it.toString()
                        db.push().setValue(
                            packageData
                        ).addOnSuccessListener {
                            Toast.makeText(context, "업로드 되었습니다.", Toast.LENGTH_SHORT).show()
                            dismiss()
                        }
                    }
                }
        }

    }

    override fun onStart() {
        super.onStart()

        //바텀 풀 스크린
//        dialog?.also {
//            val bottomSheet = dialog!!.findViewById<View>(R.id.design_bottom_sheet)
//            bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//            val behavior = BottomSheetBehavior.from<View>(bottomSheet)
//            behavior.peekHeight = resources.displayMetrics.heightPixels //replace to whatever you want
//            view?.requestLayout()
//        }
    }


}