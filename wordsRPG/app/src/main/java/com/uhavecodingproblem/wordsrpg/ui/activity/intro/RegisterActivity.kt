package com.uhavecodingproblem.wordsrpg.ui.activity.intro

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityRegisterBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.MainActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import com.uhavecodingproblem.wordsrpg.ui.fragment.MainMyRoomFragment
import com.uhavecodingproblem.wordsrpg.util.imageUrlReSize
import com.uhavecodingproblem.wordsrpg.util.tedPermissionCheck
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {
    companion object {
        const val REQUEST_IMAGE_CODE = 7852
    }

    override fun ActivityRegisterBinding.onCreate() {

    }

    fun btnRegisterComplete(view: View) {
        binding.apply {

//            val date = (SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(Date())).toInt()
//            val userBirthDayConverter =
//                tvUserbirthDay.text.toString().replace("[^0-9]".toRegex(), "").toInt()
//

            //하나라도 text 가 비었을 때 return
            if (etCrateId.text.toString().isEmpty() || etCreatePassword.text.toString().isEmpty() ||
                etNickName.text.toString().isEmpty() || etPasswordDoubleCheck.text.toString()
                    .isEmpty() ||
                etPhoneNumber.text.toString().isEmpty() || tvUserbirthDay.text.isEmpty()
            ) {
//                ToastUtil.toastShow("모두 작성하셔야 합니다.",null)
                return
            }
            //새로 생성할 비밀번호가 중복체크와 맞지 않을때 return
            if (etCreatePassword.text.toString() != etPasswordDoubleCheck.text.toString()) {
//                ToastUtil.toastShow("비밀번호가 같지 않습니다.",null)

                return
            }

//                선택한 생년월일이 오늘의 날짜보다 미래일때 return
//            if (userBirthDayConverter > date) {
//                Log.d("dateCheck", "${date},${tvUserbirthDay.text.toString().toInt()}")
//                ToastUtil.toastShow("올바른 생년월일을 선택해주세요",null)
                return
            }

        }

        Toast.makeText(this@RegisterActivity, "메인 액티비티로 이동 ", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this@RegisterActivity, MainActivity::class.java))

        /**
         * TODO:임시로 지정,로직 결정 후 변경 예정
         */
        MainMyRoomFragment.loginCheck = true
    }

    fun ibnClickListener(view: View) {
        tedPermissionCheck(this@RegisterActivity, "이미지 선택 권한을 허용해주세요") {
            startActivityForResult(
                Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                ), REQUEST_IMAGE_CODE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQUEST_IMAGE_CODE && resultCode == Activity.RESULT_OK) {
            imageUrlReSize(binding.ibnProfileImage, data?.data.toString(), 86, 86)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }


    fun btnBirthDaySearchClickListener(view: View) {

        val toDayYear = SimpleDateFormat("yyyy", Locale.KOREA).format(Date()).toInt()
        val toDayMonth = (SimpleDateFormat("MM", Locale.KOREA).format(Date()).toInt())
        val toDay = SimpleDateFormat("dd", Locale.KOREA).format(Date()).toInt()

        DatePickerDialog(
            this@RegisterActivity,
            { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                binding.tvUserbirthDay.text =
                    getString(R.string.register_datePickerSetText, year, monthOfYear, dayOfMonth)
            },
            toDayYear,
            toDayMonth,
            toDay
        ).apply {
            datePicker.touchables[0].performClick()
            show()
        }

    }
}