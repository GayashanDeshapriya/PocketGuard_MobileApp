package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pocketguard.R

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val createAccountbutton = findViewById<Button>(R.id.create_acc)
        createAccountbutton.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
}