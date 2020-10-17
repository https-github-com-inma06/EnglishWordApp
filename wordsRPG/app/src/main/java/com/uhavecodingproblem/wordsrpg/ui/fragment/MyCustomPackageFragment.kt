package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri

import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.CustomPackageRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.data.CustomPackageData
import com.uhavecodingproblem.wordsrpg.data.mockdata.CustomMyPackageListMocKData
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMyCustomPackageBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.AddNewCustomPackageActivity
import com.uhavecodingproblem.wordsrpg.ui.activity.StudyActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.util.MAKE_CUSTOM_PACKAGE_REQUEST_CODE
import com.uhavecodingproblem.wordsrpg.util.ORIGINAL_PACKAGE_TYPE

/**
 * wordsrpg
 * Class: MyPackageFragment
 * Created by pyg10.
 * Created On 2020-09-18.
 * Description:
 */
class MyCustomPackageFragment: BaseFragment<FragmentMyCustomPackageBinding>(R.layout.fragment_my_custom_package) {

    private val mockMyPackageDataList =CustomMyPackageListMocKData.list
    lateinit var recyclerViewAdapter: CustomPackageRecyclerViewAdapter


    override fun FragmentMyCustomPackageBinding.onCreateView() {
        Logger.v("실행")

        thisFragment = this@MyCustomPackageFragment

        setCustomPackageRecyclerView()

    }//onCreateView()끝




    //커스텀 패키지를 뿌려줄 리사이클러뷰 세팅
    private fun setCustomPackageRecyclerView(){

        // TODO: 2020-09-25 현재 임시 구성된 mock data list가  적용됨.
        recyclerViewAdapter=CustomPackageRecyclerViewAdapter(mockMyPackageDataList,
            ORIGINAL_PACKAGE_TYPE)//adatper 연결 -> 리사이클러뷰 TYPE은 ORIGINAL 타입으로 -> 리스트 다 뿌려줌.

         binding.recyclerviewMyCustomList.apply {
             layoutManager = GridLayoutManager(requireActivity(), 3)//grid 형태로  뿌려줌
             adapter = recyclerViewAdapter
         }


        //각 패키지 아이템 클릭시  넘어감 처리  구현
        recyclerViewAdapter.setOnItemClickListener(object : CustomPackageRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(view: View, packageName: String) {


                Toast.makeText(requireActivity(),"이 패키지로 넘기기 -> $packageName", Toast.LENGTH_SHORT).show()
//                val i= Intent(requireActivity(), StudyActivity::class.java)
//                i.putExtra("packagename",packageName)
//                startActivity(i)
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




    //fab 버튼 클릭 -> 새 커스텀 패키지 추가 엑티비티로 이동
    fun moveToAddNewCustomPackageActivity(view: View){
        Logger.v("AddNewCustomPackageActivity 로 이동")

        startActivityForResult(
            Intent(requireActivity(),
            AddNewCustomPackageActivity::class.java),
            MAKE_CUSTOM_PACKAGE_REQUEST_CODE)

    }//moveToAddNewCustomPackageActivity() 끝


    // TODO: 2020-09-26 뷰모델 생성시  뷰모델에 bind 하는 형태로  고려  일단    onactivity result로 값 적용
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(resultCode == Activity.RESULT_OK) {
            requestCode == MAKE_CUSTOM_PACKAGE_REQUEST_CODE -> {


                val name=data?.getStringExtra("name").toString()
                val uri=data?.getParcelableExtra<Uri>("uri").toString()
                val uid = mockMyPackageDataList.size+1
                val ownerUid= 3
                val tagList=data?.getStringArrayListExtra("tagList")
                Logger.v("실행됨  $name     uri -> $uri  uid -> $uid, 어레이 -> $tagList" )

                //mockdata list 에 넣어줌.
                mockMyPackageDataList.add(CustomPackageData( uid,ownerUid,name,uri,0,0, tagList!!))
                recyclerViewAdapter.notifyDataSetChanged()//리사이클러뷰 data update
            }

        }

    }



}//class 끝