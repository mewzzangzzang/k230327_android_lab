package com.example.firebasetest.model

class ItemData {
    //docId 파이어 스토어= NoSQl , <-> RDBMS(MySQL 등 디비를 저장하는 기능이 유사) 이것의 종류
    var docId: String? = null
        //인증시 해당 이메일이 인증객체에 등록
    var email: String? = null
    var content: String? = null
    var date: String? = null
}