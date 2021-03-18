package com.uhavecodingproblem.wordsrpg.component.library.recyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.data.model.Package
import com.uhavecodingproblem.wordsrpg.databinding.ItemCustomPackageBinding
import com.uhavecodingproblem.wordsrpg.databinding.ItemMyCustomPackageAddBinding
import com.uhavecodingproblem.wordsrpg.util.*


/**
 * wordsrpg
 * Class: CustomPackageRecyclerViewAdapter.
 * Created by leedonghun.
 * Created On 2020-09-25.
 * Description:
 *
 * 커스텀 패키지 리사이클러뷰  어뎁터
 * 내 패키지 &&  전체 패키지 && 검색화면에서 공동 사용 (예상)
 *
 */
class CustomPackageRecyclerViewAdapter(
    private val customPackageList: MutableList<Package>, private var customPackageType: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var onItemClickListener: OnItemClickListener? = null
private var onAddItemClickListener: OnAddItemClickListener?=null
//아이템 클릭 이벤트 받을  리스너 인터페이스
    interface OnItemClickListener {
        fun onItemClick(view: View, packageName: String)
        // TODO: 2020-09-27 임시적으로 패키지네임만 넘기게  구성  필요한  정보 더 추가해서 넘겨줘야됨
    }
    interface OnAddItemClickListener {
        fun onItemClick()
    }
    //외부에서  아이템 클릭 처리할 리스너
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    //외부에서 추가버튼 클릭 처리할 리스너
    fun setOnAddItemClickListener(addItemClickListener:OnAddItemClickListener) {
        this.onAddItemClickListener = addItemClickListener
    }


    init {
        // TODO: 2020-10-31 추가아이템  자리 만들고 확인 위해서 일단 이렇게 해놓음. -> 추후 리팩토링
        //내 커스텀의 경우는  index 0쪽에 mock 값  넣어줌.
        if (customPackageType == MY_CUSTOM_PACKAGE) {
            customPackageList.add(0, customPackageList[0])
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemCustomPackageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val binding1 = ItemMyCustomPackageAddBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return when (viewType) {

            0 -> {
                CustomPackageViewHolder(binding)
            }

            1 -> {
                AddNewMyCustomPackage(binding1)
            }
            else -> {

                CustomPackageViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (customPackageType == MY_CUSTOM_PACKAGE) {

            if (position == 0) {
                (holder as AddNewMyCustomPackage).onBind()
            } else {
                (holder as CustomPackageViewHolder).onBind(customPackageList[position])
            }

        } else {
            (holder as CustomPackageViewHolder).onBind(customPackageList[position])

        }
    }


    // TODO: 2020-10-31 일단 이렇게 적용하고 나중에 명확하게  코드 다시  작성
    override fun getItemViewType(position: Int): Int {
         when (customPackageType) {

            ENTIRE_CUSTOM_PACKAGE -> {

                return 0
            }

            MY_CUSTOM_PACKAGE -> {

                return if (position == 0) {
                    1
                } else {
                    0
                }
            }

            else -> {

                return 0
            }
        }

    }

    override fun getItemCount(): Int = customPackageList.size






    //추가 아이템 뷰홀더
    inner class AddNewMyCustomPackage(val binding: ItemMyCustomPackageAddBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            //아이템 클릭 리스너
            binding.layoutCustomPackageAdd.setOnClickListener {
                // TODO: 2020-10-31 추후 리팩토링 필요.  외부로  빼서 패키지 추가  액션 진행해야됨
                onAddItemClickListener?.onItemClick()
                Logger.d("아이템 추가 ")

            }//아이템 클릭리스너 끝
        }

    }


    //뷰홀더
    inner class CustomPackageViewHolder(val binding: ItemCustomPackageBinding) : RecyclerView.ViewHolder(binding.root) {

        //item 뷰에  데이터 바인딩 적용
        fun onBind(packAge: Package) {

            val pos = adapterPosition//아이템 포지션

            //리사이클러뷰 item xml 에 data 연
            binding.packAge = packAge

            //아이템 라운드 적용
            binding.layoutCustomPackage.clipToOutline = true


            //아이템 클릭 리스너
            binding.layoutCustomPackage.setOnClickListener {
                customPackageList[pos].package_name

                if (pos != RecyclerView.NO_POSITION) {

                    // 리스너 객체의 메서드 호출.
                    onItemClickListener?.onItemClick(
                        view = it,
                        packageName = customPackageList[pos].package_name
                    )

                }

            }//아이템 클릭리스너 끝


        }
    }
}