package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.uhavecodingproblem.wordsrpg.Dialog.SearchLoadingDialog
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.CustomPackageRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.data.CustomPackageData
import com.uhavecodingproblem.wordsrpg.data.mockdata.CustomMyPackageListMocKData
import com.uhavecodingproblem.wordsrpg.databinding.FragmentSearchCustomPackageBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.util.SEARCH_PACKAGE_TYPE



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
    lateinit var loadingDialog: SearchLoadingDialog//1-2

    //내패키지용 mockdata list 적용
    private val mockMyPackageDataList = CustomMyPackageListMocKData.list

    //recyclerview adatoer
    lateinit var recyclerViewAdapter: CustomPackageRecyclerViewAdapter



    override fun FragmentSearchCustomPackageBinding.onCreateView() {
       Logger.v("실행")

        thisFragment=this@SearchCustomPackageFragment

        inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager//1-1
        loadingDialog = SearchLoadingDialog(requireActivity())//1-2

        //리사이클러뷰 setting
        setSearchedCustomPackageRecyclerView()

    }//onCreateView() 끝




    //키보드  search action 처리
    val searchAction = TextView.OnEditorActionListener { _, actionId, _ ->

        when(actionId){
            EditorInfo.IME_ACTION_SEARCH ->{

                Logger.v("검색버튼 눌림")

                //검색 필터 적용
                recyclerViewAdapter.filter.filter(binding.editTvSearchPackage.text.toString())
                
                true
            }
            else -> false
        }

    }//searchAction 끝




    //검색된 커스텀 패키지를 뿌려줄 리사이클러뷰 세팅
    private fun setSearchedCustomPackageRecyclerView(){

        // TODO: 2020-09-25 현재 임시 구성된 mock data list가  적용됨.
        recyclerViewAdapter= CustomPackageRecyclerViewAdapter(mockMyPackageDataList,
            SEARCH_PACKAGE_TYPE)//adatper 연결 -> 타입은 검색용


        binding.recyclerviewSearchedPackageList.apply {
            layoutManager = GridLayoutManager(requireActivity(), 3)//grid 형태로  뿌려줌
            adapter = recyclerViewAdapter
        }


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
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()//메인엑티비티 toolbar  hide
    }




    override fun onPause() {
        super.onPause()
        keyboardToggle(visible = false)//키보드 숨기기
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()//메인엑티비티 toolbar  show
    }


}