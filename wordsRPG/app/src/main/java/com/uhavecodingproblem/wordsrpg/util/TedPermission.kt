package com.uhavecodingproblem.wordsrpg.util

import android.content.Context
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import java.util.jar.Manifest

fun Context.tedPermission(
    grantGranted: () -> Unit,
    denied: () -> Unit
) {

    val permissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            grantGranted()
        }

        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            denied()
        }

    }
    TedPermission.with(this)
        .setPermissionListener(permissionListener)
        .setRationaleTitle("피땀영어 앱 권한요청")
        .setRationaleMessage("피땀영어 앱을 사용하시기 위해서는 권한이 필요합니다.")
        .setDeniedTitle("권한요청 재요구")
        .setDeniedMessage("재 권한요청을 원할 시 앱을 재접속하시거나 앱 설정->권한을 체크해주세요 ")
        .setPermissions(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
        .check()

}