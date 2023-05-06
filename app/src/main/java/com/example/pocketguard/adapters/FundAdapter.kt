package com.example.pocketguard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketguard.R
import com.example.pocketguard.models.FundModel


class FundAdapter(private val fundList: ArrayList <FundModel>):
    RecyclerView.Adapter<FundAdapter.ViewHolder>(){

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
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.fund_list_item, parent, false)
        return ViewHolder(itemView, mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentFund =fundList[position]
        holder.tvFundName.text= currentFund.fundName
    }

    override fun getItemCount(): Int {
        return fundList.size
    }

    class ViewHolder(itemView: View, clickListner: onItemClickListner): RecyclerView.ViewHolder(itemView) {
        val tvFundName :TextView=itemView.findViewById(R.id.tvFundName)

        init{
            itemView.setOnClickListener{
                clickListner.onItemClick(adapterPosition)
            }
        }
    }

}