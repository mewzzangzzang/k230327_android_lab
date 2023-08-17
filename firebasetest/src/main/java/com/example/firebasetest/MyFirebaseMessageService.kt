package com.example.firebasetest

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

//서비스 컴포넌트 (백그라운드에서 동작기능)
//백그라운드에서 수행하니까 화면은 x 주로 알림기능 (액티비티 몫이라 관련없음)
class MyFirebaseMessageService : FirebaseMessagingService() {

    //새로운 토큰 발급, 이용해서 유저 구분
    //원격지에서 상대방에서 보내고 알림기능시 필요
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("kkang","fcm token....$p0")
    }

    //FCM 메세지를 받음
    //제목과 내용으로 구분
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.d("kkang", "fcm message...... ${p0.notification}")
        Log.d("kkang", "fcm message...... ${p0.data}")
        //보통은 받은 다음에 내부에 notification로 설정해서 전달
        //외부에서 전달을 해도 내부 notification 이용해서 알림을 전달 - 내부에서 notification 설정하고, 전달
    }
}