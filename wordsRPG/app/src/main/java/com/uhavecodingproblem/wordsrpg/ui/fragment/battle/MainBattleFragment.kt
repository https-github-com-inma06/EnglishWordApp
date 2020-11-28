package com.uhavecodingproblem.wordsrpg.ui.fragment.battle

import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.battle.MainBattleNotificationListAdapter
import com.uhavecodingproblem.wordsrpg.component.battle.MainBattleSimilarScoreUsersAdapter
import com.uhavecodingproblem.wordsrpg.data.model.User
import com.uhavecodingproblem.wordsrpg.data.model.UserBattleData
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMainBattleBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.battle.BattleRankingActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseFragment
import java.text.SimpleDateFormat
import java.util.*


/**
 * wordsrpg
 * Class: MainBattleFragment.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 * 배틀 프레그먼트입니다.
 *
 * DataBindingSetting by Loner
 *  Created On 2020-09-17.
 */

class MainBattleFragment : BaseFragment<FragmentMainBattleBinding>(R.layout.fragment_main_battle) {

    override fun FragmentMainBattleBinding.onCreateView() {
        setRecyclerView()
        binding.tvRandom.setOnClickListener {
            Toast.makeText(context, "곧 준비중인 기능입니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setRecyclerView() {
        /**
         * Todo: 묵데이터, 서버 참고해서 모델 정해지는대로 심플하게 모델로만 묶어서 사용 예정
         */

        val muckUserData = User(
            0,
            "asdad@gmail.com",
            "123123",
            "로너",
            "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",
            UserBattleData(0,"브론즈",235),null
        )
        val muckImageList = mutableListOf<String>()
        val muckNameList = mutableListOf<String>()
        val muckBattleDescribeList = mutableListOf<String>()
        val muckDateList = mutableListOf<String>()
        val muckAlarmModeList = mutableListOf<Boolean>()

        repeat(5) {
            muckDateList.add(SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN).format(Date()))

            if (it % 2 == 0) {
                muckImageList.add("https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg")
                muckNameList.add("로너")
                muckBattleDescribeList.add("님이 배틀을 신청하셨습니다")
                muckAlarmModeList.add(true)
            } else {
                muckImageList.add("https://img.huffingtonpost.com/asset/5d814d8e3b00002b88d66359.jpeg?ops=scalefit_630_noupscale")
                muckNameList.add("재이")
                muckBattleDescribeList.add("님과 배틀 결과를 확인해보세요")
                muckAlarmModeList.add(false)
            }

        }

        binding.apply {
            rvSimilarScoreUsers.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = MainBattleSimilarScoreUsersAdapter(muckImageList, muckNameList){
                        startActivity(Intent(requireContext(), BattleRankingActivity::class.java))
                }
            }

            rvBattleNotificationList.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = MainBattleNotificationListAdapter(
                    imageList = muckImageList,
                    userNameList = muckNameList,
                    battleAlarmDescribeList = muckBattleDescribeList,
                    battleDateList = muckDateList,
                    alarmModeList = muckAlarmModeList
                )

            }
        }

    }

}







