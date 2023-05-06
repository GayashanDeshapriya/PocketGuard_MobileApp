package com.example.pocketguard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketguard.R
import com.example.pocketguard.models.ExpenseModel



class ExpenseAdapter(private val expenseList: ArrayList <ExpenseModel>):
    RecyclerView.Adapter<ExpenseAdapter.ViewHolder>(){

    private lateinit var mListner: onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(clickListner:onItemClickListner){
        mListner=clickListner
    }


    private lateinit var mListener: onItemClickListener


    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.expense_list_item, parent, false)
        return ViewHolder(itemView, mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentFund =expenseList[position]
        holder.tvExpenseName.text= currentFund.expenseName
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    class ViewHolder(itemView: View, clickListner: onItemClickListner): RecyclerView.ViewHolder(itemView) {
        val tvExpenseName :TextView=itemView.findViewById(R.id.tvExpenseName)

        init{
            itemView.setOnClickListener{
                clickListner.onItemClick(adapterPosition)
            }
        }
    }

}