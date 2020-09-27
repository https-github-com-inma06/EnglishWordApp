package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.content.Intent
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.ByCategoryRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.component.ByLevelRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.component.ByTestRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.component.viewmodel.LibraryViewModel
import com.uhavecodingproblem.wordsrpg.component.viewmodel.factory.ViewModelFactory
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
class BasicPackageFragment : BaseFragment<FragmentBasicPackageBinding>(R.layout.fragment_basic_package), ByLevelRecyclerViewAdapter.GridItemClickListener {

    private val tabItemName = listOf("수준별", "시험별", "카테고리별")
    private val basicViewModel: LibraryViewModel by viewModels { ViewModelFactory(tabItemName) }
    private val word = listOf("A","A","A","A","A","A","A","A","A","A","A")
    private var basicRecyclerViewAdapter: ByLevelRecyclerViewAdapter? = null

    override fun FragmentBasicPackageBinding.onCreateView() {
        Logger.v("실행")

        initBinding()
        observeTabLayoutPosition()
        basicRecyclerView()
    }

    private fun initBinding(){
        binding.run {
            libraryviewmodel = basicViewModel
            lifecycleOwner = this@BasicPackageFragment
        }
    }

    private fun basicRecyclerView(){
        basicRecyclerViewAdapter = ByLevelRecyclerViewAdapter(word, this)
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return if(basicRecyclerViewAdapter!!.isHeader(position)) gridLayoutManager.spanCount else 1
            }
        }
        binding.recyclerview.apply {
            adapter = basicRecyclerViewAdapter
            layoutManager = gridLayoutManager
        }
    }

//    private fun testRecyclerView(){
//        val basicRecyclerViewAdapter = ByTestRecyclerViewAdapter(word, this)
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

    private fun observeTabLayoutPosition(){
        basicViewModel.position.observe(viewLifecycleOwner, Observer {
            when(it){
                0 -> basicRecyclerView()
//                1 -> testRecyclerView()
//                2 -> categoryRecyclerView()
            }
        })
    }

    fun moveLibrary(view: View){
        Intent(requireActivity(), LibraryActivity::class.java).also{
            startActivity(it)
        }
    }

    override fun onItemClick(view: View, position: Int) {
        Intent(requireActivity(), LibraryActivity::class.java).also{
            startActivity(it)
        }
    }
}