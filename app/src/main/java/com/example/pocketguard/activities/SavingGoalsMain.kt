package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.pocketguard.R

class SavingGoalsMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saving_goals_main)

        val myImage = findViewById<ImageView>(R.id.back_arrow)
        myImage.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        val viewsavingbutton = findViewById<Button>(R.id.view_saving_button)
        viewsavingbutton.setOnClickListener {
            val intent = Intent(this, ViewSavings::class.java)
            startActivity(intent)
        }

        val addsavingbutton = findViewById<Button>(R.id.Addsaving_button)
        addsavingbutton.setOnClickListener {
            val intent = Intent(this, AddSaving::class.java)
            startActivity(intent)
        }

        val allsavingbutton = findViewById<Button>(R.id.total_saving_button)
        allsavingbutton.setOnClickListener {
            val intent = Intent(this, TotalSaving::class.java)
            startActivity(intent)
        }
    }
}