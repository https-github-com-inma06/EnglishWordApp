package com.uhavecodingproblem.wordsrpg.ui.activity

import android.content.Intent
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.ERROR
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.MemorizationViewPagerAdapter
import com.uhavecodingproblem.wordsrpg.component.viewmodel.WordViewModel
import com.uhavecodingproblem.wordsrpg.component.viewmodel.factory.WordViewModelFactory
import com.uhavecodingproblem.wordsrpg.data.WordData
import com.uhavecodingproblem.wordsrpg.data.WordType
import com.uhavecodingproblem.wordsrpg.databinding.ActivityMemorizationBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import com.uhavecodingproblem.wordsrpg.util.Logger
import java.util.*

class MemorizationActivity :
    BaseActivity<ActivityMemorizationBinding>(R.layout.activity_memorization), MemorizationViewPagerAdapter.ItemClickListener {

    private val memorizationViewModel: WordViewModel by viewModels { WordViewModelFactory() }
    private var word : MutableList<WordData> = mutableListOf()
    private var textToSpeech: TextToSpeech? = null

    override fun ActivityMemorizationBinding.onCreate() {
        Logger.v("실행")

        initTextToSpeech()
        setToolbarTitle()
        setWord()
        initBinding()
        setViewPager()
    }

    private fun initBinding(){
        binding.run {
            memorizationviewmodel = memorizationViewModel
            lifecycleOwner = this@MemorizationActivity
        }
    }

    private fun setWord(){
        intent?.let {
            word = it.getParcelableArrayListExtra<WordData>("Memorization")?.toMutableList()!!
        }
    }

    private fun setViewPager(){
        binding.memorization.apply {
            adapter = MemorizationViewPagerAdapter(word, this@MemorizationActivity)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
    }

    private fun setToolbarTitle(){
        setSupportActionBar(binding.memorizationToolbar)

        val actionbar = supportActionBar
        actionbar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.title = "테스트"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.memorization_toolbar_menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
               finish()
            }
            R.id.hide_word->{
                Toast.makeText(this@MemorizationActivity, R.string.toolbar_menu_for_hide_all_word, Toast.LENGTH_SHORT).show()
            }
            R.id.hide_mean->{
                Toast.makeText(this@MemorizationActivity, R.string.toolbar_menu_for_hide_all_mean, Toast.LENGTH_SHORT).show()
            }
            R.id.print_test_paper->{
                Toast.makeText(this@MemorizationActivity, R.string.toolbar_menu_for_print_test_paper_as_pdf, Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    private fun initTextToSpeech(){
         textToSpeech = TextToSpeech(this@MemorizationActivity
         ) { status ->
             if (status != ERROR) {
                 textToSpeech?.language = Locale.ENGLISH
             }
         }
    }

    override fun micClick(v: View, position: Int) {
        textToSpeech?.let {
            it.setPitch(1.0f) // 기본톤
            it.setSpeechRate(1.0f) // 기본속도
            it.speak(word[position].word, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(textToSpeech != null){
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
            textToSpeech = null
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}