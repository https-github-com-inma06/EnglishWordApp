package com.uhavecodingproblem.wordsrpg.ui.fragment.battle

import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMainBattleBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility

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

class MainBattleFragment : BaseUtility.BaseFragment<FragmentMainBattleBinding>(R.layout.fragment_main_battle) {

    companion object {
        const val BATTLE_RESULT_WIN = 2001
        const val BATTLE_RESULT_LOSE = 2002
        const val BATTLE_RESULT_TIE = 2003
        const val BATTLE_RESULT_REQUEST = 2004
    }

    override fun FragmentMainBattleBinding.onCreateView() {
//        setRecyclerView()
//        binding.tvRandom.setOnClickListener {
//            Toast.makeText(context, "곧 준비중인 기능입니다.", Toast.LENGTH_SHORT).show()
//        }
    }

//    private fun setRecyclerView() {
//        /**
//         * Todo: 묵데이터, 서버 참고해서 모델 정해지는대로 심플하게 모델로만 묶어서 사용 예정
//         */
//        val userFriendList: MutableList<User> = mutableListOf()
//        val notificationList: MutableList<BattleNotification> = mutableListOf()
//
//        for(i in 0..4) {
//            val muckFriendUserData = User(
//                0,
//                "asdad@gmail.com",
//                "123123",
//                "재이",
//                "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",
//                null, "브론즈", 0, null, null
//            )
//            val muckBattleScore = 100 * (i + 1)
//            val notification = BattleNotification(i.toLong(), null, null)
//
//            if (i % 2 == 0) {
//                muckFriendUserData.userId = i.toLong()
//                muckFriendUserData.score = muckBattleScore.toLong()
//
//                Log.d("asdasda", i.toString())
//
//                notification.notificationType = BATTLE_RESULT_WIN
//                notification.date = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN).format(Date())
//            } else {
//
//                muckFriendUserData.userId = i.toLong()
//                muckFriendUserData.profileImage =
//                    "https://img.huffingtonpost.com/asset/5d814d8e3b00002b88d66359.jpeg?ops=scalefit_630_noupscale"
//                muckFriendUserData.userName = "아타"
//                muckFriendUserData.score = muckBattleScore.toLong()
//
//
//                Log.d("asdasda", i.toString())
//                notification.notificationType = BATTLE_RESULT_REQUEST
//                notification.date = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN).format(Date())
//            }
//
//            notificationList.add(notification)
//            userFriendList.add(muckFriendUserData)
//        }
//
//        val muckCurrentUserData = User(
//            99,
//            "loner@gmail.com",
//            "123123",
//            "로너",
//            "https://img.etoday.co.kr/pto_db/2020/09/20200915135347_1511046_1000_644.jpg",
//            userFriendList, "브론즈", 235, null,
//            notificationList
//        )
//        userFriendList.also{
//         muckCurrentUserData.friendList = it
//        }
//
//
//        binding.rvSimilarScoreUsers.apply {
//            layoutManager =
//                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//            adapter = MainBattleSimilarScoreUsersAdapter(muckCurrentUserData) {
//                Intent(requireContext(), BattleRankingActivity::class.java).also {
//                    it.putExtra("currentUser", muckCurrentUserData)
//                    startActivity(it)
//                }
//            }
//        }
//
//        binding.rvBattleNotificationList.apply {
//            layoutManager =
//                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            adapter = MainBattleNotificationListAdapter(muckCurrentUserData)
//        }
//
//
//    }
}
