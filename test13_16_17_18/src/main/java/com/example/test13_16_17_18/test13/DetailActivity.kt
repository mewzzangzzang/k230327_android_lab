package com.example.test13_16_17_18.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityDetailBinding

//test13/src/main/java/com/example/test13/DetailActivity.kt
class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("kkang","DetailActivity..onCreate..........")
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //인텐트로 전달받은 값을 가져오는역할 마치.getter
        //가져오는 메서드들은 타입이 각각 있음. 지금은 문자열,정수만 연습하면 될 것 같아요
        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getIntExtra("data2", 0)

        binding.detailResultView.text = "data1: $data1, data2: $data2"

        //후처리는 메인-> 디테일로 갔다면 메인<-디테일로 진행방향이 반대!
        //후처리는 다른 액티비티에서 확인 후처리를 시작하는 함수는 따로 있어요!

        binding.detailButton.setOnClickListener {
            intent.putExtra("resultData","world")
            //현재, 2번재 디테일에서 1번째 메인으로 보내는 역할
            setResult(RESULT_OK, intent)
            //현재 액티비티를 종료
            //카카오톡 프로필 변경 할 때, 갤러리에서 사진은 한번 설정 후
            finish()
        }



    }
}