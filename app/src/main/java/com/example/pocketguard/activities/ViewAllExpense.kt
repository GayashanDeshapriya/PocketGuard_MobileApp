package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketguard.R
import com.example.pocketguard.adapters.ExpenseAdapter
import com.example.pocketguard.adapters.FundAdapter
import com.example.pocketguard.models.ExpenseModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewAllExpense : AppCompatActivity() {

    private lateinit var expenseRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var expenseList: ArrayList<ExpenseModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all_expense)

        expenseRecyclerView = findViewById(R.id.rvExpense)
        expenseRecyclerView.layoutManager = LinearLayoutManager(this)
        expenseRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        expenseList = arrayListOf()

        getExpenseData()

        val button = findViewById<Button>(R.id.back1)
        button.setOnClickListener {
            val intent = Intent(this, ExpenseTrackingMain::class.java)
            startActivity(intent)
        }
    }

    private fun getExpenseData() {
        expenseRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Expenses")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                expenseList.clear()
                if (snapshot.exists()) {
                    for (expenseSnap in snapshot.children) {
                        val expenseData = expenseSnap.getValue(ExpenseModel::class.java)
                        val add = expenseList.add(expenseData!!)
                    }
                    val eAdapter = ExpenseAdapter(expenseList)
                    expenseRecyclerView.adapter = eAdapter


                    eAdapter.setOnItemClickListner(object : ExpenseAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            val intent= Intent(this@ViewAllExpense, CurrentExpenseView::class.java)

                            //put extras
                            intent.putExtra("expenseId", expenseList[position].expenseId)
                            intent.putExtra("expenseName", expenseList[position].expenseName)
                            intent.putExtra("expenseAmount", expenseList[position].expenseAmount)
                            intent.putExtra("expenseCategory", expenseList[position].expenseCategory)
                            intent.putExtra("expenseDescription", expenseList[position].expenseDescription)
                            startActivity(intent)
                        }

                    })

                    expenseRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // TODO: Handle database error
            }
        })
    }
}