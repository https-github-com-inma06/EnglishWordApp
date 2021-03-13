package com.uhavecodingproblem.wordsrpg.ui.activity.library

import android.media.AudioManager
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.ERROR
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.viewpageradapter.StudyActivityViewPagerAdapter
import com.uhavecodingproblem.wordsrpg.data.mockdata.StageInformation
import com.uhavecodingproblem.wordsrpg.databinding.ActivityStudyBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import com.uhavecodingproblem.wordsrpg.util.Logger
import java.util.*

class StudyActivity :
    BaseUtility.BaseActivity<ActivityStudyBinding>(R.layout.activity_study),
    StudyActivityViewPagerAdapter.ItemClickListener {

    private var stageInformationInformation: StageInformation? = null
    private var wordTextToSpeech: TextToSpeech? = null
    private var studyActivityRecyclerviewAdapter: StudyActivityViewPagerAdapter? = null

    override fun ActivityStudyBinding.onCreate() {
        Logger.v("실행")

        initTextToSpeech()
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
            lifecycleOwner = this@StudyActivity
        }
    }

    private fun setWord() {
        intent?.let {
            stageInformationInformation = it.getParcelableExtra("StudyWord")
            stageInformationInformation?.let { stage ->
                setToolbarTitle("${it.getStringExtra("PackageName")} LV ${stage.stageNum}")
                stage.wordList[0].isStudyPassed = true
            }
        }
    }

    private fun setViewPager() {
        binding.viewpager2Study.apply {
            studyActivityRecyclerviewAdapter =
                StudyActivityViewPagerAdapter(
                    stageInformationInformation?.wordList!!,
                    this@StudyActivity.lifecycle,
                    this@StudyActivity
                )
            adapter = studyActivityRecyclerviewAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            isUserInputEnabled = false
            setMoveToRecentPosition(getRecentPosition())
            registerOnPageChangeCallback(pageChangeCallback)
        }
    }

    private fun getRecentPosition(): Int {
        var recent = 0
        for (i in stageInformationInformation?.wordList?.indices!!) {
            if (!stageInformationInformation?.wordList!![i].isStudyPassed)
                return recent
            else
                recent = i
        }
        return recent
    }

    /**
     *
     * https://stackoverflow.com/questions/56311862/viewpager2-default-position
     *
     */

    private fun setMoveToRecentPosition(position: Int) {
        val recyclerview = binding.viewpager2Study.getChildAt(0) as RecyclerView
        recyclerview.apply {
            val itemCount = adapter?.itemCount ?: 0
            if (itemCount >= position) {
                viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        viewTreeObserver.removeOnGlobalLayoutListener(this)
                        recyclerview.scrollToPosition(position)
                    }
                })
            }
        }
    }

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            Logger.v("Position :: $position")
            /**
             *
             * 이전코드는 아래와 같은 경고메세지를 받음
             * Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.
             * https://stackoverflow.com/questions/42944005/recyclerview-cannot-call-this-method-in-a-scroll-callback 를 보고 해당 부분 수정
             *
             */
            binding.viewpager2Study.post {
                studyActivityRecyclerviewAdapter?.setCurrentPosition(position)
                studyActivityRecyclerviewAdapter?.notifyItemChanged(position, Unit)
            }
        }
    }

    private fun setToolbarTitle(toolbarTitle: String) {
        setSupportActionBar(binding.studyToolbar)

        val actionbar = supportActionBar
        actionbar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.title = toolbarTitle
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
                studyActivityRecyclerviewAdapter?.hideWord()
            }
            R.id.hide_mean -> {
                studyActivityRecyclerviewAdapter?.hideMean()
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
        wordTextToSpeech = TextToSpeech(
            this@StudyActivity
        ) { status ->
            if (status != ERROR) {
                wordTextToSpeech?.language = Locale.ENGLISH
            }
        }
    }

    override fun onMicClick(v: View, position: Int) {
        wordTextToSpeech?.let {
            Log.e("Test", "Mic")

            it.setPitch(1.0f) // 기본톤
            it.setSpeechRate(1.0f) // 기본속도
            it.speak(stageInformationInformation?.wordList!![position].word, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onNextBtnClick(v: View, position: Int) {
        if (position == stageInformationInformation?.wordList?.size!! - 1)
            Toast.makeText(this, "테스트 보러가기", Toast.LENGTH_SHORT).show()
        else
            binding.viewpager2Study.currentItem += 1


        stageInformationInformation?.let {
            it.wordList[position].isStudyPassed = true
        }

    }

    override fun onPreviousBtnClick(v: View, position: Int) {
        binding.viewpager2Study.currentItem -= 1
    }

    override fun onVideoClick(v: View, position: Int) {
        Toast.makeText(
            this,
            "Move To YoutubePlayer word = ${stageInformationInformation?.wordList!![position].word}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (wordTextToSpeech != null) {
            wordTextToSpeech!!.stop()
            wordTextToSpeech!!.shutdown()
            wordTextToSpeech = null
        }
        binding.viewpager2Study.unregisterOnPageChangeCallback(pageChangeCallback)
    }

    private fun exitStudyDialog() {
        val exitDialog: AlertDialog = this@StudyActivity.let {
            val exitBuilder = AlertDialog.Builder(it)
            exitBuilder.apply {
                setMessage("학습을 종료하시겠습니까?")
                setPositiveButton("종료") { dialogInterface, _ ->
                    //TODO Server update
                    dialogInterface.dismiss()
                    finish()
                }
            }
            exitBuilder.create()
        }
        exitDialog.show()
    }

    override fun onBackPressed() {
        exitStudyDialog()
    }
}