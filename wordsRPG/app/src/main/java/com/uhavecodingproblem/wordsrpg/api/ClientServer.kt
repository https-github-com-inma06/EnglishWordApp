package com.uhavecodingproblem.wordsrpg.api

import com.uhavecodingproblem.wordsrpg.BuildConfig
import com.uhavecodingproblem.wordsrpg.util.BASIC_PACKAGE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ClientServer {
    // 레트로핏

    private fun createOkHttpClient() : OkHttpClient{
        val interceptor = HttpLoggingInterceptor()

        if(BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build()
    }

    companion object{

        fun getWordRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASIC_PACKAGE).addConverterFactory(GsonConverterFactory.create())
                .client(ClientServer().createOkHttpClient()).build()
        }

    }

    fun <T> create(service: Class<T>): T{
        return getWordRetrofit().create(service)
    }

}