package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.BasicPackageRecyclerView
import com.uhavecodingproblem.wordsrpg.component.viewmodel.LibraryViewModel
import com.uhavecodingproblem.wordsrpg.component.viewmodel.WordViewModel
import com.uhavecodingproblem.wordsrpg.component.viewmodel.factory.ViewModelFactory
import com.uhavecodingproblem.wordsrpg.component.viewmodel.factory.WordViewModelFactory
import com.uhavecodingproblem.wordsrpg.data.WordType
import com.uhavecodingproblem.wordsrpg.databinding.FragmentBasicPackageBinding
import com.uhavecodingproblem.wordsrpg.dialog.BasicPackageDialog
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.util.hideKeyboard

/**
 * wordsrpg
 * Class: BasicPackageFragment
 * Created by pyg10.
 * Created On 2020-09-18.
 * Description:
 */
class BasicPackageFragment : BaseFragment<FragmentBasicPackageBinding>(R.layout.fragment_basic_package),
    BasicPackageRecyclerView.BasicPackageGridItemClickListener {

    private val tabItemName = listOf("수준별", "시험별", "카테고리별")
    private val basicViewModel: LibraryViewModel by viewModels { ViewModelFactory(tabItemName) }
    private val wordViewModel: WordViewModel by viewModels { WordViewModelFactory() }
    private var basicRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private var wordList = mutableListOf<WordType>()

    override fun FragmentBasicPackageBinding.onCreateView() {
        Logger.v("실행")

        initBinding()
        observeTabLayoutPosition()
        byLevelRecyclerView()
    }

    private fun initBinding() {
        binding.run {
            libraryviewmodel = basicViewModel
            librarywordviewmodel = wordViewModel
            lifecycleOwner = this@BasicPackageFragment
        }
    }

    private fun byLevelRecyclerView() {
        if (basicRecyclerViewAdapter == null) {
            basicRecyclerViewAdapter = BasicPackageRecyclerView(wordList, this)
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
            basicRecyclerViewAdapter = BasicPackageRecyclerView(wordList, this)
            binding.recyclerview.apply {
                adapter = basicRecyclerViewAdapter
                layoutManager = setGridLayout()
            }
        }
        setListData(basicRecyclerViewAdapter, wordViewModel.getByTestWord())
    }

    private fun setListData(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?, data: MutableList<WordType>){
        if (wordList.isNullOrEmpty()) {
            wordList.addAll(data)
            adapter?.notifyDataSetChanged()
        }else{
            wordList.clear()
            wordList.addAll(data)
            adapter?.notifyDataSetChanged()
        }
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
        basicViewModel.position.observe(viewLifecycleOwner, Observer {
            when (it) {
                0 -> byLevelRecyclerView()
                1 -> testRecyclerView()
                2 -> setDialog()
            }
        })
    }

    override fun onItemClick(view: View, position: Int, isByLevel: Boolean) {
        val dialog = if (isByLevel)
            BasicPackageDialog(requireContext(), wordList[position])
        else
            BasicPackageDialog(requireContext(), wordList[position])
        dialog.show()
        dialogResize(dialog)
    }

    private fun dialogResize(dialog: Dialog) {

        if (Build.VERSION.SDK_INT < 30) {
            val display = requireActivity().windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val window = dialog.window

            val x = (size.x * 0.95f).toInt()
            val y = (size.y * 0.9f).toInt()
            window?.setLayout(x, y)

        }else{
            val windowManager = requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager

            val rect = windowManager.currentWindowMetrics.bounds

            val window = dialog.window

            val x = (rect.width() * 0.95f).toInt()
            val y = (rect.height() * 0.9f).toInt()

            window?.setLayout(x, y)
        }
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }

}