package com.uhavecodingproblem.wordsrpg.ext

import android.content.Context
import android.content.Intent

fun Context.startActivity(targetActivity: Class<out Any>){
    val i = Intent(this, targetActivity)
    startActivity(i)
}