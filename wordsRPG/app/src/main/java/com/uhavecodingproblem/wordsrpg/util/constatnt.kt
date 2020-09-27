package com.uhavecodingproblem.wordsrpg.util

/**
 * wordsrpg
 * Class: constatnt.
 * Created by leedonghun.
 * Created On 2020-09-26.
 * Description:  전역상수 모음
 *
 */

//커스텀 패키지  만들때  REQUEST 코드
const val MAKE_CUSTOM_PACKAGE_REQUEST_CODE = 101


//커스텀 패키지 별  리사이클러뷰 필터 타입 나눔
const val ORIGINAL_PACKAGE_TYPE = 1001// 기본적으로 뿌려주는 형태
const val SEARCH_PACKAGE_TYPE = 1002//타이틀 검색으로 필터링 -> 태그랑  제목으로 나뉘어야됨.
const val MY_SUBSCRIBE_FILTER_TYPE = 1003//내가 구독한 유저의 패키지로 필터링
const val MY_HEART_FILTER_TYPE = 1004//내가 하트 표시한 유저의 패키지로 필터링