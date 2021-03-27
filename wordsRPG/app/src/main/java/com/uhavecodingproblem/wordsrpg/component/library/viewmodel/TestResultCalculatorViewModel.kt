package com.uhavecodingproblem.wordsrpg.component.library.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.uhavecodingproblem.wordsrpg.data.model.CorrectAnswer
import com.uhavecodingproblem.wordsrpg.data.model.Learning
import com.uhavecodingproblem.wordsrpg.data.model.Test
import com.uhavecodingproblem.wordsrpg.data.model.TestTotalScore
import com.uhavecodingproblem.wordsrpg.util.Logger
import java.util.*

/**
 * wordsrpg
 * Class: TestResultCalculatorViewModel
 * Created by pyg10.
 * Created On 2021-03-25.
 * Description:
 */
class TestResultCalculatorViewModel: ViewModel() {

    fun calculatorScore(answer : MutableList<Test>, correctAnswer : MutableList<CorrectAnswer>): TestTotalScore{

        var correct = 0
        var wrong = 0

        answer.forEach { Logger.d("${it.question.question_mean} ${it.question.question_word}") }
        correctAnswer.forEach { Logger.d("${it.type} ${it.correct_answer}") }

        for (i in correctAnswer.indices){
            if (correctAnswer[i].type % 2 == 0){
                if (answer[i].question.question_mean == correctAnswer[i].correct_answer)
                    correct++
                else
                    wrong++
            }else if (correctAnswer[i].type % 2 == 1){
                if (answer[i].question.question_word == correctAnswer[i].correct_answer)
                    correct++
                else
                    wrong++
            }
        }
        if (correct + wrong != answer.size){
            wrong += answer.size - (correct + wrong)
        }

        return TestTotalScore(answer.size, correct, wrong)
    }

    fun updateResult(totalScore: Int, correct: Int, learning: Learning){

        val passScore = totalScore * 0.7

        FirebaseDatabase.getInstance().reference.child("Learning").orderByChild("l_id").equalTo(learning.l_id)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (childSnapshot in snapshot.children) {
                        childSnapshot?.key?.let {
                            val param = hashMapOf<String, String>()
                            param["l_id"] = learning.l_id
                            param["s_id"] = learning.s_id
                            param["u_id"] = learning.u_id
                            param["p_id"] = learning.p_id
                            param["total_page"] = learning.total_page
                            param["current_page"] = learning.current_page
                            if (correct * 10 >= passScore.toInt()) {
                                param["stage_status"] = "3"
                            } else {
                                param["stage_status"] = "2"
                            }

                            FirebaseDatabase.getInstance().reference.child("Learning").child(it).setValue(param).addOnSuccessListener {
                                Logger.d("ResultUpdateSuccess")
                            }.addOnFailureListener {exception->
                                Logger.d("ResultUpdateFailure $exception")
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })


    }



}