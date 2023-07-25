package com.example.test13_16_17_18.test13

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager.OnActivityStopListener
import android.util.Log
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain414Binding

//test13/src/main/java/com/example/test13/MainActivity414.kt
class MainActivity414 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain414Binding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("life","onCreate 호출")

        //ACTION_EDIT 시스템이 사용하는 액션 문자열이 아님
        binding.button1.setOnClickListener {
            val intent = Intent()
            //intent.action = "ACTION_EDIT"
            intent.action =Intent.ACTION_VIEW
            intent.data = Uri.parse("http://www.google.com")
            startActivity(intent)
        }

        // 액션 문자열: 시스템에서 제공하는 Intent.ACTION_VIEW
        // 웹주소, 웹 브라우저 연결, 위도, 경도 값이면, 지도로 알아서 연결 합니다
        binding.button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.7749,127.4194"))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
    }
 /*   override fun OnStart(){
        Log.d("life","onStart 호출")
        super.onStart()
    }

    override fun OnResume(){
        Log.d("life","OnResume 호출")
        super.onResume()

    }
    override fun OnPause(){
        Log.d("life","OnPause 호출")
        super.onPause()
    }
    override fun OnStop(){
        Log.d("life","OnStop 호출")
        super.onStop()

    }
    override fun OnDestroy(){
        Log.d("life","OnDestroy 호출")

    }*/
}
