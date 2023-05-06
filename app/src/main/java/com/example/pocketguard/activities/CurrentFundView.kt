package com.example.pocketguard.activities

import android.content.ClipDescription
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.pocketguard.R
import com.example.pocketguard.models.FundModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class CurrentFundView : AppCompatActivity() {

    private lateinit var tvFundId: TextView
    private lateinit var tvFundName: TextView
    private lateinit var tvFundAmount: TextView
    private lateinit var tvFundDescription: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_fund_view)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("fundId").toString(),
                intent.getStringExtra("fundName").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("fundId").toString()
            )
        }




        val backButton = findViewById<Button>(R.id.back1)
        backButton.setOnClickListener {
            val intent = Intent(this, AllExistingFunds::class.java)
            startActivity(intent)
        }
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Funds").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Funds data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, CurrentFundView::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }



    private fun initView() {
        tvFundId = findViewById(R.id.tvFundId)
        tvFundName = findViewById(R.id.tvFundName)
        tvFundAmount = findViewById(R.id.tvFundAmount)
        tvFundDescription = findViewById(R.id.tvFundDescription)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvFundId.text = intent.getStringExtra("fundId")
        tvFundName.text = intent.getStringExtra("fundName")
        tvFundAmount.text = intent.getStringExtra("fundAmount")
        tvFundDescription.text = intent.getStringExtra("fundDescription")
    }

    private fun openUpdateDialog(
        fundId: String,
        fundName: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_funds_details, null)

        mDialog.setView(mDialogView)
        val etFundName =mDialogView.findViewById<EditText>(R.id.etFundName)
        val etFundAmount =mDialogView.findViewById<EditText>(R.id.etFundAmount)
        val etFundDescription =mDialogView.findViewById<EditText>(R.id.etFundDescription)
        val btnUpdate =mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etFundName.setText(intent.getStringExtra("fundName").toString())
        etFundAmount.setText(intent.getStringExtra("fundAmount").toString())
        etFundDescription.setText(intent.getStringExtra("fundDescription").toString())

        mDialog.setTitle("updating $fundName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdate.setOnClickListener{
            updateFundData(
                fundId,
                etFundName.text.toString(),
                etFundAmount.text.toString(),
                etFundDescription.text.toString()

            )

            Toast.makeText(getApplicationContext(), "Fund Data Updated", Toast.LENGTH_LONG).show();


            //setting updated data to our textviews
            tvFundName.text = etFundName.text.toString()
            tvFundAmount.text =  etFundAmount.text.toString()
            tvFundDescription.text = etFundDescription.text.toString()

            //dismiss alert dialog
            alertDialog.dismiss()
        }


    }

    private fun updateFundData(
        id:String,
        name:String,
        amount:String,
        description: String
    ) {
        //passing data to database
        val dbRef= FirebaseDatabase.getInstance().getReference("Funds").child(id)
        val fundInfo =FundModel(id, name,amount,description)

        //updating the data in the Firebase Realtime Database
        dbRef.setValue(fundInfo)
    }
}

