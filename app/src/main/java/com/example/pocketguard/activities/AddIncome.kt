package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.pocketguard.R

class AddIncome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income)

        val myImage = findViewById<ImageView>(R.id.back_arrow)
        myImage.setOnClickListener {
            val intent = Intent(this, IncomeTrackingMain::class.java)
            startActivity(intent)
        }


    }
}