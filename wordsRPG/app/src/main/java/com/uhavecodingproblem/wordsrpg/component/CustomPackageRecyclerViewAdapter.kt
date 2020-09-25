package com.uhavecodingproblem.wordsrpg.component

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.data.CustomPackageData
import com.uhavecodingproblem.wordsrpg.databinding.ItemCustomPackageRecyclerViewBinding

/**
 * wordsrpg
 * Class: CustomPackageRecyclerViewAdapter.
 * Created by leedonghun.
 * Created On 2020-09-25.
 * Description:
 *
 * 커스텀 패키지 리사이클러뷰  어뎁터
 * 내 패키지 및  전체 패키지 공동 사용 (예상)
 */
class CustomPackageRecyclerViewAdapter(
    private val customPackageList:MutableList<CustomPackageData>
) : RecyclerView.Adapter<CustomPackageRecyclerViewAdapter.CustomPackageViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomPackageViewHolder {
       val binding =ItemCustomPackageRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return CustomPackageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomPackageViewHolder, position: Int) {
       holder.onBind(customPackageList[position])
    }

    override fun getItemCount(): Int = customPackageList.size

  //뷰홀더
  inner class CustomPackageViewHolder(val binding:ItemCustomPackageRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){

      //item 뷰에  데이터 바인딩 적용
      fun onBind(data : CustomPackageData){
          binding.data = data
      }
  }
}