package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.pocketguard.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    //variables
    private lateinit var topAnim: AnimationSet
    private lateinit var bottomAnim: AnimationSet
    private lateinit var image: ImageView
    private lateinit var logoName: TextView
    private lateinit var slogan: TextView
    private val SPLASH_SCREEN_TIMEOUT = 1500L // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation) as AnimationSet
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation) as AnimationSet

        //hooks
        image = findViewById(R.id.MainLogo)
        logoName = findViewById(R.id.textLogo)
        slogan = findViewById(R.id.slogan)

        //set animations
        image.animation = topAnim
        logoName.animation = bottomAnim
        slogan.animation = bottomAnim

        Handler().postDelayed({
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN_TIMEOUT)
    }
}

