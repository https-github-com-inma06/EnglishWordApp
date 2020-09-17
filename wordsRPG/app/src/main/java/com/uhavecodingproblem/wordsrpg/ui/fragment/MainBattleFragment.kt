package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.widget.Toast
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMainBattleBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main_home.*

/**
 * wordsrpg
 * Class: MainBattleFragment.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 * 배틀 프레그먼트입니다.
 *
 * DataBindingSetting by Loner
 *  Created On 2020-09-17.
 */
class MainBattleFragment : BaseFragment<FragmentMainBattleBinding>(R.layout.fragment_main_battle) {
    override fun FragmentMainBattleBinding.onCreateView() {
        /* 프레그먼트 onCreateView함수 안에서 자체적인 데바가 적용되었으므로
         뷰id만 적고 뷰 객체 가져오시면 됩니다.*/

        textView3.setOnClickListener {
            Toast.makeText(requireContext(), "암욜맨", Toast.LENGTH_SHORT).show()
        }

    }

    /**
     * 온크레이트 바깥함수 에서 데바 사용할때
     * binding.뷰이름 으로 가져오시면 됩니다.
     **/
    fun example() {
        //        binding.textView3
    }


}







