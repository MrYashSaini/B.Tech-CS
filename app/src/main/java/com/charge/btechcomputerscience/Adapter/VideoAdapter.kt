package com.charge.btechcomputerscience.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.charge.btechcomputerscience.Models.VideoModel
import com.charge.btechcomputerscience.R

class VideoAdapter(private val list: List<VideoModel>,private val context:Context):
RecyclerView.Adapter<VideoAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnail: ImageView = view.findViewById(R.id.ivVideoThumbnail)
        val Title: TextView = view.findViewById(R.id.tvVideoTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_sample,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:VideoModel = list.get(position)
        holder.Title.text = model.name

        holder.thumbnail.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setPackage("com.google.android.youtube")
            intent.data = Uri.parse("https://www.youtube.com/watch?v=nvGmnGXlueA")
            context.startActivity(intent)
        }

    }
}