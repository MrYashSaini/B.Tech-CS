package com.charge.btechcomputerscience.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.charge.btechcomputerscience.Activity.OtherActivity
import com.charge.btechcomputerscience.Models.ShowTopicModel
import com.charge.btechcomputerscience.R

class ShowTopicsAdapter(private val list: MutableList<ShowTopicModel>,private val context:Context):
    RecyclerView.Adapter<ShowTopicsAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val name:TextView = view.findViewById(R.id.tvShowTopicsSpName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.show_topics_sample,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ShowTopicModel = list.get(position)
        holder.name.text = model.name
        holder.itemView.setOnClickListener {
            val intent = Intent(context,OtherActivity::class.java)
            intent.putExtra("url",model.link)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}