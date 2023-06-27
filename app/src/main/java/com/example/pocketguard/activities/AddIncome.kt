package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.pocketguard.R
import com.example.pocketguard.models.IncomeModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddIncome : AppCompatActivity() {

    private lateinit var etDate: EditText
    private lateinit var etAmount: EditText
    private lateinit var etCategory: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnSaveData: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income)

        etDate = findViewById(R.id.IncomeDate)
        etAmount = findViewById(R.id.IncomeAmount)
        etCategory = findViewById(R.id.IncomeCategory)
        etDescription = findViewById(R.id.IncomeDescription)
        btnSaveData = findViewById(R.id.btnSave)

        var path = null
        dbRef = FirebaseDatabase.getInstance().getReference("Income")

        btnSaveData.setOnClickListener {
            saveIncomeData()
        }


        val myImage = findViewById<ImageView>(R.id.back_arrow)
        myImage.setOnClickListener {
            val intent = Intent(this, IncomeTrackingMain::class.java)
            startActivity(intent)
        }


    }

    private fun saveIncomeData() {

        //getting values

        val IncomeDate = etDate.text.toString()
        val InocmeAmount = etAmount.text.toString()
        val IncomeCategory = etCategory.text.toString()
        val IncomeDescription = etDescription.text.toString()

        if (IncomeDate.isEmpty()) {
            etDate.error = "Please Enter The Date"
        }

        if (InocmeAmount.isEmpty()) {
            etAmount.error = "Please enter the amount"
            return
        }

        if (IncomeCategory.isEmpty()) {
            etCategory.error = "Please Enter Category"
        }
        if (IncomeDescription.isEmpty()) {
            etDescription.error = "Please Enter Description"

        }

        val IncomeId = dbRef.push().key!!

        var IncomeAmount = null
        val income =
            IncomeModel(IncomeId, IncomeDate, IncomeAmount, IncomeCategory, IncomeDescription)

        dbRef.child(IncomeId).setValue(income)
            .addOnCompleteListener {
                Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_LONG).show()

                etDate.text.clear()
                etAmount.text.clear()
                etCategory.text.clear()
                etDescription.text.clear()

            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
