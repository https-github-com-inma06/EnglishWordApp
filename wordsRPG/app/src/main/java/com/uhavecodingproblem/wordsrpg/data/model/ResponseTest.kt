package com.uhavecodingproblem.wordsrpg.data.model

/**
 * wordsrpg
 * Class: ResponseTest
 * Created by pyg10.
 * Created On 2021-03-23.
 * Description:
 */
data class ResponseTest(val success: Boolean, val total_test : String, val stage_id : String, val learning_id: String, val test: MutableList<Test>)