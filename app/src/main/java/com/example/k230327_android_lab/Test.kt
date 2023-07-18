package com.example.k230327_android_lab



val data5 : Int = 1



val data4 : Int by lazy {
    println("in lazy....")
    10
}


lateinit var name2: String
//  기본 문법에 대한 설명은 참고 및, 한번정도
//  직접 코드로 작성, 오류 확인.
//  실제 작업은 IDE에서 도움받기

val name:String = "zzz"



fun main(){

    //Array, 선언, setter, getter
    //선언
    val data1: Array<Int> = Array(3,{0})
    //setter
    data1[0] = 10
    data1[1] = 20
    //getter
    println("""
        data1의 size : ${data1.size}
        data1의 요소 :${data1[0]}, ${data1.get(1)}, ${data1[2]}
    """.trimIndent())





    var data6: Int? = 2
    data6 = null
    // 널 허용할 수 있는 연산자가 있을 때만.



    //data5. :모든 타입이 객체이기 때문에, 닷 연산자를 이용해서
    //멤버에 다 접근 가능함.
    //println("in main 순서1")
    //println("data4 사용 :("{data4} + 10)
    //println("data4 사용 :$data4")
    //val name:String
    //val name:String ="main 안의 name변수"
    //println("이름은: $name") //jsp EL표기법에서 ${}변수사용
    //$변수, 사용하면 된다~
    //println("hell world")
}

class Test {
}