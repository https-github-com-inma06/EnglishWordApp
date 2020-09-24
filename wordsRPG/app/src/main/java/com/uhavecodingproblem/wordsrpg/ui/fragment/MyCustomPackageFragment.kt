package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.content.Intent
import android.view.View
import androidx.navigation.fragment.findNavController
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMyCustomPackageBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.AddNewCustomPackageActivity
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

        thisFragment = this@MyCustomPackageFragment

    }//onCreateView()끝


    // TODO: 2020-09-24  recycerview 추가 작업 진행 할것
    private fun initRecyclerView(){



    }


    //내 패키지 검색 tv 클릭 event
    fun searchMyCustomPackage(view: View){
        Logger.v("내 패키지 검색 엑티비티 실행")

        //네비게이션 내패키지 검색 fragment로 이동
        findNavController().navigate(
            R.id.action_library_to_search_fragment,
            arguments
        )

    }//searchMyCustomPackage() 끝




    //fab 버튼 클릭 -> 새 커스텀 패키지 추가 엑티비티로 이동
    fun moveToAddNewCustomPackageActivity(view: View){
        Logger.v("AddNewCustomPackageActivity 로 이동")

        startActivity(Intent(requireActivity(),AddNewCustomPackageActivity::class.java))

    }//moveToAddNewCustomPackageActivity() 끝



}//class 끝