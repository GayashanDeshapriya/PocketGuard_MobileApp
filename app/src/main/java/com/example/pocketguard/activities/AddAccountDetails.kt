package com.example.pocketguard.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pocketguard.R
import com.example.pocketguard.databinding.ActivityAddAccountDetailsBinding
import com.example.pocketguard.databinding.ActivityUserProfileBinding
import com.example.pocketguard.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class AddAccountDetails : AppCompatActivity() {

    private lateinit var binding: ActivityAddAccountDetailsBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var storage: FirebaseStorage
    private lateinit var selectedImg: Uri
    private lateinit var dialog: AlertDialog.Builder

    private lateinit var etFullName: EditText
    private lateinit var etUserName: EditText
    private lateinit var etPhone: EditText
    private lateinit var btnSaveData: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAccountDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etFullName = binding.fullName
        etUserName = binding.userName
        etPhone = binding.Phone
        btnSaveData = binding.AddUser

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()

        dbRef = database.getReference("User")

        btnSaveData.setOnClickListener {
            saveUserData()
        }

        dialog = AlertDialog.Builder(this)
            .setMessage("Updating Profile")
            .setCancelable(false)

        binding.userImage.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        val button = binding.back1
        button.setOnClickListener {
            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            if (data.data != null) {
                selectedImg = data.data!!
                binding.userImage.setImageURI(selectedImg)
            }
        }
    }

    private fun saveUserData() {
        //getting values
        val userFullName = etFullName.text.toString()
        val userName = etUserName.text.toString()
        val userPhone = etPhone.text.toString()

        if (userFullName.isEmpty()) {
            etFullName.error = "Please enter your full name"
            return
        }
        if (userName.isEmpty()) {
            etUserName.error = "Please enter user name"
            return
        }
        if (userPhone.isEmpty()) {
            etPhone.error = "Please fill the mobile number"
            return
        }

        val userId = dbRef.push().key!!

        val user = UserModel(userId, userFullName, userName, userPhone)

        dbRef.child(userId).setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etFullName.text.clear()
                etUserName.text.clear()
                etPhone.text.clear()

            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
