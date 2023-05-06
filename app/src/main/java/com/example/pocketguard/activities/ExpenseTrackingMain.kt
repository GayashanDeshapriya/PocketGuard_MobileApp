package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.pocketguard.R

class ExpenseTrackingMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_tracking_main)

        val myImage = findViewById<ImageView>(R.id.back_arrow)
        myImage.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        val addExpenseButton = findViewById<Button>(R.id.AddExpense)
        addExpenseButton.setOnClickListener {
            val intent = Intent(this, AddExpense::class.java)
            startActivity(intent)
        }

        val viewExpenseButton = findViewById<Button>(R.id.ViewExpense)
        viewExpenseButton.setOnClickListener {
            val intent = Intent(this, ViewAllExpense::class.java)
            startActivity(intent)
        }

        val allExpenseButton = findViewById<Button>(R.id.TotalExpense)
        allExpenseButton.setOnClickListener {
            val intent = Intent(this, TotalExpense::class.java)
            startActivity(intent)
        }
    }
}