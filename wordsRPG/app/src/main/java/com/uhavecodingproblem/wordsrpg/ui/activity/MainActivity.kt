package com.uhavecodingproblem.wordsrpg.ui.activity

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.ActivityMainBinding
import com.uhavecodingproblem.wordsrpg.ui.activity.setting.SettingActivity
import com.uhavecodingproblem.wordsrpg.ui.base.BaseUtility


/**
 * wordsrpg
 * Class: MainActivity.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 *
 * 메인 엑티비티 입니다.
 */

class MainActivity : BaseUtility.BaseActivity<ActivityMainBinding>(R.layout.activity_main)  {

    override fun ActivityMainBinding.onCreate() {
        Logger.d("실행")

        configureToolbar()//1-1
        setUpNavigation()//1-2

    }

    //toolbar  setting
    private fun configureToolbar(){//1-1
        //action bar로 연결
        setSupportActionBar(binding.mainToolbar)

        val actionbar = supportActionBar

        actionbar?.setDisplayHomeAsUpEnabled(false)
        actionbar?.setDisplayShowTitleEnabled(false)//toolbar  title 안보이게

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu_item,menu)
        return true
    }

    //툴바에서  option item  select 리스너
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            //오른쪽 상단 햄버거 키 클릭
            R.id.menu_setting ->{
                //binding.mainDrawerLayout.openDrawer(GravityCompat.START)//드로워  오픈
                /* 액티비티 */
                startActivity(Intent(this@MainActivity, SettingActivity::class.java))
                Toast.makeText(this,"세팅눌림",Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }



    //네이게이션의  현재 fragment 변경 사항을 listen 한다. ( 안쓴 값  _ 처리)
    private val navDestinationChangedListener = NavController.OnDestinationChangedListener {
            _, destination, _ ->

        //navigation에 설정한 label로
        //바뀐 fragment가 무엇인지 감지한다.
        when(destination.label){

            //현재 fragment가  검색 프래그먼트 일때  액션바  숨겨줌.
            "MainLibraryFragment" -> { Logger.d("현재 프래그먼트 -> 라이브러리")
                binding.tvPresentMenu.setText(R.string.menu_for_library_en)
            }

            "MainBattleFragment" -> { Logger.d("현재 프래그먼트 -> 배틀 ")
                 binding.tvPresentMenu.setText(R.string.menu_for_battle_en)
            }

            "MainMyRoomFragment" -> { Logger.d("현재 프래그먼트 -> 마이룸")
                binding.tvPresentMenu.setText(R.string.menu_for_my_room_en)
            }

        }
    }



    //bottom navigation을  jetpack navigation controller로 연결
    private fun setUpNavigation() {//1-2

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?

        val navController=navHostFragment!!.navController


        //네비게이션  현재 fragment  측정하기 위한 리스너
        navController.addOnDestinationChangedListener(navDestinationChangedListener)


        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navController
        )

        //네비게이션 사용시 같은 바텀 메뉴  누를때,
        //프래그먼트  중복 실행을 방지하기 위해서 추가
        binding.bottomNavigationView.setOnNavigationItemReselectedListener {}
    }




}//클래스 끝