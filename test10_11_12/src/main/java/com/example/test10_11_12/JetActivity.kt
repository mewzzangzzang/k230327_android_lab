package com.example.test10_11_12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.test10_11_12.databinding.ActivityJetBinding

class JetActivity : AppCompatActivity() {
    lateinit var binding: ActivityJetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //툴바를 직접 만들어서 뷰에 붙이는 작업
        // 뷰, 뒷단 코드 가져오기
        //경로
        setSupportActionBar(binding.toolbar)
    }




        //메뉴, 액션바(테마사용)->툴바로 대체 할 예정 기초
        //메뉴 구성방법 1) 코드로 2) xml 구성하는 방식(현재 xml로 작업중)
        // 경로
        // https://github.com/lsy3709/AndroidLab/blob/master/test11/src/main/java/com/example/test11/MainActivity328.kt
        // 뷰 경로 https://github.com/lsy3709/AndroidLab/blob/master/test11/src/main/res/menu/menu_328.xml
        // 참고했지만 파일을 직접만들어서 확인

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            //화면 출력하기 위한 객체 생성
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu1 -> {
            Log.d("kkang", "menu1 click")
            Toast.makeText(this@JetActivity,"아이콘 클릭",Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu2 -> {
            Log.d("kkang", "menu2 click")
            Toast.makeText(this@JetActivity,"1 클릭",Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu3 -> {
            Log.d("kkang", "menu2 click")
            Toast.makeText(this@JetActivity,"3 클릭",Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu22 -> {
            Log.d("kkang", "menu2 click")
            Toast.makeText(this@JetActivity,"1 클릭",Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu3 -> {
            Log.d("kkang", "menu2 click")
            Toast.makeText(this@JetActivity,"1 클릭",Toast.LENGTH_SHORT).show()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }
}