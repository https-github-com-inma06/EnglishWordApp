package com.uhavecodingproblem.wordsrpg.util

import android.content.Context
import android.widget.Toast
import com.uhavecodingproblem.wordsrpg.application.Application

fun Context.toastShow(stringMessage: String)=
    Toast.makeText(this, stringMessage, Toast.LENGTH_SHORT).show()

