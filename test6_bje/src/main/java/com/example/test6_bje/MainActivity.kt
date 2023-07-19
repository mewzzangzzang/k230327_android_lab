package com.example.test6_bje

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.test6_bje.databinding.ActivityMainBinding
import kotlinx.coroutines.joinAll

//액티비티:화면을 그려주는 역할
class MainActivity : AppCompatActivity() {


    //최조에 한번 화면에 출력하는 역할.
    override fun onCreate(savedInstanceState: Bundle?) {


        //뷰 바인딩 적용1
        //시스템에서 자동으로 생성. ActivityMainBinding
        //여기 코드, 설정 코드이기때문에 디비 연결하는 Connection 객체 용도가 비슷
         val binding=ActivityMainBinding.inflate(layoutInflater)
        //savedInstanceState: Bundle? -> 임시객체
        super.onCreate(savedInstanceState)
        //레이아웃 샘플로 리니어 사용하는 중, 왜냐 제약 조건 설정이 간단해서
        //뷰만 선택해서 배치만 하면 기본 세로 가로방향으로 배치가 간단함

        //setContentView(R.layout.activity_main)
        //뷰 바인딩 적용2
        setContentView(binding.root)

        //버튼 클릭 -> 이미지 -> show/hide
        //버튼에 이벤트 클릭 리스너 설정 후-> 결과로 이미지의 속성 중에
        //visibility 속성으로 show/hide 테스트
        // binding 객체안에 사용하는 뷰의 모든 객체가 다 담아져 있습니다
        //추가로 버튼하나로 토글(스위치)처럼 => 상태패턴, 상태를 나타내는 변수를 하나 정해야함
        binding.btn1.setOnClickListener {
            //스위치 효과를 주기위해 상태패턴의 변수를 이용해서
            //이미지 보여주고, 안보여주기
            //선언만 했숴요,~
            var status : Int = 0

            if(status == 0) {
                binding.img1.visibility = View.GONE
                status = 1
            }else {
                binding.img1.visibility = View.VISIBLE
                status = 0
            }
        println("status 값 확인 :$status")
        }
        //버튼 2개로 하는 경우
       /* binding.btn2.setOnClickListener {
            binding.img1.visibility = View.VISIBLE
        }*/
        //회원가입 버튼 클릭시 화면이동
        binding.joinBtn.setOnClickListener {
            //인텐트 라는 개념에 가장 기초인 화면전환
            //화면 전환시, 데이터를 가지고 이동도 할 예정
            val intent = Intent(this@MainActivity, JoinActivity::class.java)
            startActivity(intent)

        }
        //아이디, 패스워드를 입력받고, 콘솔에 출력, 토스트(알림창) 출력
        binding.loginBtn.setOnClickListener{
            val id : String = binding.idInput.text.toString()
            val pw : String = binding.pwInput.text.toString()
            Log.d("zzz","id의 값 : $id , pw의 값 : $pw")
            Toast.makeText(this@MainActivity,"id의 값 : $id , pw의 값 : $pw",Toast.LENGTH_SHORT).show()
            //Toast.makeText(this@MainActivity,"test",Toast.LENGTH_SHORT)
        }
    }
}