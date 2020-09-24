package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.view.View
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMyCustomPackageBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.util.Logger

/**
 * wordsrpg
 * Class: MyPackageFragment
 * Created by pyg10.
 * Created On 2020-09-18.
 * Description:
 */
class MyCustomPackageFragment: BaseFragment<FragmentMyCustomPackageBinding>(R.layout.fragment_my_custom_package) {

    override fun FragmentMyCustomPackageBinding.onCreateView() {
        Logger.v("실행")

        thisFragment=this@MyCustomPackageFragment

    }//onCreateView()끝



    //내 패키지 검색 tv 클릭 event
    fun searchMyCustomPackage(view: View){
        Logger.v("내 패키지 검색 엑티비티 실행")


    }

    //새 커스텀 패키지 추가 엑티비티로 이동
    fun moveToAddNewCustomPackageActivity(view: View){
        Logger.v("fab 버튼 클릭 ->  새 커스텀 패키지 추가 엑티비티로 이동")

    }


}//class 끝