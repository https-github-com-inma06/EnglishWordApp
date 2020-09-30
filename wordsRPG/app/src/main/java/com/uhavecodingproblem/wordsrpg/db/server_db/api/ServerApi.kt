package com.uhavecodingproblem.wordsrpg.db.server_db.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object ApiClient {

    private const val BaseUri = "http://52.78.122.62"
    val api = Retrofit.Builder()
        .baseUrl(BaseUri)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ServerApi::class.java)

}

interface ServerApi {

    /**
     * 가입 POST ( 필수입력 파라미터 user_id, user_password, email )
     *    수정 PUT
     *    검색 GET
     *    탈퇴 DELETE
     **/


    @GET("/api/users/")
    suspend fun userDataSearch(
        @Query("user") userId: String,
        @Query("user_password") userPassword: String,
        @Query("email") email: String
    )


}