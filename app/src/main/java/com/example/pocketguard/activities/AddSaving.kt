package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.pocketguard.R
import com.example.pocketguard.models.SavingModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.pocketguard.activities.SavingGoalsMain

class AddSaving : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAmount: EditText
    private lateinit var etCategory: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnADD: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_saving)
        etName = findViewById(R.id.etName)
        etAmount = findViewById(R.id.etAmount)
        etCategory = findViewById(R.id.etCategory)
        etDescription = findViewById(R.id.etDescription)
        btnADD = findViewById(R.id.btnADD)

        dbRef = FirebaseDatabase.getInstance().getReference("Saving")

        btnADD.setOnClickListener {
            saveSavingData()

        }
        val myImage = findViewById<ImageView>(R.id.back_arrow)
        myImage.setOnClickListener {
            val intent = Intent(this, SavingGoalsMain::class.java)
            startActivity(intent)
        }
    }

    private fun saveSavingData() {
        //getting values
        val name = etName.text.toString()
        val amount = etAmount.text.toString()
        val category = etCategory.text.toString()
        val description = etDescription.text.toString()

        if (name.isEmpty()) {
            etName.error = "please enter Date"
        }
        if (amount.isEmpty()) {
            etAmount.error = "please enter Amount"
        }
        if (category.isEmpty()) {
            etCategory.error = "please enter Category"
        }
        if (description.isEmpty()) {
            etDescription.error = "please enter Description"

        }
        val saveId = dbRef.push().key!!
        val save = SavingModel(name, amount, category, description)

        dbRef.child(saveId).setValue(save)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etName.text.clear()
                etAmount.text.clear()
                etCategory.text.clear()
                etDescription.text.clear()

            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
