package com.uhavecodingproblem.wordsrpg.application
import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import com.kakao.sdk.common.KakaoSdk

/**
 * wordsrpg
 * Class: Application.
 * Created by leedonghun.
 * Created On 2020-09-17.
 * Description:
 */
class Application:Application() {
    companion object{

        //debug 가능 여부 -> default 값은  false
        var DEBUG_AVAILABLE:Boolean=false

    }

    override fun onCreate() {
        super.onCreate()

        //debug 가능 여부 넣어줌.
        DEBUG_AVAILABLE=isDebuggable(this)

        KakaoSdk.init(this, "{NATIVE_APP_KEY}")
    }

    //debug 가능 여부를  체크해준다. (logger 안보이게 할려고)
    //release 버전에서는  false 로 체크된다.
    private fun isDebuggable(context: Context): Boolean {
        return context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    }
}