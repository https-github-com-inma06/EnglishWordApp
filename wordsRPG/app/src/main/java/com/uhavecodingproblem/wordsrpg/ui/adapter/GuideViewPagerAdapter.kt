package com.uhavecodingproblem.wordsrpg.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ItemGuideViewpagerBinding
import com.uhavecodingproblem.wordsrpg.util.imageUrl
import kotlinx.android.synthetic.main.item_guide_viewpager.view.*

class GuideViewPagerAdapter(private val imageList: MutableList<String>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        DataBindingUtil.inflate<ItemGuideViewpagerBinding>(
            LayoutInflater.from(container.context),
            R.layout.item_guide_viewpager, container, false
        ).also {
            it.imageUrl = imageList[position]
            container.addView(it.root)
            return it.root
        }
    }

    override fun getCount() = imageList.size

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}