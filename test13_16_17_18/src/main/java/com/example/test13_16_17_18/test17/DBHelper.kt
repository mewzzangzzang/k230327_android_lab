package com.example.test13_16_17_18.test17

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


//ch17_database/src/main/java/com/example/ch17_database/DBHelper.kt
//안드로이드에서 기본으로 SQLlite를 추상화한 클래스
//사용의 편의성 생각
// 부모클래스 부분의 상속부분 SQLiteOpenHelper
// 2번째 매개변수 부분이 스키마 (원하는 데이터 베이스를 말함)
class DBHelper(context: Context): SQLiteOpenHelper(context, "testdb", null, 1) {
    //최초 1회 호출, 테이블생성
    //testdb의 저장위치
    //에뮬 익스플로러 -> data -> data->패키지명->databases->testdb에 있음
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table TODO_TB (" +
                "_id integer primary key autoincrement," +
                "todo not null)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}