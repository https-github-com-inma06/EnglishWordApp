package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.content.Intent
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.uhavecodingproblem.wordsrpg.R
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
class BasicPackageFragment : BaseFragment<FragmentBasicPackageBinding>(R.layout.fragment_basic_package) {

    private val tabItemName = listOf("수준별", "시험별", "카테고리별")
    private val basicViewModel: LibraryViewModel by viewModels { ViewModelFactory(tabItemName) }

    override fun FragmentBasicPackageBinding.onCreateView() {
        Logger.v("실행")

        initBinding()
        observeTabLayoutPosition()
    }

    private fun initBinding(){
        binding.run {
            libraryviewmodel = basicViewModel
            lifecycleOwner = this@BasicPackageFragment
            basicFragment = this@BasicPackageFragment
        }
    }

    private fun observeTabLayoutPosition(){
        basicViewModel.position.observe(viewLifecycleOwner, Observer {
            Logger.v("position : $it")
        })
    }

    fun moveLibrary(view: View){
        Intent(requireActivity(), LibraryActivity::class.java).also{
            startActivity(it)
        }
    }
}