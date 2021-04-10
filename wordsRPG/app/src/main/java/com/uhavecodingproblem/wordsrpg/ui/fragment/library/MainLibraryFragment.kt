package com.uhavecodingproblem.wordsrpg.ui.fragment.library

import androidx.fragment.app.viewModels
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.application.RUCPApplication
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.BasicPackageTabObserveViewModel
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.factory.ViewModelFactory
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMainLibraryBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import com.uhavecodingproblem.wordsrpg.util.Logger


class MainLibraryFragment : BaseUtility.BaseFragment<FragmentMainLibraryBinding>(R.layout.fragment_main_library) {


    private val tabName = listOf("기본 패키지", "커스텀", "구독", "내 패키지")
    private val mainBasicPackageTabObserveViewModel by viewModels<BasicPackageTabObserveViewModel> { ViewModelFactory(RUCPApplication.userId, tabName, (requireActivity().application as RUCPApplication).repository)}


    override fun FragmentMainLibraryBinding.onCreateView() {

        Logger.d("실행")

        initBinding()
        setEnableSwipe()
//        setDisableSwipe() // 리사이클러뷰 제스처와 같이 쓸 시 불편할 수 있어서 잠금
    }

    private fun initBinding(){
        binding.run {
            libraryviewmodel = mainBasicPackageTabObserveViewModel
            activityfragment = requireActivity()
            lifecycleOwner = this@MainLibraryFragment
        }
    }

    private fun setDisableSwipe(){
        binding.viewpager2Library.isUserInputEnabled = false
    }

    private fun setEnableSwipe(){
        binding.viewpager2Library.isUserInputEnabled = true
    }

}