package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.pocketguard.R

class IncomeTrackingMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_tracking_main)

        val myImage = findViewById<ImageView>(R.id.back_arrow)
        myImage.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        val addIncomeButton = findViewById<Button>(R.id.addincome)
        addIncomeButton.setOnClickListener {
            val intent = Intent(this, AddIncome::class.java)
            startActivity(intent)
        }

        val viewIncomeButton = findViewById<Button>(R.id.incomebtn2)
        viewIncomeButton.setOnClickListener {
            val intent = Intent(this, ViewIncome::class.java)
            startActivity(intent)
        }

        val allIncomeButton = findViewById<Button>(R.id.incomebtn3)
        allIncomeButton.setOnClickListener {
            val intent = Intent(this, TotalIncome::class.java)
            startActivity(intent)
        }


    }
}