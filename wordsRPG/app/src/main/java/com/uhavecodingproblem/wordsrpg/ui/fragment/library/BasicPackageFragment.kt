package com.uhavecodingproblem.wordsrpg.ui.fragment.library

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.application.Application
import com.uhavecodingproblem.wordsrpg.component.library.recyclerviewadpter.MainLibraryFragmentBasicPackageListAdapter
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.BasicPackageTabObserveViewModel
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.PackageObserveViewModel
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.factory.BasicPackageTabObserveViewModelFactory
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.factory.PackageObserveViewModelFactory
import com.uhavecodingproblem.wordsrpg.data.PackageInformation
import com.uhavecodingproblem.wordsrpg.databinding.FragmentBasicPackageBinding
import com.uhavecodingproblem.wordsrpg.dialog.SearchLoadingDialog
import com.uhavecodingproblem.wordsrpg.dialog.StageDialogFragment
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.util.Logger
import java.util.*

/**
 * wordsrpg
 * Class: BasicPackageFragment
 * Created by pyg10.
 * Created On 2020-09-18.
 * Description:
 */
class BasicPackageFragment : BaseFragment<FragmentBasicPackageBinding>(R.layout.fragment_basic_package),
    MainLibraryFragmentBasicPackageListAdapter.BasicPackageGridItemClickListener {

    private val tabItemName = listOf("수준별", "시험별", "카테고리별")
    private val basicPackageTabObserveViewModel: BasicPackageTabObserveViewModel by viewModels {
        BasicPackageTabObserveViewModelFactory(
            tabItemName
        )
    }
    private val packageObserveViewModel by viewModels<PackageObserveViewModel> { PackageObserveViewModelFactory(Application.userId) }
    private var basicRecyclerViewAdapter: MainLibraryFragmentBasicPackageListAdapter? = null
    private var progressDialog: SearchLoadingDialog? = null
    private var dialogFragment: DialogFragment? = null


    override fun FragmentBasicPackageBinding.onCreateView() {
        Logger.v("실행")

        progressDialog = SearchLoadingDialog((requireContext()))

        initBinding()
        observeTabLayoutPosition()
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

    private fun setRecyclerItem() {
        packageObserveViewModel.typePackage.observe(viewLifecycleOwner) {
            basicRecyclerViewAdapter?.submitList(it.toMutableList())
        }
    }

    private fun initRecyclerView() {
        basicRecyclerViewAdapter = MainLibraryFragmentBasicPackageListAdapter(this)
        binding.recyclerview.apply {
            adapter = basicRecyclerViewAdapter
            layoutManager = setGridLayout()
        }
        setRecyclerItem()
    }


    private fun setGridLayout(): GridLayoutManager {
        return GridLayoutManager(requireContext(), 3)
    }

    private fun setDialog() {

        val dialog: AlertDialog = requireActivity().let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage("현재 준비중입니다.")
            }
            builder.create()
        }
        dialog.show()

    }

    private fun observeTabLayoutPosition() {
        basicPackageTabObserveViewModel.position.observe(viewLifecycleOwner, Observer {
            when (it) {
                0 -> {
                    packageObserveViewModel.setType("수준별")
                }
                1 -> {
                    packageObserveViewModel.setType("시험별")
                }
                2 -> {
                    setDialog()
                }
                else -> throw IllegalStateException("unKnown Tab Position")
            }
            Logger.v("$it")
        })
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

    override fun onItemClick(selectedItem: PackageInformation) {
        dialogFragment = StageDialogFragment.newInstance(selectedItem.name)
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