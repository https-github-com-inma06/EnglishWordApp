package com.uhavecodingproblem.wordsrpg.ui.fragment.battle

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.uhavecodingproblem.wordsrpg.R

class BattleStartDialogFragment : DialogFragment() {

    private var userName: String = ""
    private var userProfileImage: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_battle_start, container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userName = arguments?.getString("userName").toString()
        userProfileImage = arguments?.getString("imageUrl").toString()

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_fragment_battle_start, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)

        return builder.create()
    }

    companion object {
        fun newInstance(userName: String, imageUrl: String): BattleStartDialogFragment? {
            val args = Bundle()
            args.putString("userName", userName)
            args.putString("imageUrl", imageUrl)
            val fragment = BattleStartDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }


}