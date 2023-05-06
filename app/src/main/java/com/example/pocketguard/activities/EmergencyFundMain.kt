package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.pocketguard.R

class EmergencyFundMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency_fund_main)

        val myImage = findViewById<ImageView>(R.id.back_arrow)
        myImage.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        val newFundButton = findViewById<Button>(R.id.button_create)
        newFundButton.setOnClickListener {
            val intent = Intent(this, CreateNewFund::class.java)
            startActivity(intent)
        }

        val viewFundButton = findViewById<Button>(R.id.button_view)
        viewFundButton.setOnClickListener {
            val intent = Intent(this, AllExistingFunds::class.java)
            startActivity(intent)
        }

        val availableFundButton = findViewById<Button>(R.id.button_available)
        availableFundButton.setOnClickListener {
            val intent = Intent(this, AvailableFunds::class.java)
            startActivity(intent)
        }
    }
}