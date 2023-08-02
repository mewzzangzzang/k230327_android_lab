package com.example.firebasetest.util

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Date

//해당 코틀린 파일인데, 함수를 리팩토링, 파일로 따로 분리
//자주 사용하는 기능을 분리
//매개 변수에 타입이 액티비티로 사용되는 부분확인
fun myCheckPermission(activity: AppCompatActivity) {

    //인텐트, 후처리 하는 함수
    //권한 여부확인 후 후처리하는 함수(액티비티 설명 참고)
    val requestPermissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(activity, "권한 승인", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "권한 거부", Toast.LENGTH_SHORT).show()
        }
    }

    //권한 체크 부분에서 변경, Manifest 변경함 이미지 접근 권한
    if (ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.READ_MEDIA_IMAGES
        ) !== PackageManager.PERMISSION_GRANTED
    ) {
        requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
    }
}

fun dateToString(date: Date): String {
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.format(date)
}

//이미지를 압축하는 기능이 없어서 압축하려는 함수를 가져와서 선언해서 해당 액티비티에서 실행