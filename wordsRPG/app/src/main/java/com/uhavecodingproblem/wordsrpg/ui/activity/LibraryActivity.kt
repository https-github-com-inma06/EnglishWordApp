package com.uhavecodingproblem.wordsrpg.ui.activity

import android.content.Intent
import android.view.View
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityLibraryBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import com.uhavecodingproblem.wordsrpg.util.Logger

class LibraryActivity : BaseActivity<ActivityLibraryBinding>(R.layout.activity_library) {

    override fun ActivityLibraryBinding.onCreate() {
        Logger.v("실행")
        binding.libraryActivity = this@LibraryActivity
    }

    fun moveMemorization(v: View){
        Intent(this, MemorizationActivity::class.java).also {
            startActivity(it)
        }
    }
}