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
import com.uhavecodingproblem.wordsrpg.data.model.User
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
    private var dataList:MutableList<Package> = mutableListOf()
    private val db = FirebaseDatabase.getInstance().reference.child("Package")
    private val userDb = FirebaseDatabase.getInstance().reference.child("User")
    private var filterMode:Int?=null
    private var userData = User()
    override fun FragmentEntireCustomPackageBinding.onCreateView() {
        Logger.d("실행")
        viewgroupLibrarySearch.checkPackage = ENTIRE_CUSTOM_PACKAGE
        thisFragment = this@EntireCustomPackageFragment

        setUserDb()
        setFilterMode()
        setCustomPackageRecyclerView()
        setFilterListener()


        viewgroupLibrarySearch.imgSelectedHeart.setOnClickListener {
            it.isSelected = !it.isSelected
        }
    }

    private fun setUserDb() {
        userDb.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snap in snapshot.children) {
                    snap.getValue(User::class.java).also {
                        if (it!!.u == SharedPreferenceUtil.userIdx)
                            userData = it
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun FragmentEntireCustomPackageBinding.setFilterListener() {
        viewgroupLibrarySearch.editTvSearchPackage.setOnEditorActionListener { v, actionId, event ->
            FirebaseDatabase.getInstance().reference.child("Package").addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val getTempList = mutableListOf<Package>()
                        for (snap in snapshot.children) {
                            snap.getValue(Package::class.java).also {
                                if (it?.customCheck == "1")
                                    getTempList.add(it)
                            }
                        }
                        (recyclerviewCustomList.adapter as CustomPackageRecyclerViewAdapter).apply {
                            val search = viewgroupLibrarySearch.editTvSearchPackage.text.toString()
                            var filterList = if (filterMode == SEARCH_PACKAGE_TITLE)
                                dataList.filter { it.package_name.contains(search)}
                            else
                                dataList.filter { it.hashTagList!!.contains(search) }

                            if(viewgroupLibrarySearch.imgSelectedHeart.isSelected)
                                filterList =
                                    filterList.sortedBy { it.likeList?.contains(SharedPreferenceUtil.userIdx!!) }


                            customPackageList.clear()
                            customPackageList = filterList as MutableList<Package>
                            notifyDataSetChanged()
                            dataList = getTempList
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
            true
        }
    }


    private fun FragmentEntireCustomPackageBinding.setFilterMode() {
        filterMode = if (viewgroupLibrarySearch.tvSearchFilter.text == getString(R.string.str_filter_title))
            SEARCH_PACKAGE_TITLE
        else
            SEARCH_PACKAGE_TAG
    }

    //커스텀 패키지를 뿌려줄 리사이클러뷰 세팅
    private fun setCustomPackageRecyclerView() {

        db.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for(snap in snapshot.children){
                    snap.getValue(Package::class.java).also {
                        if (it?.customCheck == "1") {
                            dataList.add(it)
                        }
                    }
                }
                /**테스트*/
                recyclerViewAdapter = CustomPackageRecyclerViewAdapter(
                    dataList.reversed() as MutableList<Package>,
                    ENTIRE_CUSTOM_PACKAGE
                )
                //adatper 연결 -> 리사이클러뷰 TYPE은 ORIGINAL 타입으로 -> 리스트 다 뿌려줌.


                //각 패키지 아이템 클릭시  넘어감 처리  구현
                recyclerViewAdapter.setOnItemClickListener(object : CustomPackageRecyclerViewAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, packageData: Package) {
                        Toast.makeText(requireActivity(), "이 패키지로 넘기기 -> ${packageData.package_name}", Toast.LENGTH_SHORT).show()
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
            SEARCH_PACKAGE_TITLE ->
              binding.viewgroupLibrarySearch.tvSearchFilter.setText(R.string.str_filter_title)


            SEARCH_PACKAGE_TAG ->
                binding.viewgroupLibrarySearch.tvSearchFilter.setText(R.string.str_filter_tag)

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