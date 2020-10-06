package com.uhavecodingproblem.wordsrpg.repository

import com.google.gson.JsonElement
import com.uhavecodingproblem.wordsrpg.db.server_db.api.AuthApiClient
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthDataSource {
    suspend fun userRegister(
        userId: String,
        email: String,
        userPassword: String,
        createdAt: Int,
        userName: String?,
        userNick: String?,
        emailVerifiedAt: String?,
        rememberToken: String?,
        updatedAt: String?
    ): JsonElement


    suspend fun userLogin(
        userId: String,
        userPassword: String
    ): JsonElement
}

class AuthRepository : AuthDataSource {
    override suspend fun userRegister(
        userId: String,
        email: String,
        userPassword: String,
        createdAt: Int,
        userName: String?,
        userNick: String?,
        emailVerifiedAt: String?,
        rememberToken: String?,
        updatedAt: String?
    ): JsonElement {
        return AuthApiClient.api.userRegister(
            userId,
            email,
            userPassword,
            createdAt,
            userName,
            userNick,
            emailVerifiedAt,
            rememberToken,
            updatedAt
        )
    }

    override suspend fun userLogin(
        userId: String,
        userPassword: String
    ) = AuthApiClient.api.userLogin(userId, userPassword)

}