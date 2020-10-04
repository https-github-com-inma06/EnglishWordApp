package com.uhavecodingproblem.wordsrpg.ui.activity

import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.LibraryActivityRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.data.WordData
import com.uhavecodingproblem.wordsrpg.data.WordType
import com.uhavecodingproblem.wordsrpg.databinding.ActivityLibraryBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import com.uhavecodingproblem.wordsrpg.util.Logger

class LibraryActivity : BaseActivity<ActivityLibraryBinding>(R.layout.activity_library), LibraryActivityRecyclerViewAdapter.GridItemClickListener {

    private var libraryItem: WordType? = null

    override fun ActivityLibraryBinding.onCreate() {
        Logger.v("실행")

        getLibraryItem()
        setToolbarTitle()
        setRecyclerView()

    }

    private fun getLibraryItem(){
        intent?.let {
            libraryItem = it.getParcelableExtra("Words")
        }
    }

    private fun setLibraryList(): MutableList<WordType>{
        val item = mutableListOf<WordType>()
        val word = mutableListOf<WordData>()
        var count = 1
        libraryItem?.words?.let {
            for (i in it.indices){
                if (word.size == 40) {
                    item.add(WordType(type = "수준별", name = "스탭 $count", backgroundImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg", words = word))
                    count++
                    word.clear()
                }
                word.add(it[i])
            }
            if (!word.isNullOrEmpty())
                item.add(WordType(type = "수준별", name = "스탭 $count", backgroundImage = "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg", words = word))
        }
        return item
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
        }
        return false
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
            Logger.v(setLibraryList().size.toString())
            adapter = LibraryActivityRecyclerViewAdapter(setLibraryList(), this@LibraryActivity)
            layoutManager = GridLayoutManager(this@LibraryActivity, 3)
        }
    }

    override fun onItemClick(v: View, clickItem: MutableList<WordData>, position: Int) {
        Intent(this@LibraryActivity, MemorizationActivity::class.java).also {
            it.putExtra("Memorization", ArrayList(clickItem))
            startActivity(it)
        }
    }
}