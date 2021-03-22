package com.uhavecodingproblem.wordsrpg.ui.dialog

import android.app.AlertDialog
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.Package
import com.uhavecodingproblem.wordsrpg.databinding.DialogMyCustomPackageAddBinding
import com.uhavecodingproblem.wordsrpg.util.FIREBASE_STORAGE_REFERENCE_URL
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.util.SharedPreferenceUtil
import gun0912.tedimagepicker.builder.TedImagePicker
import gun0912.tedimagepicker.builder.type.ButtonGravity
import gun0912.tedimagepicker.builder.type.MediaType
import kotlin.random.Random

class MyCustomPackageAddDialogFragment : BottomSheetDialogFragment() {
    lateinit var binding: DialogMyCustomPackageAddBinding
    private var tagArray = mutableListOf<String>()
    private var profileImage: Uri? = null

    private var db = FirebaseDatabase.getInstance().reference.child("Package")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_my_custom_package_add, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnAdd.setOnClickListener {
                addTagViewInChipGroup(etInsertTag.text.toString())
                etInsertTag.setText("")
            }
            ivPackageImage.setOnClickListener {
                pickThumbNailImageFromGallery()
            }
            btnAddPackage.setOnClickListener {
//                if (profileImage == null)
//                    Toast.makeText(context, "사진을 선택해주세요", Toast.LENGTH_SHORT).show()
//
//                if (tagArray.isEmpty())
//                    Toast.makeText(context, "사용할 태그를 적어주세요", Toast.LENGTH_SHORT).show()

                /**테스트*/
                val path = Random.nextInt().toString() + "packageImage.jpg"
                val packid = Random.nextInt().toString() + "packName"
                FirebaseStorage.getInstance().reference.child("images/").child(path)
                    .putFile(profileImage!!).addOnSuccessListener {
                        db.push().setValue(
                            Package(
                                packid,
                                etMyPackageName.text.toString(), "50",
                                FIREBASE_STORAGE_REFERENCE_URL + "images/"+path, "0",
                                "1", SharedPreferenceUtil.userIdx, tagArray, mutableListOf()
                            )
                        ).addOnCanceledListener {
                            Toast.makeText(context, "업로드 되었습니다.", Toast.LENGTH_SHORT).show()
                            this@MyCustomPackageAddDialogFragment.dismiss()
                        }
                    }
            }
        }

    }

    //패키지 썸네일 이미지 갤러리에서 가져오기
    private fun pickThumbNailImageFromGallery() {
        Logger.d("썸네일 이미지 가져오기 실행")

        // TODO: 2020-09-24 이미지 추가  넣기
        AlertDialog.Builder(requireContext())
            .setMessage("갤러리에서 이미지를 가져오시겠습니까?")
            .setPositiveButton("네") { dialog, i ->


                // TODO: 2020-09-25 임시적으로  ted image_picker 적용  회의에서 사용 여부 결정
                //앨범에서 커스텀 패키지용으로 사용할 이미지를 가지고온다.
                TedImagePicker.with(requireContext())
                    .title("패키지 이미지 고르기")
                    .mediaType(MediaType.IMAGE)
                    .scrollIndicatorDateFormat("YYYYMMDD")
                    .buttonGravity(ButtonGravity.BOTTOM)
                    .showCameraTile(false)
                    .errorListener { message -> Logger.d("ted image_picker error -> $message") }
                    .startAnimation(R.anim.to_left_slide, R.anim.fade_out)
                    .start {
                        profileImage = it
                        //썸네일 이미지뷰 및 썸네일 프리뷰에 가져온 이미지 넣어줌.
                        Glide.with(binding.ivPackageImage).load(it).centerCrop().into(binding.ivPackageImage)
                        // TODO: 2020-09-26 뷰모델 적용시  지울것
                        requireActivity().intent.putExtra("uri", it)
                    }
                dialog.dismiss()
            }.setNegativeButton("아니오") { dialog, i ->
                dialog.dismiss()
            }
            .show()

    }//pickThumbNailImageFromGallery() 끝

    //chipgroup 에 유저가 입력한  태그 내용을
    //chip뷰에 담아 동적 추가 진행
    private fun addTagViewInChipGroup(tagString: String) {

        val chip = Chip(requireContext())

        //chip close icon 때문에 커스텀 style 추가
        chip.setChipDrawable(
            ChipDrawable.createFromAttributes(
                requireContext(),
                null, 0, R.style.tag_view_style
            )
        )
        chip.setBackgroundColor(Color.parseColor("#AFAFAF"))
        chip.text = tagString//유저가 입력한 태그 내용 넣어줌.

        //chipgroup에 동적 추가
        binding.chipGroup.addView(chip)

        tagArray.add(tagString)

        //chip뷰에  close icon 클릭 event
        chip.setOnCloseIconClickListener {

            //투명하게  사라지는  애니메이션 적용
            val anim = AlphaAnimation(1f, 0f)
            anim.duration = 250
            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {}
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {//애니메이션 끝났을때  chipgroup에서  해당 chip 삭제
                    binding.chipGroup.removeView(it)
                }
            })
            it.startAnimation(anim)
        }

    }//addTagViewInChipGroup() 끝

}