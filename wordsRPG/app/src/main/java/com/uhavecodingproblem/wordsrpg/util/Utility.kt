package com.uhavecodingproblem.wordsrpg.util

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Parcel
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission

/**
 * wordsrpg
 * Class: Utility
 * Created by pyg10.
 * Created On 2021-03-21.
 * Description:
 */




///////////////////// Ted_Permission //////////////////

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

/////////////////// KeyBoardExt(Keyboard Hide) //////////////////////

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


///////////////// Dialog Resize ////////////////////

fun Context.dialogResize(dialogFragment: DialogFragment, width: Float, height: Float) {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

    if (Build.VERSION.SDK_INT < 30) {

        val display = windowManager.defaultDisplay
        val size = Point()

        display.getSize(size)

        val window = dialogFragment.dialog?.window

        val x = (size.x * width).toInt()
        val y = (size.y * height).toInt()
        window?.setLayout(x, y)

    } else {

        val rect = windowManager.currentWindowMetrics.bounds

        val window = dialogFragment.dialog?.window

        val x = (rect.width() * width).toInt()
        val y = (rect.height() * height).toInt()

        window?.setLayout(x, y)
    }
}

/////////////////////////// constatnt 상수영역 /////////////////////

//커스텀 패키지  만들때  REQUEST 코드
const val MAKE_CUSTOM_PACKAGE_REQUEST_CODE = 101


//커스텀 패키지 검색시  필터 종류
const val SEARCH_PACKAGE_TITLE = 1000
const val SEARCH_PACKAGE_TAG = 1001

//일반 커스텀 패키지 리스트 필터 종류
const val ORIGINAL_PACKAGE_TYPE = 1002// 기본적으로 뿌려주는 형태(최근 순으로)
const val MY_SUBSCRIBE_FILTER_TYPE = 1003//내가 구독한 유저의 패키지로 필터링
const val MY_HEART_FILTER_TYPE = 1004//내가 하트 표시한 유저의 패키지로 필터링



//내패키지랑  전체 패키지 구별 용 상수
const val MY_CUSTOM_PACKAGE = 2001
const val ENTIRE_CUSTOM_PACKAGE = 2002


//스테이지 상태구분
const val STAGE_NONE = 0 // 학습하지도, 테스트통과하지도 않은 상태
const val STAGE_STUDYING = 1 // 학습중인상태( 학습만 한상태, 학습하다가 중간에 나간상태 )
const val STAGE_TEST_FAIL = 2 // 테스트 실패
const val STAGE_TEST_CLEAR = 3 // 테스트 통과


//기본 패키지 주소
const val BASIC_PACKAGE = "http://3.34.162.73/api/"

//firebase storage
const val FIREBASE_STORAGE_REFERENCE_URL = "gs://wordrpg-618f7.appspot.com"
const val FIREBASE_STORAGE_IMAGE = "images/"
const val FIREBASE_STORAGE_VIDEO = "videos/"
