package com.uhavecodingproblem.wordsrpg.component.library.bindingadpater

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.uhavecodingproblem.wordsrpg.util.SEARCH_PACKAGE_TAG
import com.uhavecodingproblem.wordsrpg.util.SEARCH_PACKAGE_TITLE

/**
 * wordsrpg
 * Class: BindingAdapter.
 * Created by leedonghun.
 * Created On 2020-09-25.
 * Description:
 *
 * 커스텀  패키지 binding adapter 모음
 *
 */
object CustomPackageBindingAdapter {


    //커스텀 패키지 썸네일 이미지  binding 용
    @BindingAdapter("image","error")
    @JvmStatic
    fun loadImage(
        imageView: ImageView,
        url:String?,
        error: Drawable
    ){
        try {
            Glide.with(imageView.context)
                .load(url)
                .error(error)
                .centerCrop()
                .into(imageView)
        }catch (e:Exception){
            e.printStackTrace()
        }

    }


    //editext search action binding 용
    @BindingAdapter("searchAction")
    @JvmStatic
    fun keyboardSearchAction(
        editText: EditText,
        editorActionListener: TextView.OnEditorActionListener
    ){
        editText.setOnEditorActionListener(editorActionListener)
    }


    //커스텀 패키지(내패키지, 전체패키지) 검색  필터값 변경
    @BindingAdapter("android:onClick")
    @JvmStatic
    fun changeSearchFilter(
        searchFilterViewGroup: ViewGroup,
        filterChangeListener: FilterChangeListener
    ){

        //맨처음 필터값은 -> default는  '제목'
        var presentFilter = SEARCH_PACKAGE_TITLE

        //필터 클릭 이벤트
        searchFilterViewGroup.setOnClickListener {
            when(presentFilter){

                //현재 필터 tag 일때 -> <제목으로 필터 변경> 및 <뷰에게 필터 바뀜을 알림>.
                SEARCH_PACKAGE_TAG ->{
                    presentFilter =SEARCH_PACKAGE_TITLE
                    filterChangeListener.setFilterChangeListener(presentFilter)
                }

                SEARCH_PACKAGE_TITLE ->{
                    presentFilter = SEARCH_PACKAGE_TAG
                    filterChangeListener.setFilterChangeListener(presentFilter)
                }
            }
        }
    }//changeSearchFilter 끝

    //검색용 필터  변화[ex (제목 -> 태그)]를  listen 한다.
    interface FilterChangeListener { fun setFilterChangeListener(filter: Int) }


}