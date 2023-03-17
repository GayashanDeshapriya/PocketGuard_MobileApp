package com.example.pocketguard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {



    //variables
    private lateinit var topAnim: AnimationSet
    private lateinit var bottomAnim: AnimationSet
    private lateinit var image: ImageView
    private lateinit var logoName: TextView
    private lateinit var slogan: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation) as AnimationSet
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation) as AnimationSet

        //hooks
        image = findViewById(R.id.Mainlogo)
        logoName = findViewById(R.id.textlogo)
        slogan = findViewById(R.id.slogan)

        //set animations
        image.animation = topAnim
        logoName.animation = bottomAnim
        slogan.animation = bottomAnim

    }
}

