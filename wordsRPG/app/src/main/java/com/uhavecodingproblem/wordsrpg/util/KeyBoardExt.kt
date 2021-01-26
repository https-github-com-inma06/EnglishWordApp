package com.uhavecodingproblem.wordsrpg.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

/**
 * wordsrpg
 * Class: keyboardutil.
 * Created by leedonghun.
 * Created On 2020-10-28.
 * Description:
 *
 * 키보드 hide()를 진행한다.
 */

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

//프래그먼트 확장용
fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

//엑티비티 확장용
fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}
