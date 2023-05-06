package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import com.example.pocketguard.R

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)


        val goButton = findViewById<Button>(R.id.go)

        goButton.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        val newUserButton = findViewById<Button>(R.id.new_user)
        newUserButton.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
}
