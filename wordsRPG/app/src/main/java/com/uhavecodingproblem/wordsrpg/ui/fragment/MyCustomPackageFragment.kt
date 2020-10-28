package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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
import com.uhavecodingproblem.wordsrpg.util.*

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

     var presentSearchTag = SEARCH_PACKAGE_TAG

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


    //필터 바뀜 처리
    fun filterChange(filter: Int){

        changeFilterAnimation(binding.viewgroupLibrarySearch.iconSearchFilter).start()


        when(filter){
            SEARCH_PACKAGE_TITLE ->{
                binding.viewgroupLibrarySearch.tvSearchFilter.setText(R.string.str_filter_title)
            }

            SEARCH_PACKAGE_TAG ->{
                binding.viewgroupLibrarySearch.tvSearchFilter.setText(R.string.str_filter_tag)
            }
        }

    }//filterChange() 끝


    //필터 바뀜 애니메이션
    private fun changeFilterAnimation(view: View) :Animation{
        view.animation = AnimationUtils.loadAnimation(requireActivity(),R.anim.search_filter_rotation)
        return view.animation
    }





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