package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.pocketguard.R

class AvailableFunds : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_funds)

        val myImage = findViewById<ImageView>(R.id.back_arrow)
        myImage.setOnClickListener {
            val intent = Intent(this, EmergencyFundMain::class.java)
            startActivity(intent)
        }
    }
}