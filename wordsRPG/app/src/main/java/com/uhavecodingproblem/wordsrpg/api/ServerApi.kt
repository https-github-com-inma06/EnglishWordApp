package com.uhavecodingproblem.wordsrpg.api

import com.uhavecodingproblem.wordsrpg.data.PackageInformation
import com.uhavecodingproblem.wordsrpg.data.PackageRead
import com.uhavecodingproblem.wordsrpg.data.StageInformation
import retrofit2.Call
import retrofit2.http.*

interface ServerApi {
    //REST API 사용하는곳


    //단어 API
    @GET("package_read.php")
    fun requestWord(@Query("package_name") package_name: String) : Call<List<PackageInformation>>

    // 패키지조회 1회차
    @GET("package_read.php")
    fun requestBasicPackage(@Query("package_name") package_name: String) : Call<PackageRead>

    companion object {
        fun requestWordData(package_name: String): Call<List<PackageInformation>> {
            return ClientServer().create(ServerApi::class.java).requestWord(package_name)
        }

        fun requestBasicPackage(package_name: String) : Call<PackageRead>{
            return ClientServer().create(ServerApi::class.java).requestBasicPackage(package_name)
        }

    }



}