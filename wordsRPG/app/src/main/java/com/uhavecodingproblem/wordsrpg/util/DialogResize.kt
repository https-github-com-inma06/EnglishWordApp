package com.uhavecodingproblem.wordsrpg.util

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager

/**
 * wordsrpg
 * Class: DialogResize
 * Created by pyg10.
 * Created On 2020-12-05.
 * Description:
 */


fun Context.dialogResize(dialog: Dialog, width: Float, height: Float) {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

    if (Build.VERSION.SDK_INT < 30) {

        val display = windowManager.defaultDisplay
        val size = Point()

        display.getSize(size)

        val window = dialog.window

        val x = (size.x * width).toInt()
        val y = (size.y * height).toInt()
        window?.setLayout(x, y)

    } else {

        val rect = windowManager.currentWindowMetrics.bounds

        val window = dialog.window

        val x = (rect.width() * width).toInt()
        val y = (rect.height() * height).toInt()

        window?.setLayout(x, y)
    }
}