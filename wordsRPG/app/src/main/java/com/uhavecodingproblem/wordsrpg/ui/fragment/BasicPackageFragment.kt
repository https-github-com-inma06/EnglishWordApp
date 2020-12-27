package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.recyclerviewadpter.MainLibraryFragmentBasicPackageRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.component.viewmodel.BasicPackageTabObserveViewModel
import com.uhavecodingproblem.wordsrpg.component.viewmodel.PackageObserveViewModel
import com.uhavecodingproblem.wordsrpg.component.viewmodel.factory.BasicPackageTabObserveViewModelFactory
import com.uhavecodingproblem.wordsrpg.component.viewmodel.factory.PackageObserveViewModelFactory
import com.uhavecodingproblem.wordsrpg.data.PackageInformation
import com.uhavecodingproblem.wordsrpg.databinding.FragmentBasicPackageBinding
import com.uhavecodingproblem.wordsrpg.dialog.SearchLoadingDialog
import com.uhavecodingproblem.wordsrpg.dialog.StageDialogFragment
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import com.uhavecodingproblem.wordsrpg.util.Logger
import java.lang.IllegalStateException

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
    private val basicPackageTabObserveViewModel: BasicPackageTabObserveViewModel by viewModels { BasicPackageTabObserveViewModelFactory(tabItemName) }
    private val packageObserveViewModel by activityViewModels<PackageObserveViewModel> { PackageObserveViewModelFactory("A") }
    private var basicRecyclerViewAdapter: MainLibraryFragmentBasicPackageRecyclerViewAdapter? = null
    private var packageList = mutableListOf<PackageInformation>()
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

            //최초실행시 보여지는화면이 수준별이기때문에()
            packageList = packageObserveViewModel.byLevelPackage()

            initRecyclerView()
        }
    }

    private fun initRecyclerView(){
        basicRecyclerViewAdapter = MainLibraryFragmentBasicPackageRecyclerViewAdapter(packageList, this)
        binding.recyclerview.apply{
            adapter = basicRecyclerViewAdapter
            layoutManager = setGridLayout()
        }
    }


    private fun setGridLayout(): GridLayoutManager {
        return GridLayoutManager(requireContext(), 3)
    }

    private fun updateData(adapter: MainLibraryFragmentBasicPackageRecyclerViewAdapter?, data: MutableList<PackageInformation>) {
        adapter?.updateData(data)
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
                    packageList = packageObserveViewModel.byLevelPackage()
                }
                1 -> {
                    packageList = packageObserveViewModel.byTestPackage()
                }
                2 -> {
                    packageList.clear()
                    setDialog()
                }
                else -> throw IllegalStateException("unKnown Tab Position")
            }
            Logger.v("$it")
            updateData(basicRecyclerViewAdapter, packageList)
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

    override fun onItemClick(view: View, position: Int, isByLevel: Boolean) {
        dialogFragment = StageDialogFragment.newInstance(packageList[position].name)
        dialogFragment?.show(childFragmentManager, "StageDialog")
    }

    override fun onResume() {
        super.onResume()
        Logger.v("BasicPackageFragment onResume")

        if (dialogFragment != null) {
            packageObserveViewModel.requestUpdatePackage()
//            when(basicPackageTabObserveViewModel.position.value){
//                0 -> {
//                    packageList = packageObserveViewModel.byLevelPackage()
//                }
//                1 -> {
//                    packageList = packageObserveViewModel.byTestPackage()
//                }
//                2 -> {
//                    packageList.clear()
//                }
//                else -> throw IllegalStateException("unKnown Tab Position")
//            }
//            updateData(basicRecyclerViewAdapter, packageList)
        }

    }

    override fun onPause() {
        super.onPause()
        Logger.v("BasicPackageFragment onPause")
    }

}