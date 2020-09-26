package com.uhavecodingproblem.wordsrpg.util

import android.content.Context
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import java.security.Permission

fun tedPermissionCheck(
    context: Context,
    rationalMessage: String,
    onPermissionGrantedAfterFunction: () -> Unit
) {
    val permission = object : PermissionListener {
        override fun onPermissionGranted() {//설정해놓은 위험권한 허용시
            onPermissionGrantedAfterFunction()
        }

        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            Toast.makeText(context, "권한이 거부 되었습니다.", Toast.LENGTH_SHORT).show()
            return//설정한 위험 권한 거부시

        }
    }

    try {
        TedPermission.with(context)
            .setPermissionListener(permission)
            .setRationaleMessage(rationalMessage)
            .setDeniedMessage("권한이 거부되었습니다. [앱 설정] -> [권한] 항목에서 이용해주세요")
            .setPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .check()
    } catch(e:Exception) {
      e.printStackTrace()
    }
}
