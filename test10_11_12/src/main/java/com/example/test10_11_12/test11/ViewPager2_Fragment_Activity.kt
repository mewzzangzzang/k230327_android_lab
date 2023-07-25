package com.example.test10_11_12.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test10_11_12.databinding.ActivityViewPager2FragmentBinding
import com.example.test10_11_12.fragment.OneFragment
import com.example.test10_11_12.fragment.ThreeFragment
import com.example.test10_11_12.fragment.TwoFragment

class ViewPager2_Fragment_Activity : AppCompatActivity() {
    lateinit var binding: ActivityViewPager2FragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityViewPager2FragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뷰페이저 2 구현 - 프레그먼트 방식으로 연결
        //프래그 먼트 구현 방법 1) xml 태그 출력 2)액티비티 코드상에서 출력
        //경로 https://github.com/lsy3709/AndroidLab/blob/master/test11/src/main/res/layout/activity_main355.xml
        //layout 뷰 참고
        //경로 https://github.com/lsy3709/AndroidLab/blob/master/test11/src/main/java/com/example/test11/MainActivity355.kt
        val datas = mutableListOf<String>()
        for(i in 1..3){
            datas.add("Item $i")
        }
        //프레그먼트 방식으로 설정+적용
        val adapter= MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter
    }

    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments: List<Fragment>
        init {
            fragments= listOf(OneFragment(), TwoFragment(), ThreeFragment())
            Log.d("kkang" ,"fragments size : ${fragments.size}")
        }
        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]

    }
}