package com.uhavecodingproblem.wordsrpg.api

import com.uhavecodingproblem.wordsrpg.data.mockdata.PackageInformation
import com.uhavecodingproblem.wordsrpg.data.model.Package
import com.uhavecodingproblem.wordsrpg.data.model.ResponseBasicPackage
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface ServerApi {
    //REST API 사용하는곳


    //단어 API
    @GET("package_read.php")
    fun requestWord(@Query("package_name") package_name: String) : Call<List<PackageInformation>>

    // 패키지조회 1회차
    @GET("package_read.php")
    fun requestBasicPackage(@Query("package_name") package_name: String) : Call<Package>

    @POST("basic_package_test.php")
    fun requestTest(
        @Query("user_id") user_id : String
    ): Observable<ResponseBasicPackage.ResponseBasic>

    companion object {
        fun requestWordData(package_name: String): Call<List<PackageInformation>> {
            return ClientServer().create(ServerApi::class.java).requestWord(package_name)
        }

        fun requestBasicPackage(package_name: String) : Call<Package>{
            return ClientServer().create(ServerApi::class.java).requestBasicPackage(package_name)
        }

        fun requestTest(param: String) : Observable<ResponseBasicPackage.ResponseBasic>{
            return ClientServer().create(ServerApi::class.java).requestTest(param)
        }

    }



}