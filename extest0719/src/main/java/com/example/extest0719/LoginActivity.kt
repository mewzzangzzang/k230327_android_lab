package com.example.extest0719

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.extest0719.databinding.ActivityLoginBinding




class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityLoginBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        /* setContentView(R.layout.activity_login)*/
        setContentView(binding.root)

    binding.loginBtn.setOnClickListener {
        val id: String = binding.inputId.text.toString()
        Toast.makeText(this@LoginActivity, "$id 로 로그인되었습니다", Toast.LENGTH_SHORT).show()

    }

    }

}