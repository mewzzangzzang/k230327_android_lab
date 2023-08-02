package com.example.firebasetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasetest.databinding.ActivityMainBinding
import com.example.firebasetest.model.ItemData
import com.example.firebasetest.recycler.MyAdapter
import com.example.firebasetest.util.myCheckPermission

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //미디어 서버 접근 권한 여부 확인 함수
        //매 액티비티에서 사용 가능하게 파일분리(리팩토링) MyUtil
        myCheckPermission(this)
        //플로팅 리스너
        binding.addFab.setOnClickListener {
            //인증성공시
            if(MyApplication.checkAuth()){
                startActivity(Intent(this, AddActivity::class.java))
                //실패시
            }else {
                Toast.makeText(this, "인증진행해주세요..",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        if(!MyApplication.checkAuth()){
            binding.logoutTextView.visibility= View.VISIBLE
            binding.mainRecyclerView.visibility= View.GONE
        }else {
            binding.logoutTextView.visibility= View.GONE
            binding.mainRecyclerView.visibility= View.VISIBLE
            makeRecyclerView()
        }
    }

    //메인화면 액션바에 메뉴 옵션 코드
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, AuthActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    //리사이클러뷰를 출력하는 함수를 만들어서
    private fun makeRecyclerView(){
        //파이어베이스 스토어의 컬렉션 객체를 선택하는 함수
        MyApplication.db.collection("news")
            //잘 가져오면 처리하는 함수
            //news라는 컬렉션(테이블)에서 모든 문서를 가져옴
            .get()
            //빈 리스트 생성후 임시로 저장할 공간 DTO, ItemData
            .addOnSuccessListener {result ->
                //반복문으로 받아온 문서를 하나씩 꺼내서 작업
                val itemList = mutableListOf<ItemData>()
                for(document in result){
                    //document.toObject 해당 모델 클래스에 자동으로 매핑
                    //받아온 데이터를 자동변환(매핑)
                    val item = document.toObject(ItemData::class.java)
                    //문서의 고유 아이디를 docId에 해당
                    item.docId=document.id
                    //각 ItemData 형으로 리스트에 담기
                    itemList.add(item)
                }
                //뷰 형식
                binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
                //파이어베이스에서 받아온 itemList(일반 데이터 ,문자형)
                binding.mainRecyclerView.adapter = MyAdapter(this, itemList)
            }
            .addOnFailureListener{exception ->
                Log.d("kkang", "error.. getting document..", exception)
                Toast.makeText(this, "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }
    }
}