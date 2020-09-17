package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.widget.Toast
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMainBattleBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment

/**
 * wordsrpg
 * Class: MainBattleFragment.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 * 배틀 프레그먼트입니다.
 */
class MainBattleFragment : BaseFragment<FragmentMainBattleBinding>(R.layout.fragment_main_battle) {
    override fun FragmentMainBattleBinding.onCreateView() {
//        setTextViewClickEvent() 예시입니다. 가독성을 위해 함수로 묶어서 온크레이트 뷰안에 넣어주시면 됩니다.
    }


    fun hi(){
        /**
         * onCreateView 바깥에서 데이터바인딩 사용시,
         * binding.뷰이름 으로 가져오심녀 됩니다.
         * xml 을 참조하는 실수를 주의해주세요
         */
        binding.textView3

    }


    private fun FragmentMainBattleBinding.setTextViewClickEvent() {
        textView3.setOnClickListener {
            Toast.makeText(requireContext(), "암욜맨", Toast.LENGTH_SHORT).show()
        }
    }
}

