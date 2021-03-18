package com.uhavecodingproblem.wordsrpg.ui.fragment.library

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.component.library.recyclerviewadapter.CustomPackageRecyclerViewAdapter
import com.uhavecodingproblem.wordsrpg.data.model.Package
import com.uhavecodingproblem.wordsrpg.databinding.FragmentEntireCustomPackageBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility
import com.uhavecodingproblem.wordsrpg.util.*
import com.uhavecodingproblem.wordsrpg.util.Logger

/**
 * wordsrpg
 * Class: SubscribePackageFragment
 * Created by pyg10.
 * Created On 2020-09-18.
 * Description:
 *
 */
class EntireCustomPackageFragment :
    BaseUtility.BaseFragment<FragmentEntireCustomPackageBinding>(R.layout.fragment_entire_custom_package) {


    lateinit var recyclerViewAdapter: CustomPackageRecyclerViewAdapter
    private val dataList:MutableList<Package> = mutableListOf()
    private val db = FirebaseDatabase.getInstance().reference.child("Package")

    override fun FragmentEntireCustomPackageBinding.onCreateView() {
        Logger.d("실행")

        thisFragment = this@EntireCustomPackageFragment

        setCustomPackageRecyclerView()
    }

    //커스텀 패키지를 뿌려줄 리사이클러뷰 세팅
    private fun setCustomPackageRecyclerView() {

        db.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(snap in snapshot.children){
                    snap.getValue(Package::class.java).also {
                        if (it?.customCheck == "1")
                        dataList.add(it)
                    }
                }
                recyclerViewAdapter = CustomPackageRecyclerViewAdapter(
                    dataList,
                    ENTIRE_CUSTOM_PACKAGE
                )
                //adatper 연결 -> 리사이클러뷰 TYPE은 ORIGINAL 타입으로 -> 리스트 다 뿌려줌.


                //각 패키지 아이템 클릭시  넘어감 처리  구현
                recyclerViewAdapter.setOnItemClickListener(object : CustomPackageRecyclerViewAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, packageName: String) {
                        Toast.makeText(requireActivity(), "이 패키지로 넘기기 -> $packageName", Toast.LENGTH_SHORT).show()
                    }
                })

                binding.recyclerviewCustomList.apply {
                    layoutManager = GridLayoutManager(requireActivity(), 3) //grid 형태로  뿌려줌
                    adapter = recyclerViewAdapter
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })


    }//initRecyclerView()끝


    //필터 바뀜 처리
    fun filterChange(filter: Int) {

        changeFilterAnimation(binding.viewgroupLibrarySearch.iconSearchFilter).start()

        when (filter) {
            SEARCH_PACKAGE_TITLE -> {
                binding.viewgroupLibrarySearch.tvSearchFilter.setText(R.string.str_filter_title)
            }

            SEARCH_PACKAGE_TAG -> {
                binding.viewgroupLibrarySearch.tvSearchFilter.setText(R.string.str_filter_tag)

            }
        }
    }//filterChange 끝


    //필터 바뀜  애니메이션
    private fun changeFilterAnimation(view: View): Animation {
        view.animation = AnimationUtils.loadAnimation(requireActivity(), R.anim.search_filter_rotation)
        return view.animation
    }


    // TODO: 2020-10-29 viewpager selection 에서  keyboard hide 처리를 할때 editText focus 시에
    // TODO: 다시  keyboard  hide 되는  문제가 있어 각 라이브러리 fragment 의 onResume 에서 처리
    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }

}//class 끝