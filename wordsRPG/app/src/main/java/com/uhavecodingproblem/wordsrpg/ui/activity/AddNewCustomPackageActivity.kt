package com.uhavecodingproblem.wordsrpg.ui.activity

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import androidx.core.view.size
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



    //뒤로 가기 아이콘 클릭
    fun backIconClicked(view: View){
        Logger.v("뒤로가기 클릭")
        finish()
    }


    //패키지 썸네일 이미지 갤러리에서 가져오기
    fun pickThumbNailImageFromGallery(view: View){
        Logger.v("썸네일 이미지 가져오기 실행")

        AlertDialog.Builder(this)
            .setMessage("이미지 가져오기 준비중")
            .setPositiveButton("확인"){dialog,i->
                dialog.dismiss()
            }
            .show()

    }


    //완료 버튼 event
    fun completeAddNewCustomPackage(view: View){
        Logger.v("커스텀 패키지 추가 완료 버튼 클릭")

        // TODO: 2020-09-24 일단  엑티비티 종료로 넣어둠  나중에  패키지 추가 작업 필요
        finish()
    }




}