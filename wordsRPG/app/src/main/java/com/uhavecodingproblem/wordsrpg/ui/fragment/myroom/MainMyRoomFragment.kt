package com.uhavecodingproblem.wordsrpg.ui.fragment.myroom

import android.content.Intent
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMainMyRoomBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.LoginActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility


/**
 * wordsrpg
 * Class: MainMyRoomFragment.
 * Created by Atanasio.
 * Created On 2021-03-06.
 * Description:
 * 마이룸 프레그먼트입니다.
 */
class MainMyRoomFragment : BaseUtility.BaseFragment<FragmentMainMyRoomBinding>(R.layout.fragment_main_my_room) {
    override fun FragmentMainMyRoomBinding.onCreateView() {
        Logger.d("실행")

        btnGoToTheLogin.setOnClickListener { startActivity(Intent(requireContext(),
        LoginActivity::class.java)) }

    }
}