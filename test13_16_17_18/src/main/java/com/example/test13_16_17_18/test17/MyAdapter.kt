package com.example.test13_16_17_18.test17

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test13_16_17_18.databinding.ItemRecyclerviewBinding

//ch17_database/src/main/java/com/example/ch17_database/MyAdapter.kt
//리사이클러 뷰의 목록요소의 뷰 부분 복사
//ch17_database/src/main/res/layout/item_recyclerview.xml
//이미지 drawable todojpg 이미지 다운했어요

class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: MutableList<String>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding
        binding.itemData.text= datas!![position]
    }
}