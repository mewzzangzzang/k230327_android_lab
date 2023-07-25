package com.example.test10_11_12.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test10_11_12.R
import com.example.test10_11_12.databinding.FragmentThreeBinding


class ThreeFragment : Fragment() {
    //경로
    //https://github.com/lsy3709/AndroidLab/blob/master/test11/src/main/java/com/example/test11/ThreeFragment.kt
    lateinit var binding: FragmentThreeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentThreeBinding.inflate(layoutInflater)
        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_three, container, false)
    }


}