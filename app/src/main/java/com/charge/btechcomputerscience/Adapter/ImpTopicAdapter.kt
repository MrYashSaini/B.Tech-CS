package com.charge.btechcomputerscience.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.charge.btechcomputerscience.Activity.ShowTopicsActivity
import com.charge.btechcomputerscience.Models.ImpTopicModel
import com.charge.btechcomputerscience.R

class ImpTopicAdapter(private val list:MutableList<ImpTopicModel>,private val context:Context) :
    RecyclerView.Adapter<ImpTopicAdapter.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvImpTopicName)
        val img: ImageView = view.findViewById(R.id.ivImpTopicImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImpTopicAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.imp_topic_sample,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImpTopicAdapter.ViewHolder, position: Int) {
        val model: ImpTopicModel = list.get(position)
        holder.name.text = model.Name
        Glide.with(context).load(model.Img).into(holder.img)
        holder.itemView.setOnClickListener {
            val intent = Intent(context,ShowTopicsActivity::class.java)
            intent.putExtra("name",model.Name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}