package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.CustomPackageRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.data.mockdata.CustomPackageListMockData
import com.uhavecodingproblem.wordsrpg.databinding.FragmentEntireCustomPackageBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.MemorizationActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.util.ORIGINAL_PACKAGE_TYPE

/**
 * wordsrpg
 * Class: SubscribePackageFragment
 * Created by pyg10.
 * Created On 2020-09-18.
 * Description:
 *
 *
 */
class EntireCustomPackageFragment: BaseFragment<FragmentEntireCustomPackageBinding>(R.layout.fragment_entire_custom_package) {

    private val mockMyPackageDataList = CustomPackageListMockData.list
    lateinit var recyclerViewAdapter: CustomPackageRecyclerViewAdapter

    override fun FragmentEntireCustomPackageBinding.onCreateView() {
        Logger.v("실행")


        thisFragment = this@EntireCustomPackageFragment
        setCustomPackageRecyclerView()
    }

    //커스텀 패키지를 뿌려줄 리사이클러뷰 세팅
    private fun setCustomPackageRecyclerView(){

        // TODO: 2020-09-25 현재 임시 구성된 mock data list가  적용됨.
        recyclerViewAdapter= CustomPackageRecyclerViewAdapter(mockMyPackageDataList,
            ORIGINAL_PACKAGE_TYPE
        )//adatper 연결 -> 리사이클러뷰 TYPE은 ORIGINAL 타입으로 -> 리스트 다 뿌려줌.

        binding.recyclerviewCustomList.apply {
            layoutManager = GridLayoutManager(requireActivity(), 3)//grid 형태로  뿌려줌
            adapter = recyclerViewAdapter
        }


        //각 패키지 아이템 클릭시  넘어감 처리  구현
        recyclerViewAdapter.setOnItemClickListener(object : CustomPackageRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(view: View, packageName: String) {


                Toast.makeText(requireActivity(),"이 패키지로 넘기기 -> $packageName", Toast.LENGTH_SHORT).show()
                val i= Intent(requireActivity(), MemorizationActivity::class.java)
                i.putExtra("packagename",packageName)
                startActivity(i)
            }
        })


    }//initRecyclerView()끝





    //내 패키지 검색 tv 클릭 event
    fun searchMyCustomPackage(view: View){
        Logger.v("내 패키지 검색 엑티비티 실행")

        //네비게이션 내패키지 검색 fragment로 이동
        findNavController().navigate(
            R.id.action_library_to_search_fragment,
            arguments
        )

    }//searchMyCustomPackage() 끝

}