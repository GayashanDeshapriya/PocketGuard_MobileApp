package com.example.pocketguard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketguard.R
import com.example.pocketguard.adapters.FundAdapter
import com.example.pocketguard.adapters.IncomeAdapter
import com.example.pocketguard.models.FundModel
import com.example.pocketguard.models.IncomeModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllExistingIncome : AppCompatActivity() {

    private lateinit var incomeRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var incomeList: ArrayList<IncomeModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_existing_income)

        incomeRecyclerView = findViewById(R.id.rvIncome)
        incomeRecyclerView.layoutManager = LinearLayoutManager(this)
        incomeRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        incomeList = arrayListOf()

        getFundsData()

        val button = findViewById<Button>(R.id.back1)
        button.setOnClickListener {
            val intent = Intent(this, IncomeTrackingMain::class.java)
            startActivity(intent)
        }
    }

    private fun getFundsData() {
        incomeRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Income")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                incomeList.clear()
                if (snapshot.exists()) {
                    for (fundSnap in snapshot.children) {
                        val incomeData = fundSnap.getValue(IncomeModel::class.java)
                        incomeList.add(incomeData!!)
                    }
                    val fAdapter = IncomeAdapter(incomeList)
                    incomeRecyclerView.adapter = fAdapter

                    fAdapter.setOnItemClickListner(object : IncomeAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            val intent= Intent(this@AllExistingIncome, CurrentIncomeView::class.java)

                            //put extras
                            intent.putExtra("incomeId", incomeList[position].IncomeId)
                            intent.putExtra("incomeName", incomeList[position].IncomeName)
                            intent.putExtra("incomeAmount", incomeList[position].IncomeAmount)
                            intent.putExtra("incomeDescription", incomeList[position].IncomeDescription)
                            startActivity(intent)
                        }

                    })

                    incomeRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // TODO: Handle database error
            }
        })
    }
}
