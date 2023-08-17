package com.example.firebasetest

import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class MyApplication: MultiDexApplication() {
    //dex 코틀린으로 컴파일시 추가된 파일구조,
    //java -> class -> dex


    //클래스 변수
    companion object {
        //선언만 되어있어서 초기화로직 반드시 필요
        //ㄴonCreate로 초기화
        lateinit var auth: FirebaseAuth
        var email: String? = null
        lateinit var db: FirebaseFirestore
        lateinit var storage: FirebaseStorage


        fun checkAuth(): Boolean {
            //파이어 베이스 인증의 기능을 이용하는 로직,
            //파이어베이스로 인증된 사용자를 가르키는데 로그인 후-> 인증된 유저
            //이메일로 유효성 체크
            var currentUser = auth.currentUser
            return currentUser?.let {
                email = currentUser.email
                currentUser.isEmailVerified
            } ?: let {
                false
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        storage = Firebase.storage
    }
}