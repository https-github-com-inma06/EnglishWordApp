package com.uhavecodingproblem.wordsrpg.ui.activity

import android.content.Intent
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.uhavecodingproblem.wordsrpg.R
import com.uhavecodingproblem.wordsrpg.util.Logger
import com.uhavecodingproblem.wordsrpg.databinding.ActivityMainBinding
import com.uhavecodingproblem.wordsrpg.ui.base.BaseActivity


/**
 * wordsrpg
 * Class: MainActivity.
 * Created by leedonghun.
 * Created On 2020-09-16.
 * Description:
 *훑어보겠습니다.  넵!
 * dnjsrur
 * 메인 엑티비티 입니다.
 */

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) ,NavigationView.OnNavigationItemSelectedListener {

    override fun ActivityMainBinding.onCreateSetData() {
        Logger.v("실행")

        configureToolbar()//1-1
        setUpNavigation()//1-2

        drawerNavigationView.setNavigationItemSelectedListener(this@MainActivity)
        mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)//드로워  swipe 기능  lock

    }




    //toolbar  setting
    private fun configureToolbar(){//1-1

        //action bar로 연결
        setSupportActionBar(binding.mainToolbar)

        val actionbar=supportActionBar
        actionbar?.setHomeAsUpIndicator(R.drawable.drawer)//햄버거 아이콘 넣어줌.
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayShowTitleEnabled(false)//toolbar  title 안보이게

    }




    //툴바에서  option item  select 리스너
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            //왼쪽 상단 햄버거 키 클릭
            android.R.id.home ->{
                binding.mainDrawerLayout.openDrawer(GravityCompat.START)//드로워  오픈
            }
        }
        return true
    }



    //drawer 네비 아이템 select 리스너
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            //오답노트
            R.id.wrong_answer->{
                val intent=Intent(this, WrongAnswerActivity::class.java)
                startActivity(intent)
            }

            //세팅창
            R.id.setting->{
                val intent=Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }

            //알림창
            R.id.notification-> {
                val intent=Intent(this, NotificationActivity::class.java)
                startActivity(intent)
            }

            R.id.logout->{
                Toast.makeText(this,"로그아웃 눌림",Toast.LENGTH_SHORT).show()
            }
        }
        binding.mainDrawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아줌.
        return false
    }




    //back 키 눌렀을때
    override fun onBackPressed() {

        //drawer 나와있는 경우  먼저 닫아줌.
        //그외는  원래 back 이벤트 진행
        if(binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.mainDrawerLayout.closeDrawers()
        }else{
            super.onBackPressed()
        }
    }




    //bottom navigation을  jetpack navigation controller로 연결
    private fun setUpNavigation() {//1-2

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?

        val navController=navHostFragment!!.navController

        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navController
        )

        //네비게이션 사용시 같은 바텀 메뉴  누를때,
        //프래그먼트  중복 실행을 방지하기 위해서 추가
        binding.bottomNavigationView.setOnNavigationItemReselectedListener {}
    }




}//클래스 끝