package com.charge.btechcomputerscience.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.charge.btechcomputerscience.Activity.SubjectActivity
import com.charge.btechcomputerscience.R

class SubjectAdapter(private val sem:String,private val list: List<String>,private val context: Context): RecyclerView.Adapter<SubjectAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val subjectName : TextView = view.findViewById(R.id.tvSubject_SampleName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.subject_sample,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.subjectName.text = list.get(position)
        holder.subjectName.setOnClickListener{
            Log.d("listSize",list.size.toString())
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context,SubjectActivity::class.java)
            intent.putExtra("subject",list.get(position))
            intent.putExtra("sem",sem)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}