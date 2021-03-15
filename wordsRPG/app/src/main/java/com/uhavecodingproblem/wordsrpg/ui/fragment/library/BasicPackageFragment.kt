package com.uhavecodingproblem.wordsrpg.ui.fragment.library

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.FirebaseDatabase
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.application.Application
import com.uhavecodingproblem.wordsrpg.component.library.recyclerviewadapter.MainLibraryFragmentBasicPackageListAdapter
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.BasicPackageTabObserveViewModel
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.PackageObserveViewModel
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.factory.ViewModelFactory
import com.uhavecodingproblem.wordsrpg.data.model.PackageWithStage
import com.uhavecodingproblem.wordsrpg.databinding.FragmentBasicPackageBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import com.uhavecodingproblem.wordsrpg.ui.dialog.SearchLoadingDialog
import com.uhavecodingproblem.wordsrpg.ui.dialog.StageDialogFragment
import com.uhavecodingproblem.wordsrpg.util.Logger

/**
 * wordsrpg
 * Class: BasicPackageFragment
 * Created by pyg10.
 * Created On 2020-09-18.
 * Description:
 */
class BasicPackageFragment : BaseUtility.BaseFragment<FragmentBasicPackageBinding>(R.layout.fragment_basic_package),
    MainLibraryFragmentBasicPackageListAdapter.BasicPackageGridItemClickListener {

    private val tabName = listOf("수준별", "시험별", "카테고리별")
    private val basicPackageTabObserveViewModel by viewModels<BasicPackageTabObserveViewModel>{ ViewModelFactory(null, tabName) }
    private val packageObserveViewModel by viewModels<PackageObserveViewModel> { ViewModelFactory(Application.userId, null) }
    private var basicRecyclerViewAdapter: MainLibraryFragmentBasicPackageListAdapter? = null
    private var progressDialog: SearchLoadingDialog? = null
    private var dialogFragment: DialogFragment? = null


    override fun FragmentBasicPackageBinding.onCreateView() {

        Logger.v("실행")
        progressDialog = SearchLoadingDialog((requireContext()))

        initBinding()
        observeLoading()
    }

    private fun initBinding() {
        binding.run {
            libraryviewmodel = basicPackageTabObserveViewModel
            librarywordviewmodel = packageObserveViewModel
            lifecycleOwner = this@BasicPackageFragment
            initRecyclerView()
        }
    }

    private fun observeLoadBasicPackage() {
        packageObserveViewModel.basicPackageInformation.observe(viewLifecycleOwner) {
            basicRecyclerViewAdapter?.submitList(it.toMutableList())
        }

    }

    private fun initRecyclerView() {
        basicRecyclerViewAdapter = MainLibraryFragmentBasicPackageListAdapter(this)
        binding.recyclerview.apply {
            adapter = basicRecyclerViewAdapter
            layoutManager = setGridLayout()
        }
        observeLoadBasicPackage()
    }


    private fun setGridLayout(): GridLayoutManager {
        return GridLayoutManager(requireContext(), 3)
    }

    private fun observeLoading() {
        packageObserveViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it)
                progressDialog?.showLoading()
            else
                progressDialog?.dismissLoading()

            Logger.v("로딩중 :: $it")
        })
    }

    override fun onItemClick(selectedItem: PackageWithStage) {
        dialogFragment = StageDialogFragment.newInstance(selectedItem)
        dialogFragment?.show(childFragmentManager, "StageDialog")
    }

    override fun onResume() {
        super.onResume()
        Logger.v("BasicPackageFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        Logger.v("BasicPackageFragment onPause")
    }

}