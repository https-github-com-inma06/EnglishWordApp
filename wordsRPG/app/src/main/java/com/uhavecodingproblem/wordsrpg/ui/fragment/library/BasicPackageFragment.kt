package com.uhavecodingproblem.wordsrpg.ui.fragment.library

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.application.RUCPApplication
import com.uhavecodingproblem.wordsrpg.component.library.recyclerviewadapter.MainLibraryFragmentBasicPackageListAdapter
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.BasicPackageViewModel
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.factory.ViewModelFactory
import com.uhavecodingproblem.wordsrpg.data.model.ResponseBasicPackage
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

    private val basicPackageViewModel by viewModels<BasicPackageViewModel> { ViewModelFactory(RUCPApplication.userId, null, (requireActivity().application as RUCPApplication).repository) }

    private var basicRecyclerViewAdapter: MainLibraryFragmentBasicPackageListAdapter? = null
    private var progressDialog: SearchLoadingDialog? = null
    private var dialogFragment: DialogFragment? = null


    override fun FragmentBasicPackageBinding.onCreateView() {

        Logger.d("실행")
        progressDialog = SearchLoadingDialog((requireContext()))

        initBinding()
        observeLoading()
    }

    private fun initBinding() {
        binding.run {
            lifecycleOwner = this@BasicPackageFragment
            initRecyclerView()
        }
    }

    private fun observeLoadBasicPackage() {
        basicPackageViewModel.selectBasicPackage.observe(viewLifecycleOwner){
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
        basicPackageViewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it)
                progressDialog?.showLoading()
            else
                progressDialog?.dismissLoading()
        })
    }

    override fun onItemClick(selectedItem: ResponseBasicPackage.BasicPackage) {
        dialogFragment = StageDialogFragment.newInstance(selectedItem)
        dialogFragment?.show(childFragmentManager, "StageDialog")
    }

    override fun onResume() {
        super.onResume()
        Logger.d("BasicPackageFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        Logger.d("BasicPackageFragment onPause")
    }

}