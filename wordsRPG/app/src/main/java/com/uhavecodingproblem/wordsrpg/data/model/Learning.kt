package com.uhavecodingproblem.wordsrpg.data.model

//학습 테이블
data class Learning(
    //학습 고유값
    val l_id:String = "",
    //유저 고유값
    val u_id:String = "",
    //패키지 고유값
    val p_id:String = "",
    //스테이지 고유값
    val s_id:String = "",
    //현재 페이지(0 이라면 학습중이지 않은상태)
    val current_page: String = "",
    //스테이지 총 페이지수
    val total_page:String="",
    //스테이지의 상태
    val stage_status:String=""
)
