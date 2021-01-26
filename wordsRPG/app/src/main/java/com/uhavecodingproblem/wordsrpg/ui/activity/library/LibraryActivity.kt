package com.uhavecodingproblem.wordsrpg.ui.activity.library

import android.content.Intent
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.LibraryActivityRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.data.WordData
import com.uhavecodingproblem.wordsrpg.data.WordType
import com.uhavecodingproblem.wordsrpg.databinding.ActivityLibraryBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.StudyActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import com.uhavecodingproblem.wordsrpg.util.Logger

/**
 *  서버에서 데이터 가져오는 작업 필요해보임! check by Atanasio.
 **/

class LibraryActivity : BaseActivity<ActivityLibraryBinding>(R.layout.activity_library), LibraryActivityRecyclerViewAdapter.GridItemClickListener {

    private var libraryItem: WordType? = null
    private var item = mutableListOf<WordType>()

    override fun ActivityLibraryBinding.onCreate() {
        Logger.v("실행")

        getLibraryItem()
        setToolbarTitle()
        setRecyclerView()

    }

    private fun getLibraryItem(){
        intent?.let {
            libraryItem = it.getParcelableExtra("wordData")
        }
    }

    private fun setLibraryList(): MutableList<WordType>{
        var word = mutableListOf<WordData>()
        var count = 1
        libraryItem?.words?.let {
            for (i in it.indices){
                if (word.size == 10) {
                    item.add(WordType(type = libraryItem?.type!!, name = "스탭 $count", writer = libraryItem?.writer!!, description = libraryItem?.description!!, thumbnailImage = libraryItem?.thumbnailImage!!, words = word))
                    count++
                    word = mutableListOf()
                }
                word.add(it[i])
            }

            if (!word.isNullOrEmpty())
                item.add(WordType(type = libraryItem?.type!!, name = "스탭 $count", writer = libraryItem?.writer!!, description = libraryItem?.description!!, thumbnailImage = libraryItem?.thumbnailImage!!, words = word))

        }
        return item
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
            }
        }
        return true
    }

    private fun setToolbarTitle(){
        setSupportActionBar(binding.libraryToolbar)

        val actionbar= supportActionBar
        actionbar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.title = libraryItem?.name
    }

    private fun setRecyclerView(){
        binding.libraryRecyclerview.run {
            adapter = LibraryActivityRecyclerViewAdapter(setLibraryList(), this@LibraryActivity)
            layoutManager = GridLayoutManager(this@LibraryActivity, 3)
        }
    }

    override fun onItemClick(v: View, position: Int) {
        Intent(this@LibraryActivity, StudyActivity::class.java).also {
            it.putExtra("StudyWord", item[position])
            startActivity(it)
        }
    }
}