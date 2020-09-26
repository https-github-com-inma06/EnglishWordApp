package com.uhavecodingproblem.wordsrpg.ui.fragment.battle

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
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
 *
 * DataBindingSetting by Loner
 *  Created On 2020-09-17.
 */
class MainBattleFragment : BaseFragment<FragmentMainBattleBinding>(R.layout.fragment_main_battle) {

    private val tabLayoutTextArray = arrayOf("ALL PLAYERS", "FOLLOWERS", "FOLLOWING")

    override fun FragmentMainBattleBinding.onCreateView() {

        viewPager.adapter = ViewPagerAdapter(requireActivity())

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabLayoutTextArray[position]
        }.attach()

    }

    private inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount() = tabLayoutTextArray.size
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> TabAllPlayers()
                1 -> TabFollowers()
                2 -> TabFollowing()
                else -> this@MainBattleFragment
            }
        }
    }
}







