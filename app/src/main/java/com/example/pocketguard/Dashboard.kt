package com.example.pocketguard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.cardview.widget.CardView

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val myImage = findViewById<ImageView>(R.id.LogoImage)
        myImage.setOnClickListener {
            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)
        }

        // Budget Manage Card
        val cardView1 = findViewById<CardView>(R.id.budget_manage_card)
        cardView1.setOnClickListener {
            val intent = Intent(this, BudgetManage_Main ::class.java)
            startActivity(intent)
        }

        // Expense Card
        val cardView2 = findViewById<CardView>(R.id.expense_tracking)
        cardView2.setOnClickListener {
            val intent = Intent(this, ExpenseTrackin_Main ::class.java)
            startActivity(intent)
        }

        // saving Card
        val cardView3 = findViewById<CardView>(R.id.saving_goals)
        cardView3.setOnClickListener {
            val intent = Intent(this, SavingGoalsMain ::class.java)
            startActivity(intent)
        }

        // Emergency Card
        val cardView4 = findViewById<CardView>(R.id.emergency_fund)
        cardView4.setOnClickListener {
            val intent = Intent(this, EmergencyFundMain ::class.java)
            startActivity(intent)
        }

    }
}