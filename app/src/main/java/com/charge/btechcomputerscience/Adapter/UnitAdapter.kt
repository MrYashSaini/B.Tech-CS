package com.charge.btechcomputerscience.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.charge.btechcomputerscience.Activity.UnitActivity
import com.charge.btechcomputerscience.R

class UnitAdapter(
    private val sem:String,
    private val subject:String,
    private val list: List<String>,
    private val context:Context):RecyclerView.Adapter<UnitAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val unitName : TextView = view.findViewById(R.id.tvSubject_SampleName)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.subject_sample,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.unitName.text = list.get(position)
        holder.unitName.setOnClickListener{
            Log.d("listSize",list.size.toString())
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, UnitActivity::class.java)
            intent.putExtra("unit",list.get(position))
            intent.putExtra("sem",sem)
            intent.putExtra("subject",subject)
            context.startActivity(intent)
        }
    }
}