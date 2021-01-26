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


//커스텀 패키지 검색시  필터 종류
const val SEARCH_PACKAGE_TITLE = 1000
const val SEARCH_PACKAGE_TAG = 1001

//일반 커스텀 패키지 리스트 필터 종류
const val ORIGINAL_PACKAGE_TYPE = 1002// 기본적으로 뿌려주는 형태(최근 순으로)
const val MY_SUBSCRIBE_FILTER_TYPE = 1003//내가 구독한 유저의 패키지로 필터링
const val MY_HEART_FILTER_TYPE = 1004//내가 하트 표시한 유저의 패키지로 필터링



//내패키지랑  전체 패키지 구별 용 상수
const val MY_CUSTOM_PACKAGE = 2001
const val ENTIRE_CUSTOM_PACKAGE = 2002


//스테이지 상태구분
const val STAGE_NONE = 0 // 학습하지도, 테스트통과하지도 않은 상태
const val STAGE_STUDYING = 1 // 학습중인상태( 학습만 한상태, 학습하다가 중간에 나간상태 )
const val STAGE_TEST_FAIL = 2 // 테스트 실패
const val STAGE_TEST_CLEAR = 3 // 테스트 통과


//기본 영단어 주소
const val BASIC_WORD = "http://www.naver.com"