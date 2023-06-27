package com.example.pocketguard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketguard.R
import com.example.pocketguard.models.ExpenseModel
import com.example.pocketguard.models.IncomeModel

class IncomeAdapter (private val incomeList: ArrayList <IncomeModel>):
    RecyclerView.Adapter<IncomeAdapter.ViewHolder>(){

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
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.income_list_item, parent, false)
        return ViewHolder(itemView, mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentIncome =incomeList[position]
        holder.tvIncomeName.text= currentIncome.IncomeName
    }

    override fun getItemCount(): Int {
        return incomeList.size
    }

    class ViewHolder(itemView: View, clickListner: onItemClickListner): RecyclerView.ViewHolder(itemView) {
        val tvIncomeName :TextView=itemView.findViewById(R.id.tvIncomeName)

        init{
            itemView.setOnClickListener{
                clickListner.onItemClick(adapterPosition)
            }
        }
    }

}