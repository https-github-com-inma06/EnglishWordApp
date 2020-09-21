package com.uhavecodingproblem.wordsrpg.ui.fragment

import com.google.android.material.tabs.TabLayoutMediator
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.MainLibraryViewPagerAdapter
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMainLibraryBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment


/**
 * wordsrpg
 * Class: MainLibraryFragment.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 * 라이브러리 프레그먼트입니다.
 */
class MainLibraryFragment : BaseFragment<FragmentMainLibraryBinding>(R.layout.fragment_main_library) {

    private val tabItemName = arrayOf("기본 패키지", "내 패키지", "구독 패키지")

    override fun FragmentMainLibraryBinding.onCreateView() {
        Logger.v("실행")

        setViewPager()
        setTabLayout()

    }

    private fun setTabLayout(){
        TabLayoutMediator(binding.tabPackageName, binding.viewpager2Library){tab, position ->
            tab.text = tabItemName[position]
        }.attach()
    }

    private fun setViewPager(){
        binding.viewpager2Library.apply {
            adapter = MainLibraryViewPagerAdapter(requireActivity())
        }
    }

}