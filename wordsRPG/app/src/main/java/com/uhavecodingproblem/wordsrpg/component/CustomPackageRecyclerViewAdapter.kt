package com.uhavecodingproblem.wordsrpg.component

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.data.CustomPackageData
import com.uhavecodingproblem.wordsrpg.databinding.ItemCustomPackageRecyclerViewBinding
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.util.SEARCH_PACKAGE_TYPE


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
    private val customPackageList:MutableList<CustomPackageData>, private val recyclerviewType:Int
) : RecyclerView.Adapter<CustomPackageRecyclerViewAdapter.CustomPackageViewHolder>(),Filterable {

    //필터링이 된 list -> 최종적으로  해당 list의 값들이 recyclerview에 뿌려짐.
    private  var filterList: MutableList<CustomPackageData>//1-1

    init {

        //1-1
        filterList =

        if(recyclerviewType == SEARCH_PACKAGE_TYPE){//해시,  제목 같은 검색용 리스트는  처음에  빈 list를 보여준다.
            ArrayList()

        }else{//그외 리스트들은 우선  받아온  리스트를 그대로 뿌려준다.
            customPackageList

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomPackageViewHolder {
       val binding =ItemCustomPackageRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return CustomPackageViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CustomPackageViewHolder, position: Int) {

        holder.onBind(filterList[position])
    }

    override fun getItemCount(): Int = filterList.size


    // TODO: 2020-09-27 여기서 검색어에 대한 search  필터,  검색 search 필터
    //여러 필터 적용 진행시 동작
    override fun getFilter(): Filter {
        return object :Filter(){

            //필터링 진행
            override fun performFiltering(getFilterValue: CharSequence?): FilterResults {

                //필터 값
                val filterValue = getFilterValue.toString()

                filterList= if(filterValue.isEmpty()){//검색 필터가 아닌 경우  위 필터 내용으로 바뀌어야됨 ->  앞으로 필터 추가 예정

                  when(recyclerviewType){
                      SEARCH_PACKAGE_TYPE-> ArrayList() //검색 타입의 경우 아무것도 없으면 빈 array적용
                      else ->customPackageList
                  }



                }else {//검색 필터의 경우 진행

                    //검색된 필터  리스트
                    val searchedFilterList = ArrayList<CustomPackageData>()

                    //받아온 리스트에서  해당 검색어 포함  리스트 필터링
                    for (i in 0 until customPackageList.size) {
                        if (customPackageList[i].packageName.contains(filterValue)) {
                            searchedFilterList.add(customPackageList[i])
                            Logger.v("검색어 포함된 리스트 -> $searchedFilterList")
                        }
                    }
                    searchedFilterList
                }

                val filterResults = FilterResults()
                filterResults.values = filterList//필터 결과에  필터 리스트 넣어서 넘김
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filterList  = p1?.values as MutableList<CustomPackageData>
                Logger.v("필터 결과 ->  $filterList")
                notifyDataSetChanged()//필터된 내용으로 데이터 업데이트.
            }
        }
    }




  //뷰홀더
  inner class CustomPackageViewHolder(val binding:ItemCustomPackageRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){

      //item 뷰에  데이터 바인딩 적용
      fun onBind(data : CustomPackageData){
          binding.data = data
      }
  }
}