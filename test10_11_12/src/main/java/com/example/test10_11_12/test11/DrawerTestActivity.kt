package com.example.test10_11_12.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.test10_11_12.R
import com.example.test10_11_12.databinding.ActivityDrawerTestBinding

class DrawerTestActivity : AppCompatActivity() {
    //기존 뷰바인딩선언
    lateinit var binding: ActivityDrawerTestBinding
    //추가
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //경로
        //https://github.com/lsy3709/AndroidLab/blob/master/test11/src/main/java/com/example/test11/MainActivity357.kt

        //뷰 부분을 잘 봐야함
        // 드로워 태그 하위에 뷰2개가 있고, 첫번째 뷰가 메인 화면뷰,두번째 뷰가 서랍뷰
        // 경로
        //https://github.com/lsy3709/AndroidLab/blob/master/test11/src/main/res/layout/activity_main357.xml

        //ActionBarDrawerToggle 버튼 적용
        toggle = ActionBarDrawerToggle(this, binding.drawer,
            R.string.drawer_opened,
            R.string.drawer_closed
        )
        //액션바에 토글부분을 연결시켜서 ,버튼 클릭하면, 서랍 화면이 보인다
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //동기화부분
        toggle.syncState()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}