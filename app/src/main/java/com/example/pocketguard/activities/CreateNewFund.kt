package com.example.pocketguard.activities

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.pocketguard.models.FundModel
import com.example.pocketguard.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CreateNewFund : AppCompatActivity() {
    private lateinit var etFundName: EditText
    private lateinit var etAmount: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnSaveData: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_fund)

        etFundName = findViewById(R.id.fundname)
        etAmount = findViewById(R.id.amount)
        etDescription = findViewById(R.id.description)
        btnSaveData = findViewById(R.id.fundsave)

        dbRef = FirebaseDatabase.getInstance().getReference("Funds")

        btnSaveData.setOnClickListener {
            saveFundData()
        }

        val myImage = findViewById<ImageView>(R.id.back_arrow)
        myImage.setOnClickListener {
            val intent = Intent(this, EmergencyFundMain::class.java)
            startActivity(intent)
        }
    }

    private fun saveFundData() {
        //getting values
        val fundName = etFundName.text.toString()
        val fundAmount = etAmount.text.toString()
        val fundDescription = etDescription.text.toString()

        if (fundName.isEmpty()) {
            etFundName.error = "Please enter the fund name"
            return
        }
        if (fundAmount.isEmpty()) {
            etAmount.error = "Please enter the amount"
            return
        }
        if (fundDescription.isEmpty()) {
            etDescription.error = "Please fill the description"
            return
        }

        val fundId = dbRef.push().key!!

        val fund = FundModel(fundId, fundName, fundAmount, fundDescription)

        dbRef.child(fundId).setValue(fund)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etFundName.text.clear()
                etAmount.text.clear()
                etDescription.text.clear()
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
