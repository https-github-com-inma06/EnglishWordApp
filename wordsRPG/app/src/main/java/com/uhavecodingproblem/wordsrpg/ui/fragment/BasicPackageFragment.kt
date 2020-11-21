package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.recyclerviewadpter.MainLibraryFragmentBasicPackageRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.component.viewmodel.LibraryViewModel
import com.uhavecodingproblem.wordsrpg.component.viewmodel.WordViewModel
import com.uhavecodingproblem.wordsrpg.component.viewmodel.factory.LibraryViewModelFactory
import com.uhavecodingproblem.wordsrpg.component.viewmodel.factory.WordViewModelFactory
import com.uhavecodingproblem.wordsrpg.data.PackageInformation
import com.uhavecodingproblem.wordsrpg.databinding.FragmentBasicPackageBinding
import com.uhavecodingproblem.wordsrpg.dialog.StageDialog
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.util.Logger

/**
 * wordsrpg
 * Class: BasicPackageFragment
 * Created by pyg10.
 * Created On 2020-09-18.
 * Description:
 */
class BasicPackageFragment : BaseFragment<FragmentBasicPackageBinding>(R.layout.fragment_basic_package),
    MainLibraryFragmentBasicPackageRecyclerViewAdapter.BasicPackageGridItemClickListener {

    private val tabItemName = listOf("수준별", "시험별", "카테고리별")
    private val libraryViewModel: LibraryViewModel by viewModels { LibraryViewModelFactory(tabItemName) }
    private val wordViewModel: WordViewModel by viewModels { WordViewModelFactory() }
    private var basicRecyclerViewAdapter: MainLibraryFragmentBasicPackageRecyclerViewAdapter? = null
    private var wordList = mutableListOf<PackageInformation>()
    private var stageDialog: StageDialog? = null

    override fun FragmentBasicPackageBinding.onCreateView() {
        Logger.v("실행")

        initBinding()
        observeTabLayoutPosition()
        byLevelRecyclerView()

    }

    private fun initBinding() {
        binding.run {
            libraryviewmodel = libraryViewModel
            librarywordviewmodel = wordViewModel
            lifecycleOwner = this@BasicPackageFragment
        }
    }

    private fun byLevelRecyclerView() {
        if (basicRecyclerViewAdapter == null) {
            basicRecyclerViewAdapter = MainLibraryFragmentBasicPackageRecyclerViewAdapter(wordList, this)
            binding.recyclerview.apply {
                adapter = basicRecyclerViewAdapter
                layoutManager = setGridLayout()
            }
        }
        setListData(basicRecyclerViewAdapter, wordViewModel.getByLevelWord())
    }

    private fun setGridLayout(): GridLayoutManager {
        return GridLayoutManager(requireContext(), 3)
    }

    private fun testRecyclerView() {
        if (basicRecyclerViewAdapter == null) {
            basicRecyclerViewAdapter = MainLibraryFragmentBasicPackageRecyclerViewAdapter(wordList, this)
            binding.recyclerview.apply {
                adapter = basicRecyclerViewAdapter
                layoutManager = setGridLayout()
            }
        }
        setListData(basicRecyclerViewAdapter, wordViewModel.getByTestWord())
    }

    private fun setListData(adapter: MainLibraryFragmentBasicPackageRecyclerViewAdapter?, data: MutableList<PackageInformation>) {
        adapter?.updateData(data)
    }

//    private fun categoryRecyclerView(){
//        val basicRecyclerViewAdapter = ByCategoryRecyclerViewAdapter(word, this)
//        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
//        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
//            override fun getSpanSize(position: Int): Int {
//                return if(basicRecyclerViewAdapter.isHeader(position)) gridLayoutManager.spanCount else 1
//            }
//        }
//        binding.recyclerview.apply {
//            adapter = basicRecyclerViewAdapter
//            layoutManager = gridLayoutManager
//        }
//    }

    private fun setDialog() {

        val dialog: AlertDialog? = requireActivity().let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage("현재 준비중입니다.")
            }
            builder.create()
        }
        dialog?.show()

    }

    private fun observeTabLayoutPosition() {
        libraryViewModel.position.observe(viewLifecycleOwner, Observer {
            when (it) {
                0 -> byLevelRecyclerView()
                1 -> testRecyclerView()
                2 -> setDialog()
            }
        })
    }

    override fun onItemClick(view: View, position: Int, isByLevel: Boolean) {
        stageDialog = StageDialog(requireContext(), wordList[position])
        wordViewModel.setSelectionWord(wordList[position])
        stageDialog?.show()
        dialogResize(stageDialog)
    }

    private fun dialogResize(dialog: Dialog?) {

        if (Build.VERSION.SDK_INT < 30) {
            val display = requireActivity().windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val window = dialog?.window

            val x = (size.x * 0.95f).toInt()
            val y = (size.y * 0.9f).toInt()
            window?.setLayout(x, y)

        } else {
            val windowManager = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager

            val rect = windowManager.currentWindowMetrics.bounds

            val window = dialog?.window

            val x = (rect.width() * 0.95f).toInt()
            val y = (rect.height() * 0.9f).toInt()

            window?.setLayout(x, y)
        }
    }

    override fun onDestroyView() {
        if (stageDialog?.isShowing!!)
            stageDialog?.dismiss()
        super.onDestroyView()
    }
}