package com.uhavecodingproblem.wordsrpg.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.google.gson.JsonElement
import com.uhavecodingproblem.wordsrpg.db.server_db.dataholder.User
import com.uhavecodingproblem.wordsrpg.repository.AuthRepository
import kotlinx.coroutines.launch


class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()


    fun userRegister(
        user: User,
        onSucceed: (JsonElement) -> Unit?, onFailure: () -> Unit?
    ) {
        viewModelScope.launch {
            try {
                Log.d("register", "회원 가입 성공 ")
                authRepository.userRegister(
                    user.userId,
                    user.email,
                    user.userPassword,
                    user.createdAt,
                    user.userName,
                    user.userNick,
                    user.emailVerifiedAt,
                    user.rememberToken,
                    user.updatedAt
                ).let(onSucceed)

            } catch (e: Exception) {
                onFailure()
                Log.d("register", "회원 가입 실패$e ")
            }
        }
    }

    fun userLogin(
        userId: String,
        userPassword: String,
        onSucceed: (JsonElement) -> Unit?, onFailure: () -> Unit?
    ) {
        viewModelScope.launch {
            try {
                authRepository.userLogin(userId, userPassword).let(onSucceed)
            } catch (e: Exception) {
                onFailure()
            }

        }

    }



}