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
import com.example.pocketguard.adapters.FundAdapter
import com.example.pocketguard.models.FundModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllExistingFunds : AppCompatActivity() {

    private lateinit var fundRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var fundList: ArrayList<FundModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_existing_funds)

        fundRecyclerView = findViewById(R.id.rvFund)
        fundRecyclerView.layoutManager = LinearLayoutManager(this)
        fundRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        fundList = arrayListOf()

        getFundsData()

        val button = findViewById<Button>(R.id.back1)
        button.setOnClickListener {
            val intent = Intent(this, EmergencyFundMain::class.java)
            startActivity(intent)
        }
    }

    private fun getFundsData() {
        fundRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Funds")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                fundList.clear()
                if (snapshot.exists()) {
                    for (fundSnap in snapshot.children) {
                        val fundData = fundSnap.getValue(FundModel::class.java)
                        fundList.add(fundData!!)
                    }
                    val fAdapter = FundAdapter(fundList)
                    fundRecyclerView.adapter = fAdapter

                    fAdapter.setOnItemClickListner(object :FundAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            val intent= Intent(this@AllExistingFunds, CurrentFundView::class.java)

                            //put extras
                            intent.putExtra("fundId", fundList[position].fundId)
                            intent.putExtra("fundName", fundList[position].fundName)
                            intent.putExtra("fundAmount", fundList[position].fundAmount)
                            intent.putExtra("fundDescription", fundList[position].fundDescription)
                            startActivity(intent)
                        }

                    })

                    fundRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // TODO: Handle database error
            }
        })
    }
}
