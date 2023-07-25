package com.example.test10_11_12.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.test10_11_12.R
import com.example.test10_11_12.databinding.ActivityFragTestBinding
import com.example.test10_11_12.fragment.OneFragment

class FragTestActivity : AppCompatActivity() {
    lateinit var binding:ActivityFragTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityFragTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //프래그먼트 기본 테스트,
        // 방법1)xml로 구성하는 방법 2) 코드로 구성하는방법
        //경로
        //test11/src/main/res/layout/activity_main337.xml
        // 출력방식이 액티비티에 name으로 해당 프래그먼트를 지정해서, 출력

        //방법2)
        // 액티비티 코드에서 해당 프래그먼트를 출력
        //프래그먼트 또 만들기
        //경로
        //https://github.com/lsy3709/AndroidLab/blob/master/test11/src/main/java/com/example/test11/MainActivity338.kt
        //TwoFragment.kt 만들어서 구현중
        //val fragment 만 교체해서 활용예정 재사용
        // 제약조건때문에 레이아웃 교체해주기 (android->Linear)



        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = OneFragment()
        transaction.add(R.id.fragment_content, fragment)
        //백스텝을  설정시 커밋 이전 함수에서 설정하면 된다
        // 뒤로가기 버튼을 클릭시 해당 액티비티를 종료하는게 아니라
        // 메모리상에 있는 프래그먼트를 재사용 합니다
        // 옵션 설정이 없으면 , 프래그먼트 소멸후, 다시 재생성 및 시작을 해서,
        // 자원소모가 발생
        transaction.addToBackStack(null)
        transaction.commit()
    }
}