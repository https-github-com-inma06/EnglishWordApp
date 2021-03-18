package com.uhavecodingproblem.wordsrpg.ui.fragment.library

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMySubscribePackageBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.util.hideKeyboard

/**
 * wordsrpg
 * Class: mySubScribePakage.
 * Created by leedonghun.
 * Created On 2020-10-17.
 * Description:
 *
 * 내가  구독한 사람들의  최신  패키지 소식들을
 * 볼수 있는  리스트가 담긴  프래그먼트이다.
 */
class MySubScribePackageFragment: BaseUtility.BaseFragment<FragmentMySubscribePackageBinding>(R.layout.fragment_my_subscribe_package) {

    override fun FragmentMySubscribePackageBinding.onCreateView() {
        Logger.d("실행 됨")

    }//onCreate() 끝

    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }
}