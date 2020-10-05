package com.uhavecodingproblem.wordsrpg.ui.fragment.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uhavecodingproblem.wordsrpg.data.model.BattlePlayersItem
import com.uhavecodingproblem.wordsrpg.data.model.BattleSystemData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainBattleViewModel : ViewModel() {
    private val _sendToServer = MutableLiveData<Boolean>()
    val sendToServer: LiveData<Boolean>
        get() = _sendToServer

    private val _opponentUser = MutableLiveData<BattlePlayersItem>()
    val opponentUser: LiveData<BattlePlayersItem>
        get() = _opponentUser

    private val _getSystemData = MutableLiveData<BattleSystemData>()
    val getSystemData: LiveData<BattleSystemData>
        get() = _getSystemData


    fun sendToToken(send: Boolean, afterLogic: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            // 토큰값 보내는 로직 수행
            afterLogic()
        }
    }


    fun getOpponentUserUserData() {
        //잠시 패러미터는 생략함
        //유저 토큰을 파라미터로 받아 api 호출해서 가져옴
        _opponentUser.postValue(BattlePlayersItem("나쁜놈 사진", "나쁜놈", "만렙"))
    }

    fun getSystemData() {
        //잠시 패러미터는 생략함
        //유저 토큰을 파라미터로 받아 api 호출해서 가져옴
        _getSystemData.postValue(BattleSystemData(1, 1, 1, "호우 난이도"))
    }
}