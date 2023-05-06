package com.example.pocketguard.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.pocketguard.R
import com.example.pocketguard.models.ExpenseModel
import com.example.pocketguard.models.FundModel
import com.google.firebase.database.FirebaseDatabase

class CurrentExpenseView : AppCompatActivity() {

    private lateinit var tvExpenseId: TextView
    private lateinit var tvExpenseName: TextView
    private lateinit var tvExpenseAmount: TextView
    private lateinit var tvExpenseCategory: TextView
    private lateinit var tvExpenseDescription: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_expense_view)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("expenseId").toString(),
                intent.getStringExtra("expenseName").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("expenseId").toString()
            )
        }

        val backButton = findViewById<Button>(R.id.back1)
        backButton.setOnClickListener {
            val intent = Intent(this, ViewAllExpense::class.java)
            startActivity(intent)
        }
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Expenses").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Expense data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, CurrentExpenseView::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvExpenseId = findViewById(R.id.tvExpenseId)
        tvExpenseName = findViewById(R.id.tvExpenseName)
        tvExpenseAmount = findViewById(R.id.tvExpenseAmount)
        tvExpenseCategory = findViewById(R.id.tvExpenseCategory)
        tvExpenseDescription = findViewById(R.id.tvExpenseDescription)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvExpenseId.text = intent.getStringExtra("expenseId")
        tvExpenseName.text = intent.getStringExtra("expenseName")
        tvExpenseAmount.text = intent.getStringExtra("expenseAmount")
        tvExpenseCategory.text = intent.getStringExtra("expenseCategory")
        tvExpenseDescription.text = intent.getStringExtra("expenseDescription")
    }
    private fun openUpdateDialog(
        expenseId: String,
        expenseName: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_expense_details, null)

        mDialog.setView(mDialogView)
        val etExpenseName =mDialogView.findViewById<EditText>(R.id.etExpenseName)
        val etExpenseAmount =mDialogView.findViewById<EditText>(R.id.etExpenseAmount)
        val etExpenseCategory =mDialogView.findViewById<EditText>(R.id.etExpenseCategory)
        val etExpenseDescription =mDialogView.findViewById<EditText>(R.id.etExpenseDescription)
        val btnUpdate =mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etExpenseName.setText(intent.getStringExtra("expenseName").toString())
        etExpenseAmount.setText(intent.getStringExtra("expenseAmount").toString())
        etExpenseCategory.setText(intent.getStringExtra("expenseCategory").toString())
        etExpenseDescription.setText(intent.getStringExtra("expenseDescription").toString())

        mDialog.setTitle("updating $expenseName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdate.setOnClickListener{
            updateExpenseData(
                expenseId,
                etExpenseName.text.toString(),
                etExpenseAmount.text.toString(),
                etExpenseCategory.text.toString(),
                etExpenseDescription.text.toString()

            )

            Toast.makeText(getApplicationContext(), "Expense Data Updated", Toast.LENGTH_LONG).show();


            //setting updated data to our textviews
            tvExpenseName.text = etExpenseName.text.toString()
            tvExpenseAmount.text =  etExpenseAmount.text.toString()
            tvExpenseCategory.text =  etExpenseCategory.text.toString()
            tvExpenseDescription.text = etExpenseDescription.text.toString()

            //dismiss alert dialog
            alertDialog.dismiss()
        }


    }

    private fun updateExpenseData(
        id:String,
        name:String,
        amount:String,
        category:String,
        description: String
    ) {
        //passing data to database
        val dbRef= FirebaseDatabase.getInstance().getReference("Expenses").child(id)
        val expenseInfo = ExpenseModel(id, name,amount,category,description)

        //updating the data in the Firebase Realtime Database
        dbRef.setValue(expenseInfo)
    }
}

