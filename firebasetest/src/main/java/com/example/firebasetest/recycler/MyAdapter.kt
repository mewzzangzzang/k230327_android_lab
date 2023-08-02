package com.example.firebasetest.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebasetest.MyApplication
import com.example.firebasetest.databinding.ItemMainBinding
import com.example.firebasetest.model.ItemData

//뷰홀더 -> 바인딩 기법으로 객체 선언.

class MyViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

//어댑터 : 데이터<-> 뷰 를 연결
//주 생성자의 매개변수에 val형식으로 지정, 전역으로 사용
//Context -> 액티비티, 또는 프래그먼트 형식,
//itemList -> 실제 데이터
//리사이클러뷰 구성 클래스들의 공통으로 관련 부모클래스를 상속받음
class MyAdapter(val context: Context, val itemList: MutableList<ItemData>): RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(ItemMainBinding.inflate(layoutInflater))
    }

    //해당 데이터 갯수를 이용해서 출력할 갯수알려줌
    override fun getItemCount(): Int {
        return itemList.size
    }
    //뷰<->데이터 연결 position(인덱스)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = itemList.get(position)

        //뷰 바인딩으로
        holder.binding.run {
            itemEmailView.text=data.email
            itemDateView.text=data.date
            itemContentView.text=data.content
        }

        //스토리지 이미지 다운로드........................
        //MyApp-> 매니페스트에 등록되어서 앱시작시 메모리에 로더 되어있다
        //
        val imgRef = MyApplication.storage.reference.child("images/${data.docId}.jpg")

        //다운로드 실행 완료시 콜백하고 로직 수행
        imgRef.downloadUrl.addOnCompleteListener{ task ->
            if(task.isSuccessful){
                //후처리, 스토리지의 다운로드 URL을 가져옴, 성공시 글라이드로 이미지 로드,into를 통해 해당 결과 이미지 뷰 출력
                //안드로이드에서는 bitmap으로 변환해야하는데 지금은  implementation 'com.firebaseui:firebase-ui-storage:8.0.0'

                //이용해서 한번에 다운, 이미지 바이트 처리, 출력까지 한번에 가능
                Glide.with(context)
                    //이미지 불러오고
                    .load(task.result)
                    //불러온 이미지 결과 뷰 출력
                    .into(holder.binding.itemImageView)
            }
        }
    }
}