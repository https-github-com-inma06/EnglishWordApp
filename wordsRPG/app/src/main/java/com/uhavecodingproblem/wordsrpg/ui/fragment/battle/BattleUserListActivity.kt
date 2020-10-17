package com.uhavecodingproblem.wordsrpg.ui.fragment.battle

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityBattleUserListBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity

class BattleUserListActivity : BaseActivity<ActivityBattleUserListBinding>(R.layout.activity_battle_user_list) {

    private val tabLayoutTextArray = arrayOf("ALL PLAYERS", "FOLLOWERS", "FOLLOWING")

    override fun ActivityBattleUserListBinding.onCreate() {
        viewPager.adapter = ViewPagerAdapter(this@BattleUserListActivity)

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
                else -> TabFollowing()
            }
        }
    }
}
