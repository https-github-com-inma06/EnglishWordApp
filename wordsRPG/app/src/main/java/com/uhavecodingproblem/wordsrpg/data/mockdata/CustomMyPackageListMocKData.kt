package com.uhavecodingproblem.wordsrpg.data.mockdata

import com.uhavecodingproblem.wordsrpg.data.model.CustomPackageData

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
                c_id = 1.toString(),
                ownerName = "리안",
                packageName = "프로그래밍 단어장",
                profileImageUrl = "https://ifh.cc/g/Jn9ghK.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("프로그래밈")
            ),
            CustomPackageData(
                c_id = 2.toString(),
                ownerName = "아타나시님",
                packageName = "워킹데드1화 단어장",
                profileImageUrl = "https://ifh.cc/g/b6yryB.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("영어", "언어")
            ),
            CustomPackageData(
                c_id = 3.toString(),
                ownerName = "재이",
                packageName = "토익준비용 단어장",
                profileImageUrl = "https://ifh.cc/g/PeB4iS.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("1")
            )

        )

}