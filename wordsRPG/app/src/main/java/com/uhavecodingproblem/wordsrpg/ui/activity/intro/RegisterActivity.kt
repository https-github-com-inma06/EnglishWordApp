package com.uhavecodingproblem.wordsrpg.ui.activity.intro

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.provider.MediaStore
import android.view.View
import android.widget.DatePicker
import androidx.activity.viewModels
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.databinding.ActivityRegisterBinding
import com.uhavecodingproblem.wordsrpg.db.server_db.dataholder.User
import com.uhavecodingproblem.wordsrpg.ui.activity.MainActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity
import com.uhavecodingproblem.wordsrpg.ui.fragment.MainMyRoomFragment
import com.uhavecodingproblem.wordsrpg.util.imageUrlReSize
import com.uhavecodingproblem.wordsrpg.util.tedPermissionCheck
import com.uhavecodingproblem.wordsrpg.util.toastShow
import com.uhavecodingproblem.wordsrpg.viewmodel.AuthViewModel
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {

    private val authViewModel by viewModels<AuthViewModel>()

    companion object {
        const val REQUEST_IMAGE_CODE = 7852
    }

    override fun ActivityRegisterBinding.onCreate() {

    }

    fun btnRegisterComplete(view: View) {
        with(binding) {
            val email = etCrateId.text.toString()
            val password = etCreatePassword.text.toString()
            val userName:String? = etUserName.text.toString()
            val userNick:String? = etNickName.text.toString()
            val createdAt: Int = System.currentTimeMillis().toInt()


            //하나라도 text 가 비었을 때 return
            if (email.isEmpty() || password.isEmpty() ||
                etPasswordDoubleCheck.text.toString().isEmpty()) {
                toastShow("필요한 부분은 꼭 작성 합니다.")
                return

            }
            //새로 생성할 비밀번호가 중복체크와 맞지 않을때 return
            if (etCreatePassword.text.toString() != etPasswordDoubleCheck.text.toString()) {
                toastShow("비밀번호가 같지 않습니다.")
                return
            }

            val user = User(
                userId = email,
                email = email,
                userPassword = password,
                userName =userName,
                createdAt = createdAt,
                userNick = userNick,
                emailVerifiedAt = null,
                rememberToken = null,
                updatedAt = null
            )

            authViewModel.userRegister(user,
                onSucceed = {
                    startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                }
            ,onFailure ={ toastShow("정확한 정보로 다시 입력해주세요")}
            )

            /**
             * TODO:임시로 지정,로직 결정 후 변경 예정
             */
        }
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
        val toDayMonth = (SimpleDateFormat("MM", Locale.KOREA).format(Date()).toInt() - 1)
        val toDay = SimpleDateFormat("dd", Locale.KOREA).format(Date()).toInt()

        val minDate = Calendar.getInstance()
        val maxDate = Calendar.getInstance()

        DatePickerDialog(
            this@RegisterActivity,
            { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                binding.tvUserbirthDay.text = getString(R.string.register_datePickerSetText,
                        year,
                        monthOfYear + 1,
                        dayOfMonth
                    )
            },
            toDayYear,
            toDayMonth,
            toDay
        ).apply {
            minDate.set(toDayYear - 80, toDayMonth, toDay)
            maxDate.set(toDayYear, toDayMonth, toDay)

            with(datePicker) {
                this.minDate = minDate.timeInMillis
                this.maxDate = maxDate.timeInMillis
                touchables[0].performClick()
            }
            setTitle("")
            show()
        }
    }
}