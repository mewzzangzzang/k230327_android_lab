package com.example.test10_11_12.test12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test10_11_12.R
//test11->MainActivity 사용중이라 뷰가main2
//https://github.com/lsy3709/AndroidLab/blob/master/test12/src/main/java/com/example/test12/MainActivity.kt

//뷰라서 실제 작업은 여기서
//경로
//test12/src/main/res/layout/activity_main.xml
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
}