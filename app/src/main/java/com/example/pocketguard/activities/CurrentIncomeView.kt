package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pocketguard.R
import com.example.pocketguard.models.ExpenseModel
import com.example.pocketguard.models.IncomeModel
import com.google.firebase.database.FirebaseDatabase

class CurrentIncomeView : AppCompatActivity() {

    private lateinit var tvIncomeID: TextView
    private lateinit var tvIncomeName: TextView
    private lateinit var tvIncomeAmount: TextView
    private lateinit var tvIncomeCategory: TextView
    private lateinit var tvIncomeDescription: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_income_view)

        initView()
        setValuesToViews()


        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("IncomeId").toString(),
                intent.getStringExtra("IncomeName").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("IncomeId").toString()
            )
        }




        val backButton = findViewById<Button>(R.id.back1)
        backButton.setOnClickListener {
            val intent = Intent(this, AllExistingIncome::class.java)
            startActivity(intent)
        }
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Income").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Income data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, CurrentExpenseView::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {

        tvIncomeName = findViewById(R.id.tvIncomeName)
        tvIncomeAmount = findViewById(R.id.tvIncomeAmount)
        tvIncomeCategory = findViewById(R.id.tvIncomeCategory)
        tvIncomeDescription = findViewById(R.id.tvExpenseDescription)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {

        tvIncomeID.text = intent.getStringExtra("IncomeID")
        tvIncomeName.text = intent.getStringExtra("IncomeName")
        tvIncomeAmount.text = intent.getStringExtra("IncomeAmount")
        tvIncomeCategory.text = intent.getStringExtra("IncomeCategory")
        tvIncomeDescription.text = intent.getStringExtra("IncomeDescription")
    }
    private fun openUpdateDialog(
        incomeId: String,
        incomeName: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_income_details, null)

        mDialog.setView(mDialogView)
        val etIncomeName =mDialogView.findViewById<EditText>(R.id.etIncomeName)
        val etIncomeAmount =mDialogView.findViewById<EditText>(R.id.etIncomeAmount)
        val etIncomeCategory =mDialogView.findViewById<EditText>(R.id.etIncomeCategory)
        val etIncomeDescription =mDialogView.findViewById<EditText>(R.id.etIncomeDescription)
        val btnUpdate =mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etIncomeName.setText(intent.getStringExtra("incomeName").toString())
        etIncomeAmount.setText(intent.getStringExtra("incomeAmount").toString())
        etIncomeCategory.setText(intent.getStringExtra("incomeCategory").toString())
        etIncomeDescription.setText(intent.getStringExtra("incomeDescription").toString())

        mDialog.setTitle("updating $incomeName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdate.setOnClickListener{
            updateIncomeData(
                incomeId,
                etIncomeName.text.toString(),
                etIncomeAmount.text.toString(),
                etIncomeCategory.text.toString(),
                etIncomeDescription.text.toString()

            )

            Toast.makeText(getApplicationContext(), "Income Data Updated", Toast.LENGTH_LONG).show();


            //setting updated data to our textviews
            tvIncomeName.text = etIncomeName.text.toString()
            tvIncomeAmount.text = etIncomeAmount.text.toString()
            tvIncomeCategory.text = etIncomeCategory.text.toString()
            etIncomeDescription.text.toString()
            //dismiss alert dialog
            alertDialog.dismiss()
        }


    }

    private fun updateIncomeData(
        id:String,
        name:String,
        amount:String,
        category:String,
        description: String
    ) {
        //passing data to database
        val dbRef= FirebaseDatabase.getInstance().getReference("Income").child(id)
        val incomeInfo = IncomeModel(id, name,amount,category,description)

        //updating the data in the Firebase Realtime Database
        dbRef.setValue(incomeInfo)
    }
}
