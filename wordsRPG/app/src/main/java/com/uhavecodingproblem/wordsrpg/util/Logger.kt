package com.uhavecodingproblem.wordsrpg.util

import android.util.Log
import androidx.databinding.library.BuildConfig
import com.uhavecodingproblem.wordsrpg.application.Application

/**
 * wordsrpg
 * Class: Logger.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 *
 * Logger 클래스
 * 릴리즈 버전때  로그 유출 방지
 */
class Logger private constructor(){
    companion object{

        private val DEBUG = Application.DEBUG_AVAILABLE
        private const val TAG = "rucp" // 태그변경

        fun v(msg: String) = logger(Log.VERBOSE, msg)
        fun d(msg: String) = logger(Log.DEBUG, msg)
        fun i(msg: String) = logger(Log.INFO, msg)
        fun w(msg: String) = logger(Log.WARN, msg)
        fun e(msg: String) = logger(Log.ERROR, msg)


        private fun logger(priority: Int, msg: String) {

            //debug 가능한 상태일때는  log를 출력한다.
            //해당 클래스 이름  메소드 이름 파일이름 및  라인 넘버  출력하게 함.
            if (DEBUG) {
                val message =
                    "[${Thread.currentThread().stackTrace[4].fileName} => "+
                            "${Thread.currentThread().stackTrace[4].methodName}()] :: $msg" +
                            "(${Thread.currentThread().stackTrace[4].fileName}:${Thread.currentThread().stackTrace[4].lineNumber})"
                Log.println(priority, TAG, message)
            }
        }
    }

}