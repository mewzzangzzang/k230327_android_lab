package com.example.extest0719.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.extest0719.databinding.FragmentOneBinding



class OneFragment : Fragment() {


    class OneFragment : Fragment() {
        //현재 FragTest 액티비티에 출력예정
        // 프래그 먼트-> 마치 액티비티와 비슷하게 동작을 함
        // 예) 생명주기, 뷰바인딩 등
        lateinit var binding: FragmentOneBinding
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            /*  return inflater.inflate(R.layout.fragment_one, container, false)*/
            binding = FragmentOneBinding.inflate(inflater, container, false)
            return binding.root
        }


    }
}