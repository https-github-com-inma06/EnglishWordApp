package com.uhavecodingproblem.wordsrpg.data.mockdata

import com.uhavecodingproblem.wordsrpg.data.CustomPackageData

/**
 * wordsrpg
 * Class: CustomPackageListMockData.
 * Created by leedonghun.
 * Created On 2020-09-25.
 * Description:
 *
 * 임시적으로 테스트를 용 전체 커스텀 패키지 리스트
 */
object CustomPackageListMockData {

    val list= mockCustomPackageList()


    private fun mockCustomPackageList() =
        mutableListOf(
            CustomPackageData(
                uid = 1,
                ownerName = "로너",
                packageName = "프로그래밍 단어장",
                profileImageUrl = "https://ifh.cc/g/Jn9ghK.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("프로그래밈")
            ),
            CustomPackageData(
                uid = 2,
                ownerName = "아타나시오",
                packageName = "워킹데드1화 단어장",
                profileImageUrl = "https://ifh.cc/g/b6yryB.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("영어", "언어")
            ),
            CustomPackageData(
                uid = 3,
                ownerName = "리안",
                packageName = "토익준비용 단어장",
                profileImageUrl = "https://ifh.cc/g/PeB4iS.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("1")
            ),
            CustomPackageData(
                uid = 4,
                ownerName = "초보",
                packageName = "오픽 단어장",
                profileImageUrl = "https://ifh.cc/g/5QbCWC.png",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("ㄴㅇㄹ")
            ),
            CustomPackageData(
                uid = 5,
                ownerName = "로너",
                packageName = "마케팅 단어장",
                profileImageUrl = "https://ifh.cc/g/1kQm6l.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("ㅇㅇ")
            ),
            CustomPackageData(
                uid = 6,
                ownerName = "재이",
                packageName = "여행준비 단어장",
                profileImageUrl = "https://ifh.cc/g/Jn9ghK.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("ㄴㅇ")
            ),
            CustomPackageData(
                uid = 7,
                ownerName = "초보",
                packageName = "자동차 용어 단어장",
                profileImageUrl = "https://ifh.cc/g/0q4a2h.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("ㄹㄹㄹ")
            ),
            CustomPackageData(
                uid = 8,
                ownerName = "홍님",
                packageName = "이솝우화 단어장",
                profileImageUrl = "https://ifh.cc/g/0q4a2h.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("ㅍㅊㅍ")
            ),
            CustomPackageData(
                uid = 9,
                ownerName = "초보",
                packageName = "영어유치원 단어장",
                profileImageUrl = "https://ifh.cc/g/0q4a2h.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("ㅁㄱㄷㄱ")
            ),
            CustomPackageData(
                uid = 10,
                ownerName = "아타나시오",
                packageName = "컴퓨터용품 단어장",
                profileImageUrl = "https://ifh.cc/g/KfsEuu.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("ㅍㅊㅍㅊ")
            ),
            CustomPackageData(
                uid = 11,
                ownerName = "리안",
                packageName = "우리 신체 단어장",
                profileImageUrl = "https://ifh.cc/g/Wa5o7R.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("ㅋㅊㅌㅊ")
            ),
            CustomPackageData(
                uid = 12,
                ownerName = "초보",
                packageName = "프로그래밍2 단어장",
                profileImageUrl = "https://ifh.cc/g/Jn9ghK.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("ㅂㄷ123")
            ),
            CustomPackageData(
                uid = 13,
                ownerName = "재이",
                packageName = "프로그래밍3 단어장",
                profileImageUrl = "https://ifh.cc/g/Jn9ghK.jpg",
                likeCount = 3,
                subscribeCount = 12,
                hashTagList = mutableListOf("ㅁㄴㅇㅁㄴㅇ")
            )
        )
}