package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pocketguard.R

class UserProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val button = findViewById<Button>(R.id.back1)
        button.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
}