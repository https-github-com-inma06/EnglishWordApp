package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
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

    //키보드 input 매니저
    lateinit var inputMethodManager:InputMethodManager//1-1

    override fun FragmentSearchCustomPackageBinding.onCreateView() {
       Logger.v("실행")

        thisFragment=this@SearchCustomPackageFragment

        inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager//1-1

    }//onCreateView() 끝



    //키보드  search action 처리
    val searchAction= TextView.OnEditorActionListener { _, actionId, _ ->

        when(actionId){
            EditorInfo.IME_ACTION_SEARCH ->{

                // TODO: 2020-09-27 여기서  search 관련 처리 진행 
                
                true
            }
            else -> false
        }

    }//searchAction 끝




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
        keyboardToggle(visible = true)
    }


    override fun onPause() {
        super.onPause()
        keyboardToggle(visible = false)
    }


}