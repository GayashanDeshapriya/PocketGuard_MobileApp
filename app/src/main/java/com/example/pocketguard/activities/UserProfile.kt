package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.pocketguard.R
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*

class UserProfile : AppCompatActivity() {

    private lateinit var tvFullName: TextView
    private lateinit var tvUserName: TextView
    private lateinit var tvUserPhone: TextView

    private lateinit var btnUpdate: Button

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        FirebaseApp.initializeApp(this)
        databaseReference = FirebaseDatabase.getInstance().reference.child("User").child("user1")

        initView()
        setValuesToViews()

        val button = findViewById<Button>(R.id.back1)
        button.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.create)
        button2.setOnClickListener {
            val intent = Intent(this, AddAccountDetails::class.java)
            startActivity(intent)
        }


    }

    private fun initView() {

        tvFullName = findViewById(R.id.tvFullName)
        tvUserName = findViewById(R.id.tvUserName)
        tvUserPhone = findViewById(R.id.tvUserPhone)


        btnUpdate = findViewById(R.id.btnUpdate)

    }

    private fun setValuesToViews() {

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                tvFullName.text = dataSnapshot.child("fullName").value.toString()
                tvUserName.text = dataSnapshot.child("userName").value.toString()
                tvUserPhone.text = dataSnapshot.child("userPhone").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
    }
}
