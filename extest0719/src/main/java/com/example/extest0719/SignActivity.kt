package com.example.extest0719

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.extest0719.databinding.ActivitySignBinding

class SignActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignBinding.inflate(layoutInflater)

        RoomDatabase
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val pw: String = binding.inputPw.text.toString()
        val pw2: String = binding.inputPw2.text.toString()

        binding.signBtn.setOnClickListener {
            val intent = Intent(this@SignActivity, SignActivity::class.java)
            startActivity(intent)

            if (pw == pw2) {
                Toast.makeText(this@SignActivity, "가입완료", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@SignActivity, "가입실패", Toast.LENGTH_SHORT).show()
            }




            /* binding.signBtn.setOnClickListener {
            val intent = Intent(this@SignActivity, SignActivity::class.java)
            startActivity(intent)

        }*/


        }

    }
}

