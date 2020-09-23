package com.uhavecodingproblem.wordsrpg.ui.fragment

import android.content.Intent
import android.view.View
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMainMyRoomBinding
import com.uhavecodingproblem.wordsrpg.resource_path.ResourcePath.RESOURCE_PATH
import com.uhavecodingproblem.wordsrpg.ui.activity.intro.LoginActivity
import com.uhavecodingproblem.wordsrpg.ui.activity.itemStoreActivity.ItemStoreActivity
import com.uhavecodingproblem.wordsrpg.ui.adapter.MyRoomRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment


/**
 * wordsrpg
 * Class: MainMyRoomFragment.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 * 마이룸 프레그먼트입니다.
 */
class MainMyRoomFragment : BaseFragment<FragmentMainMyRoomBinding>(R.layout.fragment_main_my_room) {

    /**
     * TODO: 로그인 방법 구체화 시키면 아래 구문은 삭제할 예정임.
     */
    companion object {
        var loginCheck = false
    }

    override fun FragmentMainMyRoomBinding.onCreateView() {
        Logger.v("실행")

        //true 라면 유저모드, false 라면 비 로그인 모드 UI설정
        setLoginCheckUIControl(loginCheck)
        setAdapter()
        setButtonClickListeners()

    }

    private fun FragmentMainMyRoomBinding.setButtonClickListeners() {
        btnGoToTheItemStore.setOnClickListener {
            startActivity(Intent(requireContext(), ItemStoreActivity::class.java))
        }

        btnGoToTheLogin.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
    }

    private fun FragmentMainMyRoomBinding.setAdapter() {
        val imageList = MutableList(6) { "" }
        for (i in imageList.indices) {
            imageList[i] = RESOURCE_PATH + resources.getIdentifier(
                "myroom_gameitem_sample${1 + i}",
                "drawable", requireActivity().packageName
            ).toString()
        }

        rvMyRoom.adapter = MyRoomRecyclerViewAdapter(imageList)
    }

    private fun FragmentMainMyRoomBinding.setLoginCheckUIControl(anonymousUserMode: Boolean) {
        if (!anonymousUserMode) {
            layoutAnonymousUser.visibility = View.VISIBLE
            rootViewGroupMyRoom.getChildAt(1).visibility = View.VISIBLE
            for (i in 2 until rootViewGroupMyRoom.childCount) {
                rootViewGroupMyRoom.getChildAt(i).visibility = View.GONE
            }
            return
        }

        layoutAnonymousUser.visibility = View.GONE
        rootViewGroupMyRoom.getChildAt(1).visibility = View.GONE
        for (i in 2 until rootViewGroupMyRoom.childCount) {
            rootViewGroupMyRoom.getChildAt(i).visibility = View.VISIBLE
        }
    }


}