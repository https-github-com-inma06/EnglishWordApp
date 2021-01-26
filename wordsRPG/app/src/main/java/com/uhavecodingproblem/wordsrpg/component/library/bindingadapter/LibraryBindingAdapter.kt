package com.uhavecodingproblem.wordsrpg.component.library.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.uhavecodingproblem.wordsrpg.component.library.viewpageradapter.MainLibraryViewPagerAdapter
import com.uhavecodingproblem.wordsrpg.component.viewmodel.LibraryViewModel

/**
 * wordsrpg
 * Class: LibraryBindingAdapter
 * Created by pyg10.
 * Created On 2020-09-25.
 * Description: DataBinding Adapter
 *
 * https://kobbi-reply.tistory.com/26 참고해서 viewpager2로 변경
 */

object LibraryBindingAdapter {

    /**
    tabLayout BindingAdapter
     */
    @JvmStatic
    @BindingAdapter("setTabContent", "setViewModel")
    fun setTabContent(
        tabLayout: TabLayout,
        item: List<String>?,
        libraryViewModel: LibraryViewModel?
    ) {

        item?.forEach {
            with(tabLayout) {
                newTab().let { tab ->
                    tab.text = it
                    addTab(tab)
                }
                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab?.position?.let { position ->
                            libraryViewModel?.selectPosition(position)
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {

                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {

                    }
                })
            }
        }
    }

    /**
     *
     * ViewPager2 BindingAdapter
     *
     */

    @JvmStatic
    @BindingAdapter("setFragmentActivity", "setViewModel")
    fun setFragmentActivity(
        viewPager2: ViewPager2,
        fragmentActivity: FragmentActivity?,
        libraryViewModel: LibraryViewModel?
    ) {

        fragmentActivity?.let {
            viewPager2.adapter = MainLibraryViewPagerAdapter(it)

            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    libraryViewModel?.selectPosition(position)
                }
            })

        }

    }

    /**
     *
     *  Current Position BindingAdapter
     *
     */

    @JvmStatic
    @BindingAdapter("setViewPosition")
    fun setViewPosition(view: View, position: Int?) {
        if (position != null) {
            when (view) {
                is ViewPager2 -> {
                    view.currentItem = position
                }
                is TabLayout -> {
                    view.run {
                        getTabAt(position)?.let { tab ->
                            selectTab(tab)
                        }
                    }
                }
            }
        }
    }

}