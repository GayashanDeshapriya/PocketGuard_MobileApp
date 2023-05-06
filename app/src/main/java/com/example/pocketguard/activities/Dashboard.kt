package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.pocketguard.R

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val myImage = findViewById<ImageView>(R.id.LogoImage)
        myImage.setOnClickListener {
            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)
        }

        // Expense Card
        val cardView1 = findViewById<CardView>(R.id.expense_tracking)
        cardView1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.blue_800))
        cardView1.setOnClickListener {
            val intent = Intent(this, ExpenseTrackingMain::class.java)
            startActivity(intent)
        }


        // Income tracking Card
        val cardView2 = findViewById<CardView>(R.id.income_tracking)
        cardView2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.blue_800))
        cardView2.setOnClickListener {
            val intent = Intent(this, IncomeTrackingMain::class.java)
            startActivity(intent)
        }

        // saving Card
        val cardView3 = findViewById<CardView>(R.id.saving_goals)
        cardView3.setCardBackgroundColor(ContextCompat.getColor(this, R.color.blue_800))
        cardView3.setOnClickListener {
            val intent = Intent(this, SavingGoalsMain::class.java)
            startActivity(intent)
        }

        // Emergency Card
        val cardView4 = findViewById<CardView>(R.id.emergency_fund)
        cardView4.setCardBackgroundColor(ContextCompat.getColor(this, R.color.blue_800))
        cardView4.setOnClickListener {
            val intent = Intent(this, EmergencyFundMain::class.java)
            startActivity(intent)
        }

    }
}
