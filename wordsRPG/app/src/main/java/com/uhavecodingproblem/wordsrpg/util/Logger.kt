package com.uhavecodingproblem.wordsrpg.util

import android.util.Log
import com.uhavecodingproblem.wordsrpg.BuildConfig

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


        private  val DEBUG= BuildConfig.DEBUG
        private const val TAG = "english_rpg_game"

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


    //TODO 2020-09-16 아래 부분의 경우  위  BuildConfig.DEBUG 보다 좀더 확실하게  release 버전에서 사라짐.
    //TODO: 앱  시작시마다  체크를 하기 위해서  APPLICATION 클래스에  적용 해야됨.  ->  현재 APPLICATION 클래스 어디다 둘지 몰라서 아래 처럼 처리.

    //private val DEBUG = GlobalClass.DEBUG_AVAILABLE


    //TODO://아래는 APPLICATION CLASS에  적용될  코드
    //debug 가능 여부 넣어줌.
    //DEBUG_AVAILABLE=isDebuggable(this)

    //debug 가능 여부를  체크해준다. (logger 안보이게 할려고)
    //release 버전에서는  false 로 체크된다.
    //fun isDebuggable(context: Context): Boolean {
    //   return context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    //}

}