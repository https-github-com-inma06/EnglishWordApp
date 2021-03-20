package com.uhavecodingproblem.wordsrpg.util

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceUtil {

    private lateinit var sharedPreferences: SharedPreferences

    fun Context.sharedInit(){
        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
    }

    var userIdx: String?
        get() = sharedPreferences.getString("userIdx", null)
        set(value) = sharedPreferences.edit().putString("userIdx", value).apply()




}