package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.view.View
import android.view.WindowManager
import androidx.navigation.fragment.findNavController
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.FragmentSearchCustomPackageBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.util.Logger

/**
 * wordsrpg
 * Class: SearchCustomPackageFragment.
 * Created by leedonghun.
 * Created On 2020-09-24.
 * Description:
 *
 * 커스텀 패키지를 검색하는  화면이다.
 *
 */
class SearchCustomPackageFragment:BaseFragment<FragmentSearchCustomPackageBinding>(R.layout.fragment_search_custom_package) {

    override fun FragmentSearchCustomPackageBinding.onCreateView() {
       Logger.v("실행")

        thisFragment=this@SearchCustomPackageFragment

        //상단 검색어 입력 edittext에 focus가  가져서 키보드가 올라올때
        //bottom navigation 메뉴 같이 딸려 올라오는거 방지함.
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)


    }//onCreateView() 끝


    //내패키지 프래그먼트로 돌아가기
    fun backToMyCustomPackageFragment(view: View){

        Logger.v("내패키지로  돌아가기")

        //네비게이션 backstack으로 이동
        findNavController().popBackStack()

    }//backToMyCustomPackageFragment()끝




}