package com.uhavecodingproblem.wordsrpg.ui.activity.library

import android.media.AudioManager
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.ERROR
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.WordObserveViewModel
import com.uhavecodingproblem.wordsrpg.component.library.viewpageradapter.StudyActivityViewPagerAdapter
import com.uhavecodingproblem.wordsrpg.data.model.Learning
import com.uhavecodingproblem.wordsrpg.data.model.PackageWithStage
import com.uhavecodingproblem.wordsrpg.data.model.WordsRead
import com.uhavecodingproblem.wordsrpg.databinding.ActivityStudyBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import com.uhavecodingproblem.wordsrpg.ui.dialog.SearchLoadingDialog
import com.uhavecodingproblem.wordsrpg.util.Logger
import java.util.*

class StudyActivity :
    BaseUtility.BaseActivity<ActivityStudyBinding>(R.layout.activity_study),
    StudyActivityViewPagerAdapter.ItemClickListener {

    private var stage: Learning? = null
    private var wordTextToSpeech: TextToSpeech? = null
    private var studyActivityRecyclerviewAdapter: StudyActivityViewPagerAdapter? = null
    private val viewModel by viewModels<WordObserveViewModel>()
    private var loadingDialog: SearchLoadingDialog? = null
    private val wordList = mutableListOf<WordsRead>()
    private var isScrolling = false

    override fun ActivityStudyBinding.onCreate() {
        Logger.d("실행")

        loadingDialog = SearchLoadingDialog(this@StudyActivity)
        initTextToSpeech()
        setWord()
        initBinding()
        setViewPager()

        observeLoading()
        observeWord()
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
            it.getParcelableExtra<Learning>("stage")?.let { learning ->
                stage = learning
                viewModel.load(learning.p_id, learning.s_id)
            }
            it.getParcelableExtra<PackageWithStage>("packageWithStage")?.let { packageInfo ->
                setToolbarTitle(packageInfo.package_name)
            }
        }
    }

    private fun observeLoading() {
        viewModel.loading.observe(this) {
            if (it)
                loadingDialog?.showLoading()
            else
                loadingDialog?.dismissLoading()
        }
    }

    private fun observeWord() {
        viewModel.wordList.observe(this) {
            wordList.clear()
            wordList.addAll(it)
            studyActivityRecyclerviewAdapter?.notifyDataSetChanged()
            setMoveToRecentPosition(stage?.current_page?.toInt())
            Logger.e("${it.size} ${studyActivityRecyclerviewAdapter?.itemCount}")
        }
    }

    private fun setViewPager() {
        binding.viewpager2Study.apply {
            studyActivityRecyclerviewAdapter =
                StudyActivityViewPagerAdapter(
                    wordList,
                    this@StudyActivity
                )
            adapter = studyActivityRecyclerviewAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            isUserInputEnabled = false
            registerOnPageChangeCallback(pageChangeCallback)
        }
    }

    /**
     *
     * https://stackoverflow.com/questions/56311862/viewpager2-default-position
     *
     */

    private fun setMoveToRecentPosition(position: Int?) {
        position?.let {
            val recyclerview = binding.viewpager2Study.getChildAt(0) as RecyclerView
            recyclerview.apply {
                val itemCount = adapter?.itemCount ?: 0
                if (itemCount >= it) {
                    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                        override fun onGlobalLayout() {
                            viewTreeObserver.removeOnGlobalLayoutListener(this)
                            studyActivityRecyclerviewAdapter?.setCurrentPosition(it - 1)
                            recyclerview.scrollToPosition(it - 1)
                        }
                    })
                }
            }
        }
    }

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {



        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            Logger.d("Position :: $position")
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

        override fun onPageScrollStateChanged(state: Int) {
            isScrolling = state != ViewPager2.SCROLL_STATE_IDLE
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
//        wordTextToSpeech?.let {
//            Log.e("Test", "Mic")
//
//            it.setPitch(1.0f) // 기본톤
//            it.setSpeechRate(1.0f) // 기본속도
//            it.speak(stageInformationInformation?.wordList!![position].word, TextToSpeech.QUEUE_FLUSH, null, null)
//        }
    }

    override fun onNextBtnClick(v: View, position: Int) {
        if (!isScrolling) {
            if (position == wordList.size - 1)
                Toast.makeText(this, "테스트 보러가기", Toast.LENGTH_SHORT).show()
            else
                binding.viewpager2Study.currentItem += 1

            stage?.let {
                viewModel.updateLearning(it.l_id, it, binding.viewpager2Study.currentItem + 1)
            }
        }

    }

    override fun onPreviousBtnClick(v: View, position: Int) {
        if (!isScrolling) {
            binding.viewpager2Study.currentItem -= 1
            stage?.let {
                viewModel.updateLearning(it.l_id, it, binding.viewpager2Study.currentItem + 1)
            }
        }
    }

    override fun onVideoClick(v: View, position: Int) {
        Toast.makeText(
            this,
            "Move To YoutubePlayer word = ${wordList[position].word}",
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