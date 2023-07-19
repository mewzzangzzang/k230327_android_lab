package com.example.k230327_android_lab

import java.util.Scanner


class Extest0718{


}
class LoginTest {
    companion object{
    //클래스 함수 -> 클래스명,함수: (static 바이브)
        fun login(user: User){
            if(user.id.equals("admin")&& user.pw.equals("1234")){
                println("로그인 성공")
            }
        }
    }
}
//data class 임시 DTO(=VO), 데이터 전달 하기위한 객체
//클래스에 만들어서 사용시, 주 생성자를 이용하되,
//매개변수 부분에 변수 앞에 val라는 키워드를 사용해서 전역으로 사용하기
data class User(val id: String, val pw:String){

}




//코틀린 : 모든 타입 객체
// 변수 선언, var(val)변수명: 타입 = 해당값 할당 형식.


fun main(){
    val scanner:Scanner = Scanner(System.`in`)


    println("ID :")
    val id= scanner.nextLine()
    //println("$id")

    println("PW :")
    val pw= scanner.nextLine()
    //println("$pw")

    val user: User = User(id, pw)
    //println("user 확인 $user")

    LoginTest.login(user)
}