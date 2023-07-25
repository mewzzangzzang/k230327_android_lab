package com.example.test13_16_17_18.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain408Binding

//https://github.com/lsy3709/AndroidLab/blob/master/test13/src/main/java/com/example/test13/MainActivity408.kt
class MainActivity408 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain408Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //후처리 결과는 동일, 과저이 다름
        // 구글 측에서 이 함수 사용을 권고
        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            //이 부분이 시스템에서 제공을 함
            //pdf 13-p11
            //ex) StartActivityForResult() 후처리
            //ex)사진촬영
            //ex)권한요청
            //ActivityResultContracts.StartActivityForResult()
            ActivityResultContracts.StartActivityForResult())
        {
            //it 후처리의 결과 객체
            val resultData = it.data?.getStringExtra("resultData")
            binding.mainResultView.text = "result : $resultData"
        }

        binding.button1.setOnClickListener {

            val intent: Intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 10)
            // 여기서 , 후처리 함수 호출 현재: 1번: 메인-> 2번: 디테일
            // 2번 디테일 화면에서 데이터를 잘 설정해서 되돌려주면
            //    ActivityResultContracts.StartActivityForResult()
            // 콜백함수의 결과 처리창에서 작업을 함
            requestLauncher.launch(intent)
        }
    }
}