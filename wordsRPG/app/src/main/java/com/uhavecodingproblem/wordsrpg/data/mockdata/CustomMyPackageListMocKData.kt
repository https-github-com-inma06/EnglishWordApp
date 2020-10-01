package com.uhavecodingproblem.wordsrpg.data.mockdata

import com.uhavecodingproblem.wordsrpg.data.CustomPackageData

/**
 * wordsrpg
 * Class: CustomMyPackageListMocKData.
 * Created by leedonghun.
 * Created On 2020-09-26.
 * Description:  내 커스텀 패키지  리스트
 */
object CustomMyPackageListMocKData {


    val list= mockCustomPackageList()
    //val list= ArrayList<CustomPackageData>()//아무것도 없게


    private fun mockCustomPackageList() =
        mutableListOf(
            CustomPackageData(
                uid = 1,
                ownerUid = 3,
                packageName = "프로그래밍 단어장",
                profileImageUrl = "https://ifh.cc/g/Jn9ghK.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashList = mutableListOf("프로그래밈")
            ),
            CustomPackageData(
                uid = 2,
                ownerUid = 3,
                packageName = "워킹데드1화 단어장",
                profileImageUrl = "https://ifh.cc/g/b6yryB.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashList = mutableListOf("영어", "언어")
            ),
            CustomPackageData(
                uid = 3,
                ownerUid = 3,
                packageName = "토익준비용 단어장",
                profileImageUrl = "https://ifh.cc/g/PeB4iS.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashList = mutableListOf("1")
            )

        )

}