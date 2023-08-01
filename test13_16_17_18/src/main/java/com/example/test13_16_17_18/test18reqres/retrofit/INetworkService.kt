package com.example.test13_16_17_18.test18reqres.retrofit

import com.example.test13_16_17_18.test18reqres.Model.UserListModel
import com.example.test13_16_17_18.test18reqres.Model.UserModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
//경로
//https://github.com/lsy3709/K230201AndroidLab/blob/master/test18/src/main/java/com/example/test18/retrofit/INetworkService.kt

//레트로핏 구조 특성상, 전달을 인터페이스로 전달을 함
//인터페이스 -> 추상 함수들의 모음집
interface INetworkService {
    //실제 백엔드 서버와 주소를 하기위한 URL 주소
    //baseUrl( https://reqres.in/) 보통 ex)localhost8080
    //레스트 컨트롤러 정의되어있어요
    @GET("api/users")
    // baseurl : https://reqres.in/
    //https://reqres.in/api/users?page=2
    //예를 들어서 doGetUserList("2")

    //레트로핏2의 함수의 리턴 타입은 Call타입-> 제너릭으로 설정이 된 모델의 구조를 파악
    fun doGetUserList(@Query("page") page: String): Call<UserListModel>
    @GET
    fun getAvatarImage(@Url url: String): Call<ResponseBody>

    //    @GET("users/list?sort=desc")
    @GET("api/users/2")
    fun test1(): Call<UserModel>
}