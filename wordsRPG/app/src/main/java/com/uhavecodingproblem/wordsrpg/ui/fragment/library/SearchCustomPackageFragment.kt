package com.uhavecodingproblem.wordsrpg.ui.fragment.library

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.uhavecodingproblem.wordsrpg.dialog.SearchLoadingDialog
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.recyclerviewadapter.CustomPackageRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.data.mockdata.CustomMyPackageListMocKData
import com.uhavecodingproblem.wordsrpg.databinding.FragmentSearchCustomPackageBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.util.SEARCH_PACKAGE_TAG
import com.uhavecodingproblem.wordsrpg.util.SEARCH_PACKAGE_TITLE



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

    //키보드 input 매니저
    lateinit var inputMethodManager:InputMethodManager//1-1

    //검색 로딩
    private lateinit var loadingDialog: SearchLoadingDialog//1-2

    //내패키지용 mockdata list 적용
    private val mockMyPackageDataList = CustomMyPackageListMocKData.list

    //recyclerview adatoer
    private lateinit var recyclerViewAdapter: CustomPackageRecyclerViewAdapter


    override fun FragmentSearchCustomPackageBinding.onCreateView() {
       Logger.v("실행")

        thisFragment=this@SearchCustomPackageFragment

        inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager//1-1
        loadingDialog = SearchLoadingDialog(requireActivity())//1-2

        //리사이클러뷰 setting
        setSearchedCustomPackageRecyclerView()

    }//onCreateView() 끝



//
//    //필터 change
//    fun setFilterChange(view: View){
//
//        if(view == binding.tvTagFilter){//태그 필터 클릭시
//            recyclerViewAdapter.changeType(newFilterType = SEARCH_PACKAGE_TAG)
//            filterClickedStyleChange(filterCheck = SEARCH_PACKAGE_TAG)
//        }else{
//            recyclerViewAdapter.changeType(newFilterType = SEARCH_PACKAGE_TITLE)
//            filterClickedStyleChange(filterCheck = SEARCH_PACKAGE_TITLE)
//        }
//
//        //필터가 바뀌면  우선, filter에 null값을 줘서  list 를   reset 시킨다.
//        recyclerViewAdapter.filter.filter(null)
//    }



     //체크된  필터  스타일 바꿔줌.
    // TODO: 2020-10-03 우선  필터 클릭에 따른  뷰 스타일  backgoround 변경으로 진행함.->  디자인에 따라  다르게 변경 적용되야 됨
    private fun filterClickedStyleChange(filterCheck : Int ){
        if(filterCheck == SEARCH_PACKAGE_TAG){//태그 클릭시
            binding.tvTagFilter.setBackgroundResource(R.color.colorDarkGray)
            binding.tvTitleFilter.setBackgroundResource(R.color.colorGray)
        }else if(filterCheck == SEARCH_PACKAGE_TITLE){//타이틀 클릭시
            binding.tvTagFilter.setBackgroundResource(R.color.colorGray)
            binding.tvTitleFilter.setBackgroundResource(R.color.colorDarkGray)
        }
    }


//    //키보드  search action 처리 (feat 필요없는 값들 _ 처리)
//    val searchAction = TextView.OnEditorActionListener { _, actionId, _ ->
//
//        when(actionId){
//            EditorInfo.IME_ACTION_SEARCH ->{
//
//                Logger.v("검색버튼 눌림")
//
//                //editext에 적힌 내용으로  검색 필터 적용
//                recyclerViewAdapter.filter.filter(binding.editTvSearchPackage.text.toString())
//
//                true
//            }
//            else -> false
//        }
//
//    }//searchAction 끝




    //검색된 커스텀 패키지를 뿌려줄 리사이클러뷰 세팅
    private fun setSearchedCustomPackageRecyclerView(){

        // TODO: 2020-09-25 현재 임시 구성된 mock data list가  적용됨.
        recyclerViewAdapter= CustomPackageRecyclerViewAdapter(mockMyPackageDataList,
            SEARCH_PACKAGE_TITLE)//adatper 연결 -> default 검색 타입은 제목

        //필터 스타일도 TITLE 적용된걸로 바꿈.
        filterClickedStyleChange(filterCheck = SEARCH_PACKAGE_TITLE)

        binding.recyclerviewSearchedPackageList.apply {
            layoutManager = GridLayoutManager(requireActivity(), 3)//grid 형태로  뿌려줌 (가로 최대 3개 아이템)
            adapter = recyclerViewAdapter
        }


        //각 패키지 아이템 클릭시  넘어감 처리  구현
        recyclerViewAdapter.setOnItemClickListener(object : CustomPackageRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(view: View, packageName: String) {


                Toast.makeText(requireActivity(),"이 패키지로 넘기기 -> $packageName",Toast.LENGTH_SHORT).show()
//                val i= Intent(requireActivity(),StudyActivity::class.java)
//                i.putExtra("packagename",packageName)
//                startActivity(i)
            }
        })


    }//initRecyclerView()끝




    //키보드 보임 여부를 결정한다.
    //visible 여부로  boolean 값으로  판단한다.
    private fun keyboardToggle(visible:Boolean){

        if(visible){//키보드가 보여야할때
            //search editext에  맞춰  키보드  보이기
            inputMethodManager.showSoftInput(binding.editTvSearchPackage, InputMethodManager.SHOW_IMPLICIT)

        }else{//키보드 사라져야할때
            inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)

        }
    }//keyboardToggle() 끝






    //내패키지 프래그먼트로 돌아가기
    fun backToMyCustomPackageFragment(view: View){
        Logger.v("내패키지로  돌아가기")

        keyboardToggle(visible = false)

        //네비게이션 backstack으로 이동
        findNavController().popBackStack()

    }//backToMyCustomPackageFragment()끝




    override fun onResume() {
        super.onResume()
        keyboardToggle(visible = true)//키보드 보이기

    }


    override fun onPause() {
        super.onPause()
        keyboardToggle(visible = false)//키보드 숨기기

    }


}