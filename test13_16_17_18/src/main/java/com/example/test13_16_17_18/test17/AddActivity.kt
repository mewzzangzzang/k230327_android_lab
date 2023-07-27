package com.example.test13_16_17_18.test17

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityAddBinding

//코드와 뷰 부분 복사
// R.menu.menu_add
class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //액션바의 메뉴 아이템의 클릭 리스너 부분에 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.menu_add_save ->{
            //add........................
            //입력창에서 입력된 값 가져오기
            val inputData = binding.addEditView.text.toString()
            val db = DBHelper(this).writableDatabase
            //DBHelper : 일반 클래스 매개변수로 현재 액티비티this로 사용하고
            //writableDatabase 문자열을 이용해서 insert작업을 할 예정
            //기본 함수 execSQL함수를 이용해 insert진행
            //첫 매개변수는 sql문법 insert사용
            //두번째 매개변수는 동적 매개변수 부분의 값으로 할당 될 값을 배열의 요소로 제공
            db.execSQL(
                "insert into TODO_TB (todo) values (?)",
                arrayOf<String>(inputData)
            )
            //db 다사용하고 자원반남
            db.close()
            //후처리 부분
            //메인 플로팅 액션 버튼 클릭->현재add입력창에서 텍스트 입력후
            //입력된 데이터를 인텐트로 다시 데이터를 세터해서 보냅니다
            val intent = intent
            intent.putExtra("result", inputData)
            //응답 메세지를 보냄(성공, emd)
            setResult(Activity.RESULT_OK, intent)
            finish()
            true
        }
        else -> true
    }
}