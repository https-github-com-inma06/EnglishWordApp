package com.uhavecodingproblem.wordsrpg.api

import com.uhavecodingproblem.wordsrpg.data.PackageInformation
import com.uhavecodingproblem.wordsrpg.data.StageInformation
import retrofit2.Call
import retrofit2.http.*

interface ServerApi {
    //REST API 사용하는곳


    //단어 API
    @GET("Test")
    fun requestWord(@Query("userId") userId: String) : Call<List<PackageInformation>>

    @PATCH("Test")
    @FormUrlEncoded
    fun uploadWord(@Field("userId") userId: String,
                   @Field("package") packageInformation: PackageInformation) : Call<Boolean>

    companion object {
        fun requestWordData(userId: String): Call<List<PackageInformation>> {
            return ClientServer().create(ServerApi::class.java).requestWord(userId)
        }

        fun uploadWord(userId: String, packageInformation: PackageInformation): Call<Boolean> {
            return ClientServer().create(ServerApi::class.java).uploadWord(userId, packageInformation)
        }
    }



}   