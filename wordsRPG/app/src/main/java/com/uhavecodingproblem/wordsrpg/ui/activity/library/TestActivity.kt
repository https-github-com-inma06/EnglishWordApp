package com.uhavecodingproblem.wordsrpg.ui.activity.library

import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.WordObserveViewModel
import com.uhavecodingproblem.wordsrpg.component.library.viewpageradapter.TestViewPagerAdapter
import com.uhavecodingproblem.wordsrpg.data.model.Learning
import com.uhavecodingproblem.wordsrpg.data.model.RequestTest
import com.uhavecodingproblem.wordsrpg.data.model.WordsRead
import com.uhavecodingproblem.wordsrpg.databinding.ActivityTestBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility

class TestActivity : BaseUtility.BaseActivity<ActivityTestBinding>(R.layout.activity_test) {

    private var testAdapter: TestViewPagerAdapter? = null
    private var testItem = mutableListOf<WordsRead>()
    private val wordViewModel by viewModels<WordObserveViewModel>()

    private val requestTestItem = mutableListOf<RequestTest>()

    override fun ActivityTestBinding.onCreate() {
        setUpTestItem()
        viewPagerInit()
        observeWord()
    }

    private fun setUpTestItem() {
        intent.getParcelableExtra<Learning>("test")?.let {
            wordViewModel.loadWordLink(it.p_id, it.s_id, true)
        }
    }

    private fun observeWord() {
        wordViewModel.wordList.observe(this@TestActivity) { list ->

//            testItem.clear()
//            testItem.addAll(it)
//            testAdapter?.notifyDataSetChanged()
        }
    }

    private fun viewPagerInit() {
        binding.viewpager2Test.apply {
            testAdapter = TestViewPagerAdapter(testItem)
            adapter = testAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            isUserInputEnabled = false
        }
    }

}