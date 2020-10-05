package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.ByLevelRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.component.ByTestRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.component.viewmodel.LibraryViewModel
import com.uhavecodingproblem.wordsrpg.component.viewmodel.WordViewModel
import com.uhavecodingproblem.wordsrpg.component.viewmodel.factory.ViewModelFactory
import com.uhavecodingproblem.wordsrpg.component.viewmodel.factory.WordViewModelFactory
import com.uhavecodingproblem.wordsrpg.data.WordType
import com.uhavecodingproblem.wordsrpg.databinding.FragmentBasicPackageBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.LibraryActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.util.Logger

/**
 * wordsrpg
 * Class: BasicPackageFragment
 * Created by pyg10.
 * Created On 2020-09-18.
 * Description:
 */
class BasicPackageFragment : BaseFragment<FragmentBasicPackageBinding>(R.layout.fragment_basic_package), ByLevelRecyclerViewAdapter.ByLevelGridItemClickListener, ByTestRecyclerViewAdapter.ByTestGridItemClickListener {

    private val tabItemName = listOf("수준별", "시험별", "카테고리별")
    private val basicViewModel: LibraryViewModel by viewModels { ViewModelFactory(tabItemName) }
    private val wordViewModel: WordViewModel by viewModels { WordViewModelFactory() }
    private var basicRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private var byLevelWord = mutableListOf<WordType>()
    private var byTestWord = mutableListOf<WordType>()

    override fun FragmentBasicPackageBinding.onCreateView() {
        Logger.v("실행")

        initBinding()
        observeTabLayoutPosition()
        byLevelRecyclerView()
    }

    private fun initBinding(){
        binding.run {
            libraryviewmodel = basicViewModel
            librarywordviewmodel = wordViewModel
            lifecycleOwner = this@BasicPackageFragment
            byLevelWord = wordViewModel.getByLevelWord()
            byTestWord = wordViewModel.getByTestWord()
        }
    }

    private fun byLevelRecyclerView(){
        basicRecyclerViewAdapter = ByLevelRecyclerViewAdapter(byLevelWord, this)
        binding.recyclerview.apply {
            adapter = basicRecyclerViewAdapter
            layoutManager = setWithHeaderGridLayout()
        }
    }

    private fun setWithHeaderGridLayout() : GridLayoutManager{
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return if((basicRecyclerViewAdapter!! as ByLevelRecyclerViewAdapter).isHeader(position)) gridLayoutManager.spanCount else 1
            }
        }
        return gridLayoutManager
    }

    private fun setNoHeaderGridLayout() : GridLayoutManager{
        return GridLayoutManager(requireContext(), 3)
    }

    private fun testRecyclerView(){
        val basicRecyclerViewAdapter = ByTestRecyclerViewAdapter(byTestWord, this)
        binding.recyclerview.apply {
            adapter = basicRecyclerViewAdapter
            layoutManager = setNoHeaderGridLayout()
        }
    }
//
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

    private fun setDialog(){

        val dialog: AlertDialog? = requireActivity().let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage("현재 준비중입니다.")
            }

            builder.create()
        }
        dialog?.show()

    }

    private fun observeTabLayoutPosition(){
        basicViewModel.position.observe(viewLifecycleOwner, Observer {
            when(it){
                0 -> byLevelRecyclerView()
                1 -> testRecyclerView()
                2 -> setDialog()
            }
        })
    }

    override fun onByLevelItemClick(view: View, position: Int) {
        Intent(requireActivity(), LibraryActivity::class.java).also{
            it.putExtra("Words", byLevelWord[position])
            startActivity(it)
        }
    }

    override fun onByTestItemClick(view: View, position: Int) {
        Intent(requireActivity(), LibraryActivity::class.java).also{
            it.putExtra("Words", byTestWord[position])
            startActivity(it)
        }
    }
}