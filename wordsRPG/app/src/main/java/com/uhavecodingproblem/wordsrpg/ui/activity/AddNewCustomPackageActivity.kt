package com.uhavecodingproblem.wordsrpg.ui.activity

import android.app.AlertDialog
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Toast
import androidx.core.view.size
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityAddNewCustomPackageBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import com.uhavecodingproblem.wordsrpg.util.Logger

/**
 * wordsrpg
 * Class: AddNewCustomPackageActivity.
 * Created by leedonghun.
 * Created On 2020-09-24.
 * Description:
 *
 * 커스텀 패키지  새로 추가하는 엑티비티
 */
class AddNewCustomPackageActivity : BaseActivity<ActivityAddNewCustomPackageBinding>(R.layout.activity_add_new_custom_package){

    override fun ActivityAddNewCustomPackageBinding.onCreate() {
        Logger.v("실행")

        thisActivity=this@AddNewCustomPackageActivity

    }


    //검색용 태그 추가하기
    fun addTag(view: View){

        //tag 내용
        val tagString= binding.editTvAddNewTag.text.toString()

        when{

            //태그가 3개 이상이면  태그 추가 불가
            binding.chipGroup.size > 2 -> {
                Toast.makeText(this,R.string.str_tag_size_max, Toast.LENGTH_SHORT).show()
            }


            //tag 내용중 공백이 있을때
            tagString.contains(" ") -> {
                Toast.makeText(this,R.string.str_tag_detect_space,Toast.LENGTH_SHORT).show()
            }

            //적은 태그내용이 없을떄
            tagString.isEmpty() -> {
                Toast.makeText(this,R.string.str_tag_no_content,Toast.LENGTH_SHORT).show()
            }

            //chip group 에  chip 형태 태그들 추가
            else -> {
                addTagViewInChipGroup(tagString)

                //추가후 editext 비워주기
                binding.editTvAddNewTag.text.clear()
            }

        }//when 끝

    }//addTag() 끝



    //chipgroup 에 유저가 입력한  태그 내용을
    //chip뷰에 담아 동적 추가 진행
    private fun addTagViewInChipGroup(tagString:String){

        val chip= Chip(this)

        //chip close icon 때문에 커스텀 style 추가
        chip.setChipDrawable(ChipDrawable.createFromAttributes(this,null,0,R.style.tag_view_style))
        chip.text = tagString//유저가 입력한 태그 내용 넣어줌.

        //chipgroup에 동적 추가
        binding.chipGroup.addView(chip)

        //chip뷰에  close icon 클릭 event
        chip.setOnCloseIconClickListener {

            //투명하게  사라지는  애니메이션 적용
            val anim = AlphaAnimation(1f, 0f)
            anim.duration = 250

            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {//애니메이션 끝났을때  chipgroup에서  해당 chip 삭제
                    binding.chipGroup.removeView(it)
                }

                override fun onAnimationStart(animation: Animation?) {}
            })

            it.startAnimation(anim)

        }
    }//addTagViewInChipGroup() 끝



    //뒤로 가기 아이콘 클릭
    fun backIconClicked(view: View){
        Logger.v("뒤로가기 클릭")
        finish()
    }//backIconClicked() 끝



    //패키지 썸네일 이미지 갤러리에서 가져오기
    fun pickThumbNailImageFromGallery(view: View){
        Logger.v("썸네일 이미지 가져오기 실행")

        AlertDialog.Builder(this)
            .setMessage("이미지 가져오기 준비중")
            .setPositiveButton("확인"){dialog,i->
                dialog.dismiss()
            }
            .show()

    }//pickThumbNailImageFromGallery() 끝


    //완료 버튼 event
    fun completeAddNewCustomPackage(view: View){
        Logger.v("커스텀 패키지 추가 완료 버튼 클릭")

        // TODO: 2020-09-24 일단  엑티비티 종료로 넣어둠  나중에  패키지 추가 작업 필요
        finish()
    }//completeAddNewCustomPackage() 끝




}