package com.example.firebasetest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.firebasetest.databinding.ActivityAddBinding
import com.example.firebasetest.util.dateToString
import java.io.File
import java.util.Date

class AddActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddBinding
    //파일의 경로를 전역으로 설정, 갤러리에서 사진을 선택 후, 해당 경로를 저장
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    //인텐트를 이용해서 후처리(ActivityResultContracts.StartActivityForResult()))
    //사진 선택 후 돌아왔을때 후처리
    val requestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult())
    //it 해당 정보를 담은 객체
    //안드로이드 버전의 http status 200 과 동일한 기능
    {
        if(it.resultCode === android.app.Activity.RESULT_OK){
            //가져온 이미지 처리를 glide를 이용함
            Glide
                .with(getApplicationContext())
                .load(it.data?.data)
                .apply(RequestOptions().override(250, 200))
                .centerCrop()
                //이미지를 결과뷰에 출력
                .into(binding.addImageView)


            //커서 부분은 해당이미지 URI 경로로 위치를 파악하는 구문
            val cursor = contentResolver.query(it.data?.data as Uri,
                arrayOf<String>(MediaStore.Images.Media.DATA), null, null, null);
            cursor?.moveToFirst().let {
                // filePath=cursor?.getString(0) as String -> 경로 주소
                //로그캣으로 출력시 미디어 서버 위치 파악가능
                filePath=cursor?.getString(0) as String
            }
        }
    }

    //액션바의 메뉴 구성옵션
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //해당 액션바의 메뉴의 이벤트 리스너
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId === R.id.menu_add_gallery){
            //Intent.ACTION_PICK -> 갤러리 사진 선택으로 이동
            val intent = Intent(Intent.ACTION_PICK)
            //인텐트 옵션에서 액션문자열은 이미지를 선택 후 URI를 가져옴
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            //인텐트 후처리를 하는 함수
            requestLauncher.launch(intent)
        }else if(item.itemId === R.id.menu_add_save){
            if(binding.addImageView.drawable !== null && binding.addEditView.text.isNotEmpty()){
                //store 에 먼저 데이터를 저장후 document id 값으로 업로드 파일 이름 지정
                saveStore()
            }else {
                Toast.makeText(this, "데이터가 모두 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }

        }
        return super.onOptionsItemSelected(item)
    }
    //....................
    //파이어 베이스 스토어에 저장하는 기능의 함수
    private fun saveStore(){
        //add............................
        //앱 객체에 키, 값의 형태로 데이터를 data 변수에 담았음
        val data = mapOf(
            //인증된 유저의 이메일을 의미
            // 앱이 시작시 인증을 체크하는 MyApplication의 CheckAuth() 확인 함
            "email" to MyApplication.email,
            //뷰에 입력된 값
            "content" to binding.addEditView.text.toString(),
            "date" to dateToString(Date())
        )

        //MyApplication -> db -> 파이어 스토어를 사용하기 위한 객체
        //collection -> 컬렉션을 생성하는 함수 매개변수로 컬렉션 명,(임의로 지정가능함)
        MyApplication.db.collection("news")
            //add 부분에, 임의로 만든 data를 추가
            .add(data)
            //성공시
            .addOnSuccessListener {
                uploadImage(it.id)
            }
            .addOnFailureListener{
                //실패시 실행되는 로직
                Log.d("kkang", "data save error", it)
            }

    }
    //스토리지 기능 중, 업로드
    private fun uploadImage(docId: String){
        //매개변수 부분은 글작성시 docId 라고 문서번호(자동생성)
        //add............................
        //storage 사용을 위한 객체
        val storage = MyApplication.storage
        //스토리지 객체에서 해당 래퍼런스를 이용해서 해당 객체를 바인딩
        val storageRef = storage.reference
        //실제로 imgRef라는 객체로 다운로드를 실행 여기서는 업로드
        //child -> 상위폴더 images하위에 이미지 파일이 저장되는 구조
        val imgRef = storageRef.child("images/${docId}.jpg")

        //후처리 코드에서 선택된 사진의 절대경로를 file라고 하는 참조형 변수에 할당함
        val file = Uri.fromFile(File(filePath))
        //imgRef의 기능중 putFile경로의 파일을 업로드 하는기능
        imgRef.putFile(file)
            .addOnSuccessListener {
                Toast.makeText(this, "save ok..", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener{
                Log.d("kkang", "file save error", it)
            }

    }
}