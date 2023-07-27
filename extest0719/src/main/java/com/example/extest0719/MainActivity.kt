package com.example.extest0719

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.extest0719.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        /* setContentView(R.layout.activity_main)*/
        setContentView(binding.root)

        binding.signBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, SignActivity::class.java)
            startActivity(intent)


        }
        binding.loginBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)

        }
    }



}