package com.uhavecodingproblem.wordsrpg.ui.fragment.myroom

import android.content.Intent
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.data.model.User
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.FragmentMainMyRoomBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.LoginActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import com.uhavecodingproblem.wordsrpg.util.SharedPreferenceUtil


/**
 * wordsrpg
 * Class: MainMyRoomFragment.
 * Created by Atanasio.
 * Created On 2021-03-06.
 * Description:
 * 마이룸 프레그먼트입니다.
 */
class MainMyRoomFragment :
    BaseUtility.BaseFragment<FragmentMainMyRoomBinding>(R.layout.fragment_main_my_room) {
    override fun FragmentMainMyRoomBinding.onCreateView() {
        Logger.d("실행")

        btnGoToTheLogin.setOnClickListener { startActivity(Intent(requireContext(),
        LoginActivity::class.java)) }

        button2.setOnClickListener {
            FirebaseDatabase.getInstance().reference.child("User").addListenerForSingleValueEvent(object :
            ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(snap in snapshot.children){
                         snap.getValue(User::class.java).also {
                             if(it?.u == SharedPreferenceUtil.userIdx)
                                 Log.d("UserData",it.toString())
                         }

                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }
    }
}