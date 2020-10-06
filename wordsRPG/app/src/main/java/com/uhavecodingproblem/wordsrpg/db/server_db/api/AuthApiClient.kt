package com.uhavecodingproblem.wordsrpg.db.server_db.api

import com.google.gson.JsonElement
import com.uhavecodingproblem.wordsrpg.db.server_db.dataholder.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object AuthApiClient {

    private const val BaseUri = "http://52.78.122.62/api/"
    val api = Retrofit.Builder()
        .baseUrl(BaseUri)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthServerApi::class.java)

}

interface AuthServerApi {

    /**
     * 가입 POST ( 필수입력 파라미터 user_id, user_password, email )
     *    수정 PUT
     *    검색 GET
     *    탈퇴 DELETE
     **/

    @FormUrlEncoded
    @POST("users")
    suspend fun userRegister(
        @Field("user_id") userId: String,
        @Field("email") email: String,
        @Field("user_password") userPassword: String,
        @Field("created_at") created_at: Int,
        @Field("user_name") user_name: String?,
        @Field("user_nick") user_nick: String?,
        @Field("email_verified_at") email_verified_at: String?,
        @Field("remember_token") remember_token: String?,
        @Field("updated_at") updated_at: String?
    ): JsonElement

    @FormUrlEncoded
    @POST("logs")
    suspend fun userLogin(
        @Field("user_id") userId: String,
        @Field("user_password") userPassword: String
    ): JsonElement

}