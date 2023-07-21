package com.example.test10_11_12

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.ProgressDialog.show
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.nfc.Tag
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.test10_11_12.databinding.ActivityMainBinding
import com.example.test10_11_12.databinding.DialogSampleBinding

class MainActivity : AppCompatActivity() {
    //binding 사용 세팅
    lateinit var binding: ActivityMainBinding


    //생명주기 13장, 액티비티 조금 더 상세히 설명할 예정
    //최초 1회에 한번 실행되는 함수, 특징, 매개변수로 번들타입의 객체를 가짐
    //번들타입의 객체 : 메모리 상에 임시 저장하는 파일
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inflate -> 인스턴스화를 한다 시스템상에 등록되어있는 액티비티 -> 객체 생성한다
        binding = ActivityMainBinding.inflate(layoutInflater)
        //화면에 출력하는 역할
        setContentView(binding.root)

        //테스트5, 알림 커스텀 다이얼로그 3, 본인 만든 뷰를 이용한 다이얼로그 창
        binding.button5.setOnClickListener {
            val dialogBinding = DialogSampleBinding.inflate(layoutInflater)
            AlertDialog.Builder(this@MainActivity).run {
                setTitle("button")
                setView(dialogBinding.root)
                show()


            }


            //테스트 5번 알림 다이얼로그2 선택할 수 있는 옵션 (배열)
            binding.button4.setOnClickListener {
                val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
                /*context this 기본 본인 명확하게 명시하려면 @사용*/
                AlertDialog.Builder(this).run {
                    setTitle("items test")
                    setIcon(android.R.drawable.ic_dialog_info)
                    setItems(
                        items,
                        //익명클래스
                        object : DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                Log.d(
                                    "kkang",
                                    "선택한 과일 : ${items[p1]}"
                                )
                                //익명클래스이기때문에 this@로 명시
                                Toast.makeText(
                                    this@MainActivity,
                                    "선택한 과일 ${items[p1]}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    setPositiveButton("닫기", null)
                    show()
                }
            }
            //실험 테스트 6
            binding.button5.setOnClickListener {
                val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
                AlertDialog.Builder(this).run {
                    setTitle("items test")
                    setIcon(android.R.drawable.ic_dialog_info)
                    setMultiChoiceItems(
                        items,
                        booleanArrayOf(true, false, true, false),
                        object : DialogInterface.OnMultiChoiceClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int, p2: Boolean) {
                                Log.d(
                                    "kkang",
                                    "${items[p1]} 이 ${if (p2) "선택되었습니다." else "선택 해제되었습니다."}"
                                )

                            }
                        })
                    setPositiveButton("닫기", null)
                    show()
                }
            }

            //테스트4, 알림 다이얼로그 1,
            //경로
            //https://github.com/lsy3709/AndroidLab/blob/master/test10/src/main/java/com/example/test10/MainActivity282.kt
            binding.button3.setOnClickListener {
                // 경고창 뜬 이유는 임포트시 2개로 나와서, 선택해야해서
                //androidx 를 선택, 호환성 부분때문에
                // 제트팩 라이브러리는 현재 구글에서 추천하는 라이브러리임
                AlertDialog.Builder(this).run {
                    setTitle("test dialog")
                    setIcon(android.R.drawable.ic_dialog_info)
                    setMessage("정말 종료하시겠습니까?")
                    //eventHandler 를 정의하는 함수
                    setPositiveButton("OK", eventHandler)
                    setNegativeButton("Cancel", eventHandler)
                    show()
                }
            }
        }

            //테스트3 날짜 다이얼로그와 시간 다이얼로그 확인
            //경로
            // https://github.com/lsy3709/AndroidLab/blob/master/test10/src/main/java/com/example/test10/MainActivity279.kt

            binding.button1.setOnClickListener {
                DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                        //현재 로그캣에 데이터 날짜 출력
                        //추가로 뷰에 출력
                        binding.dateView.text = "year : $p1, month : ${p2 + 1}, dayOfMonth : $p3"
                        Log.d("kkang", "year : $p1, month : ${p2 + 1}, dayOfMonth : $p3")
                    }
                }, 2023, 6, 21).show()
            }
            binding.button2.setOnClickListener {
                TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {

                        //추가로 선택하면 뷰에 출력하기
                        binding.timeView.text = "현재 시각: $p1 시 , $p2 분"
                        Log.d("kkang", "time : $p1, minute : $p2")
                    }
                }, 15, 0, true).show()
            }
            //테스트 2 , 토스트 추가 옵션 확인
            //showToast()


            //테스트 1 권한관련
            // 경로
            //https://github.com/lsy3709/AndroidLab/blob/master
            // /test10/src/main/java/com/example/test10/MainActivity.kt

            //인텐트 할 때, 후처리 방법에서 더 설명.
            //인텐트 기본 기능
            // 1) 액티비티 간의 전환(화면이동)
            // 2) 화면 간의 이동시, 데이터 전달하는 경우
            // 3) 화면 이동하고, 이동 된 화면에서 작업 후 데이터 원래 화면에 가져오는 역할
            // 예) A 앱, 갤러리 (외부앱) 접근해서, 사진을 선택후, 다시 A앱 가져오는 경우
            // 예) A 앱, 외부앱 접근해서 권한을 획득하고, 다시 A앱으로 돌아오는 경우.
            // 4) 외부(시스템 해당 앱에 접근시) intent- filter -> 명시적으로 설정
            /*val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->

            if (isGranted) {
                Log.d("kkang", "callback, granted..")
            } else {
                Log.d("kkang", "callback, denied..")
            }
        }

        val status= ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION")
        if(status == PackageManager.PERMISSION_GRANTED){
            Log.d("kkang", "granted..")
        }else {
            requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")
        }*/
        }
        //onCreate 마지막


        // 알림의 다이얼로그 부분에서, 예 또는 아니오 선택시
        // 추가 작업을 설정하는 이벤트 핸들러
        val eventHandler = object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                if (p1 == DialogInterface.BUTTON_POSITIVE) {
                    Log.d("kkang", "positive button click")
                    //만약 특정 데이터를 삭제하는 지 물어보고, 예-> 삭제하는 비지니스 로직 수행
                    Toast.makeText(this@MainActivity, "positive button click", Toast.LENGTH_SHORT)
                        .show()
                } else if (p1 == DialogInterface.BUTTON_NEGATIVE) {
                    Log.d("kkang", "negative button click")
                    Toast.makeText(this@MainActivity, "negative button click", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        //함수 정리
        //테스트 2 , 토스트 추가 옵션. 보이고 나서 추가 작업, 사라지고 나서 추가작업
        // 경로 https://github.com/lsy3709/AndroidLab/blob/master/test10/src/main/java/com/example/test10/MainActivity278.kt
        @RequiresApi(Build.VERSION_CODES.R)
        fun showToast() {
            val toast = Toast.makeText(this, "종료하려면 한 번 더 누르세요.", Toast.LENGTH_LONG)
            toast.addCallback(
                object : Toast.Callback() {
                    override fun onToastHidden() {
                        super.onToastHidden()
                        Log.d("kkang", "toast hidden")
                    }

                    override fun onToastShown() {
                        super.onToastShown()
                        Log.d("kkang", "toast shown")
                    }
                })
            toast.show()
        }

    }
