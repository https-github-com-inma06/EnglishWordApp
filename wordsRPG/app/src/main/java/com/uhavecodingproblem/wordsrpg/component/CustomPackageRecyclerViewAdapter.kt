package com.uhavecodingproblem.wordsrpg.component

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.uhavecodingproblem.wordsrpg.data.CustomPackageData
import com.uhavecodingproblem.wordsrpg.databinding.ItemCustomPackageRecyclerViewBinding
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.util.SEARCH_PACKAGE_TAG
import com.uhavecodingproblem.wordsrpg.util.SEARCH_PACKAGE_TITLE


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
    private val customPackageList:MutableList<CustomPackageData>, private var filterType:Int
) : RecyclerView.Adapter<CustomPackageRecyclerViewAdapter.CustomPackageViewHolder>(),Filterable {


    private var onItemClickListener: OnItemClickListener? =null

    //필터링이 된 list -> 최종적으로  해당 list의 값들이 recyclerview에 뿌려짐.
    private  var filterList: MutableList<CustomPackageData>//1-1

    init {

        //1-1
        filterList =

        if(filterType == SEARCH_PACKAGE_TITLE || filterType == SEARCH_PACKAGE_TAG){//검색용 리스트는  처음에  빈 list를 보여준다
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


    //필터 타입을 외부에서  변경할때  사용한다.
    fun changeType(newFilterType: Int){  this.filterType = newFilterType }



    // TODO: 2020-09-27 여기서 검색어에 대한 search  필터,  검색 search 필터
    //여러 필터 적용 진행시 동작
    override fun getFilter(): Filter {
        return object :Filter(){

            //필터링 진행
            override fun performFiltering(getFilterValue: CharSequence?): FilterResults {

            //필터를 통해 들어온  charsequence 값 -> 검색에서 사용됨
            val searchValue = getFilterValue?.toString()

            //검색어가 없는 경우 필터 타입에 따라 리스트를 filterlist에 적용한다.
            filterList = if(searchValue.isNullOrEmpty()){
                  when(filterType){
                      SEARCH_PACKAGE_TITLE, SEARCH_PACKAGE_TAG-> ArrayList() //검색, 태그 필터의 경우는 검색어가 없으면  빈 array를 뿌려준다.
                      else ->customPackageList// 그 외에는  필터 적용시  각각의 맞는  리스트를  뿌려줌.
                  }

            }else {//필터에  검색어가 적용될때

                    //검색된 필터  리스트
                    val searchedFilterList = ArrayList<CustomPackageData>()

                    if(filterType == SEARCH_PACKAGE_TITLE) {//제목 필터가 적용될때

                        //받아온 리스트에서  해당 검색어 포함  리스트 필터링
                        for (i in 0 until customPackageList.size) {
                            if (customPackageList[i].packageName.contains(searchValue)) {
                                searchedFilterList.add(customPackageList[i])
                                Logger.v("검색어 포함된 리스트 -> $searchedFilterList")
                            }
                        }//for문 끝
                    }else if(filterType == SEARCH_PACKAGE_TAG){//태그 필터 적용되었을때

                        for (i in 0 until customPackageList.size) {

                            //태그리스트 filter 해서  가장 마지막에  필터된  태그값  가진 패키지 넣어줌.
                            customPackageList[i].hashList.findLast {
                                Logger.v("pos -> $i   searchvalue-> $searchValue  find-> last-> $it")
                                if(it.contains(searchValue)){
                                    searchedFilterList.add(customPackageList[i])
                                }else{
                                    false
                                }
                            }

                        }//for문 끝

                    }//태그 필터 끝


                    searchedFilterList//검색으로 필터링된  리스트 filterlist에 적용
                }

                val filterResults = FilterResults()
                filterResults.values = filterList//필터 결과에  필터 리스트 넣어서 넘김
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {//filter 결과  발행
                filterList  = p1?.values as MutableList<CustomPackageData>
                Logger.v("필터 결과 ->  $filterList")
                notifyDataSetChanged()//필터된 내용으로 데이터 업데이트.
            }
        }


    }//getFilter() 끝




    //아이템 클릭 이벤트 받을  리스너 인터페이스
    interface  OnItemClickListener {
        fun onItemClick(view:View,packageName:String)
        // TODO: 2020-09-27 임시적으로 패키지네임만 넘기게  구성  필요한  정보 더 추가해서 넘겨줘야됨
    }


    //외부에서  아이템 클릭 처리할 리스너
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }


  //뷰홀더
  inner class CustomPackageViewHolder(val binding:ItemCustomPackageRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){

      //item 뷰에  데이터 바인딩 적용
      fun onBind(data : CustomPackageData){
          binding.data = data

          binding.containerItem.setOnClickListener {

              val pos = adapterPosition//클릭된 아이템 포지션
              filterList[pos].packageName

              if (pos != RecyclerView.NO_POSITION) {

                  // 리스너 객체의 메서드 호출.
                  onItemClickListener?.onItemClick(view = it,packageName =  filterList[pos].packageName)

              }

          }


      }
  }
}