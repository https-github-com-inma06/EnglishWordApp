import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.uhavecodingproblem.wordsrpg.R


class SearchLoadingDialog(context: Context) {

    //로딩용 다이얼로그
    private var loadingDialog: Dialog = Dialog(context)
    private val loadingImageView: ImageView
    private val loadingAnimation: Animation


    //외부 context 받아서  로딩용 dialog 세팅 진행
    init {

        loadingDialog.setContentView(R.layout.dialog_search_package_loading)
        loadingDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //다이얼로그 유저가 취소 못하게 만듬.
        // loadingDialog.setCancelable(false)

        loadingImageView = loadingDialog.findViewById(R.id.loading_img)
        loadingAnimation = AnimationUtils.loadAnimation(context, R.anim.search_loading)
    }


    //로딩 다이얼로그 보이기
    fun showLoading(){

        loadingImageView.startAnimation(loadingAnimation)//로딩 애니  시작
        loadingDialog.show()

    }

    //로딩 다이얼로그 숨기기
    fun dismissLoading(){

        loadingImageView.clearAnimation()//로딩 애니 clear
        loadingDialog.dismiss()
    }



}