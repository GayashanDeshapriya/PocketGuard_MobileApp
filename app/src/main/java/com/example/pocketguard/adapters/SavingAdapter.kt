package com.example.pocketguard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketguard.R
import com.example.pocketguard.models.SavingModel

class SavingAdapter(private val savingList: ArrayList<SavingModel>) :
    RecyclerView.Adapter<SavingAdapter.ViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener) {
        itemClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.saving_list_item, parent, false
        )
        return ViewHolder(itemView, itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSaving = savingList[position]
        holder.tvSavingName.text = currentSaving.Name
    }

    override fun getItemCount(): Int {
        return savingList.size
    }

    class ViewHolder(itemView: View, clickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val tvSavingName: TextView = itemView.findViewById(R.id.tvSavingName)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}
