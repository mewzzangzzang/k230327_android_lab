package com.example.test13_16_17_18.test17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain3Binding
import com.example.test13_16_17_18.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //기본 SQLite 살펴보기
    //연습문제
    //https://github.com/lsy3709/AndroidLab/tree/master/ch17_database/src/main/java/com/example/ch17_database
    // 1)AddActivity.kt 액티비티 클래스
    // 변경사항 -> R.menu.menu_add 추가
    // 2)DBHelper.kt 일반 클래스
    // 3)MainActivity 변경
    // -1.menu_main 생성
    // https://github.com/lsy3709/AndroidLab/blob/master/ch17_database/src/main/res/menu/menu_main.xml
    // -2.추가 세팅부분 제거 예정 SettingActivity 부분 주석

    // 4) MyAdapter.kt
    //액션바
    //3 crud 블로그 참고
    //https://github.com/lsy3709/K230201AndroidLab/tree/master/test17-crud/src/main/java/com/example/test17_crud


    lateinit var binding: ActivityMain3Binding
    //AddActivity에서 입력된 한줄의 텍스트들을 요소로 리스트에 보관
    var datas: MutableList<String>? = null
    //입력된 문자열 내용을, 어댑터
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //입력창으로 텍스트 입력 후에 저장 버튼을 누르면 여기로 돌아와요
        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            //돌아온 결과값은 it이라는 객체 Map에 저장
            //키가 result라는 부분의 값을 가지고 와서
            // datas 라는 리스트에 add담기
            // 어댑터 객체에 함수중에 변경사항을 알리는 함수를 수행에서
            // 리사이클러뷰
            it.data!!.getStringExtra("result")?.let {
                datas?.add(it)
                adapter.notifyDataSetChanged()
            }
        }
        // 플로팅 액션바 버튼 클릭 이벤트 처리 -> 입력 액티비티로 이동
        //후처리를 하는 함수.requestLauncher
        //입력창에서 투두로 입력후에 입력된 값을 가지고  val requestLauncher = registerForActivityResult로 되돌아 옴
        // AddActivity 데이터를 처리하는 세터부분이 있음
        binding.mainFab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            requestLauncher.launch(intent)
        }

        //변경가능한 리스트 형식으로 객체 선언
        datas= mutableListOf<String>()

        //조회
        // readableDatebase -> 일기

        val db = DBHelper(this).readableDatabase
        // 커서 cursor 쉽게, 조회된 결과를 테이블 형식으로 저장된 객체
        val cursor = db.rawQuery("select * from TODO_TB", null)
        //테이블 형식으로 저장되어있음
        cursor.run {
            //반복문으로 커서 테이블에 데이터를 1행씩 불러와서 해당 컬럼을 가져오기
            //커서는 1행부터 선택함 원래 리스트 인덱스0부터
            while(moveToNext()){
                datas?.add(cursor.getString(1))
            }
        }
        //디비 서버에서 조회된 내용을 현재 메모리 datas라는 리스트에 다 담기
        //디비 서버 반납
        db.close()

        //리사이클러뷰 적용
        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.layoutManager=layoutManager
        //디비 서버에서 받아온 데이터를 메모리 상에 임시 객체 datas 담아서
        //어댑터 클래스 연결부분
        adapter=MyAdapter(datas)
        //어댑터 클래스에 적용된 데이터<->뷰 결과를 뷰에 적용
        binding.mainRecyclerView.adapter=adapter
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId===R.id.menu_main_setting){
//            val intent = Intent(this, SettingActivity::class.java)
//            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}