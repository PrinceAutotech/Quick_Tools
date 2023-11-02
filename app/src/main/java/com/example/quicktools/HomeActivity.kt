package com.example.quicktools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quicktools.databinding.ActivityHomeBinding
import com.example.quicktools.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generate.setOnClickListener {
            startActivity(Intent(this@HomeActivity, MainActivity::class.java))
        }

        binding.rateUs.setOnClickListener {

        }

        binding.share.setOnClickListener {

        }
    }
}