package com.uhavecodingproblem.wordsrpg.ui.fragment

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMySubscribePackageBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.util.Logger

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
class MySubScribePackageFragment:BaseFragment<FragmentMySubscribePackageBinding>(R.layout.fragment_my_subscribe_package) {

    override fun FragmentMySubscribePackageBinding.onCreateView() {
        Logger.v("실행 됨")

    }//onCreate() 끝

}