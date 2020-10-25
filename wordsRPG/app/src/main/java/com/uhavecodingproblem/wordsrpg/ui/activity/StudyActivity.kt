package com.uhavecodingproblem.wordsrpg.ui.activity

import android.media.AudioManager
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.ERROR
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.viewpager2.widget.ViewPager2
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.StudyViewPagerAdapter
import com.uhavecodingproblem.wordsrpg.component.viewmodel.WordViewModel
import com.uhavecodingproblem.wordsrpg.component.viewmodel.factory.WordViewModelFactory
import com.uhavecodingproblem.wordsrpg.data.Stage
import com.uhavecodingproblem.wordsrpg.databinding.ActivityStudyBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import com.uhavecodingproblem.wordsrpg.util.Logger
import java.util.*

class StudyActivity :
    BaseActivity<ActivityStudyBinding>(R.layout.activity_study),
    StudyViewPagerAdapter.ItemClickListener {

    private val studyViewModel: WordViewModel by viewModels { WordViewModelFactory() }
    private var word: Stage? = null
    private var textToSpeech: TextToSpeech? = null
    private lateinit var studyRecyclerviewAdapter: StudyViewPagerAdapter

    override fun ActivityStudyBinding.onCreate() {
        Logger.v("실행")

        initTextToSpeech()
        setToolbarTitle()
        setWord()
        initBinding()
        setViewPager()
    }

    /**
     *
     * TTS Sound = 시스템사운드가아닌 미디어 사운드에 영향을 받기 때문에 해당액티비티에서 사운드조절할 때 미디어 사운드 조절가능하게 설정.
     * https://developer.android.com/guide/topics/media-apps/mediabuttons?hl=ko
     *
     */

    override fun onResume() {
        super.onResume()
        volumeControlStream = AudioManager.STREAM_MUSIC
    }

    private fun initBinding() {
        binding.run {
            studyviewmodel = studyViewModel
            lifecycleOwner = this@StudyActivity
        }
    }

    private fun setWord() {
        intent?.let {
            word = it.getParcelableExtra("StudyWord")
        }
    }

    private fun setViewPager() {
        binding.viewpager2Study.apply {
            studyRecyclerviewAdapter = StudyViewPagerAdapter(word?.words!!, this@StudyActivity)
            adapter = studyRecyclerviewAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
    }

    private fun setToolbarTitle() {
        setSupportActionBar(binding.studyToolbar)

        val actionbar = supportActionBar
        actionbar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.title = "테스트"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.study_toolbar_menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                exitStudyDialog()
            }
            R.id.hide_word -> {
                studyRecyclerviewAdapter.hideWord()
            }
            R.id.hide_mean -> {
                studyRecyclerviewAdapter.hideMean()
            }
            R.id.print_test_paper -> {
                Toast.makeText(
                    this@StudyActivity,
                    R.string.toolbar_menu_for_print_test_paper_as_pdf,
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.take_an_exam -> {
                Toast.makeText(
                    this@StudyActivity,
                    R.string.toolbar_menu_for_take_an_exam,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return true
    }

    private fun initTextToSpeech() {
        textToSpeech = TextToSpeech(
            this@StudyActivity
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
            it.speak(word?.words!![position].word, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (textToSpeech != null) {
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
            textToSpeech = null
        }
    }

    private fun exitStudyDialog() {
        val exitDialog: AlertDialog? = this@StudyActivity.let {
            val exitBuilder = AlertDialog.Builder(it)
            exitBuilder.apply {
                setMessage("학습을 종료하시겠습니까?")
                setPositiveButton("종료") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    finish()
//                    Intent(this@StudyActivity, LibraryActivity::class.java).also { intent ->
//                        startActivity(intent)
//                        finish()
//                    }
                }
            }
            exitBuilder.create()
        }
        exitDialog?.show()
    }

    override fun onBackPressed() {
        exitStudyDialog()
    }
}