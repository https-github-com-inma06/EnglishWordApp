package com.uhavecodingproblem.wordsrpg.component.viewpageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.uhavecodingproblem.wordsrpg.ui.fragment.BasicPackageFragment
import com.uhavecodingproblem.wordsrpg.ui.fragment.EntireCustomPackageFragment
import com.uhavecodingproblem.wordsrpg.ui.fragment.MyCustomPackageFragment
import com.uhavecodingproblem.wordsrpg.ui.fragment.MySubScribePackageFragment

/**
 * wordsrpg
 * Class: MainLibraryViewPagerAdapter
 * Created by pyg10.
 * Created On 2020-09-18.
 * Description:
 * MainLibrary ViewPager2 Adapter
 */
class MainLibraryFragmentViewPagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> BasicPackageFragment()
            1 -> EntireCustomPackageFragment()
            2 -> MySubScribePackageFragment()
            else-> MyCustomPackageFragment()
        }
    }
}