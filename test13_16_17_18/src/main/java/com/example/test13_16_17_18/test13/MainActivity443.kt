package com.example.test13_16_17_18.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain443Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

//test13/src/main/java/com/example/test13/MainActivity443.kt

class MainActivity443 : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val binding = ActivityMain443Binding.inflate(layoutInflater)
            setContentView(binding.root)
            // 방법1) 핸들러 방식
            // 핸들러,메세지,스레드 임포트
            binding.button.setOnClickListener {

                // 무거운 연산을 테스트 ,cpu를 사용하는 것 5초룰
            var sum = 0L
            var time = measureTimeMillis {
                for(i in 1..2_000_000_000){
                    sum += i
                }
            }
            Log.d("kkang","time : $time")
                Log.d("kkang","sum: $sum")
            binding.resultView.text = "sum : $sum"

//            val handler=object: Handler(){
//                override fun handleMessage(msg: Message) {
//                    super.handleMessage(msg)
//                    binding.resultView.text = "sum : ${msg.arg1}"
//                }
//            }
//
//            thread {
//                var sum = 0L
//                var time = measureTimeMillis {
//                    for (i in 1..1_000_000_000) {
//                        sum += i
//                    }
//                    val message = Message()
//                    message.arg1=sum.toInt()
//                    handler.sendMessage(message)
//                }
//                Log.d("kkang", "time : $time")
//            }
                //코틀린x 임포트함
                //방법 2)  코루틴
                //코루틴 데이터의 결과를 메세지로 전달하는 도구
                val channel = Channel<Int>()

                //코루틴, 스코프(클래스)를 구성해서
                //메인 스코프 , 백그라운드 스코프
                // 백그라운드에서 1)무거운 연산(CPU 사용하는 작업) ->Dispatchers.Default
                // 2) 네트워크, 파일 IO Dispatchers.IO
                // 3)메인작업 ->Dispatchers.Main

                //CoroutineScope ->개발자가 임의로 만든 스코프
                val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
                backgroundScope.launch {
                    var sum = 0L
                    var time = measureTimeMillis {
                        for (i in 1..2_000_000_000) {
                            sum += i
                        }
                    }
                    Log.d("kkang", "time : $time")

                    //메인에 전달
                    channel.send(sum.toInt())
                }

                //메인 스코프
                //GlobalScope : 시스템의 스코프를 사용
                val mainScope= GlobalScope.launch(Dispatchers.Main) {
                    //channel 도구를 이용해서 전달된, 데이터를 가져오는 작업
                    channel.consumeEach {
                        binding.resultView.text = "sum : $it"
                    }
                } //코루틴 닫는 부분


            } //버튼의 클릭 리서너 이벤트 핸들러 닫는 부분
        }
    }