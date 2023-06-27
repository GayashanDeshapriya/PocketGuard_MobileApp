package com.example.pocketguard.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pocketguard.R
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase



class TotalExpense : AppCompatActivity() {
    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("Expenses")
    var dataKey = "expenseAmount"
    var total = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.pocketguard.R.layout.activity_total_expense)

        var tot = findViewById<TextView>(com.example.pocketguard.R.id.totalTextView)

        val myImage = findViewById<ImageView>(com.example.pocketguard.R.id.back_arrow)
        myImage.setOnClickListener {
            val intent = Intent(this, ExpenseTrackingMain::class.java)
            startActivity(intent)
        }

        myRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                // Get the value of the data item
                val value = snapshot.child(dataKey).value

                // Add the value to the running total
                if (value is Int) {
                    total += value.toInt()
                }

                // Log the total so far
                Log.d("Firebase", "Total: $total")

                val totalTextView = findViewById<TextView>(R.id.totalTextView)
                totalTextView.text = total.toString()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                // Handle child changed events
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                // Handle child removed events
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                // Handle child moved events
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors
                Log.e("Firebase", "Failed to read value.", error.toException())
            }
        })
    }
}
