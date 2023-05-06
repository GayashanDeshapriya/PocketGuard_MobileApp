package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.pocketguard.models.ExpenseModel
import com.example.pocketguard.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddExpense : AppCompatActivity() {

    private lateinit var etdate: EditText
    private lateinit var etAmount: EditText
    private lateinit var etCategory: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnSaveData: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        etdate = findViewById(R.id.expenseName)
        etAmount = findViewById(R.id.expense_amount)
        etCategory = findViewById(R.id.expense_category)
        etDescription = findViewById(R.id.expense_description)
        btnSaveData = findViewById(R.id.AddExpense)

        dbRef = FirebaseDatabase.getInstance().getReference("Expenses")

        btnSaveData.setOnClickListener {
            saveExpenseData()
        }

        val myImage = findViewById<ImageView>(R.id.back_arrow)
        myImage.setOnClickListener {
            val intent = Intent(this, ExpenseTrackingMain::class.java)
            startActivity(intent)
        }


    }


    private fun saveExpenseData() {
        //getting values
        val expenseDate = etdate.text.toString()
        val expenseAmount = etAmount.text.toString()
        val expenseCategory = etCategory.text.toString()
        val expenseDescription = etDescription.text.toString()

        if (expenseDate.isEmpty()) {
            etdate.error = "Please enter the fund name"
            return
        }
        if (expenseAmount.isEmpty()) {
            etAmount.error = "Please enter the amount"
            return
        }
        if (expenseDescription.isEmpty()) {
            etDescription.error = "Please fill the description"
            return
        }

        val expenseId = dbRef.push().key!!

        val expense = ExpenseModel(expenseId, expenseDate, expenseAmount, expenseCategory,expenseDescription)

        dbRef.child(expenseId).setValue(expense)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etdate.text.clear()
                etAmount.text.clear()
                etCategory.text.clear()
                etDescription.text.clear()
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

}