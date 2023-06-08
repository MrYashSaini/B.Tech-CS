package com.charge.btechcomputerscience.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.charge.btechcomputerscience.Activity.OtherActivity
import com.charge.btechcomputerscience.Models.OtherModel
import com.charge.btechcomputerscience.R

class OtherAdapter(private val list: List<OtherModel>,private val context: Context):
RecyclerView.Adapter<OtherAdapter.ViewHolder>(){
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvOtherTitle)
        val date: TextView = view.findViewById(R.id.tvOtherDateTime)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.other_sample,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:OtherModel = list.get(position)
        holder.title.text = model.name
        holder.date.text = model.date+" "+model.time
        holder.itemView.setOnClickListener {
            val intent = Intent(context,OtherActivity::class.java)
            intent.putExtra("url",model.link)
            context.startActivity(intent)
        }
    }
}