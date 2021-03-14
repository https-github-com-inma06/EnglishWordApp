package com.uhavecodingproblem.wordsrpg.ui.fragment.library

import androidx.fragment.app.viewModels
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.viewmodel.BasicPackageTabObserveViewModel
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMainLibraryBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import com.uhavecodingproblem.wordsrpg.util.Logger


/**
 * wordsrpg
 * Class: MainLibraryFragment.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 * 라이브러리 프레그먼트입니다.
 */
class MainLibraryFragment : BaseUtility.BaseFragment<FragmentMainLibraryBinding>(R.layout.fragment_main_library) {


    private val tabLayoutName = listOf("기본 패키지", "커스텀", "구독", "내 패키지")
    private val mainBasicPackageTabObserveViewModel: BasicPackageTabObserveViewModel by viewModels ()


    override fun FragmentMainLibraryBinding.onCreateView() {

        Logger.v("실행")

        initBinding()
        setDisableSwipe()
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

}