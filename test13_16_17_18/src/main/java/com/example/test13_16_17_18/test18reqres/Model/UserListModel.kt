package com.example.test13_16_17_18.test18reqres.Model

import com.google.gson.annotations.SerializedName

data class UserListModel (
    //실제 사용하는 오픈 api에서 사용하라 파라미터의 모델타입이 명세서에 적혀있다
    //그래서 이름을 그대로 사용해야함
    //공공데이터의 모델링의 구조를 잘 파악 해야함
    var page: String,
    @SerializedName("per_page")
    var perPage: String,
    var total: String,
    @SerializedName("total_pages")
    var totalPages: String,

    // 실제 데이터 모델를 리스트 타입으로 제너릭으로해서 명시한다.
    var data: List<UserModel>?
)
