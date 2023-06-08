package com.charge.btechcomputerscience.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.charge.btechcomputerscience.Activity.FileActivity
import com.charge.btechcomputerscience.Models.DownloadModel
import com.charge.btechcomputerscience.R
import com.charge.btechcomputerscience.databasemenu
import java.io.File

class DownloadsAdapter(private val list: List<DownloadModel>,private val context: Context):
RecyclerView.Adapter<DownloadsAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val sem:TextView = view.findViewById(R.id.tvDownloadSpSem)
        val subject:TextView = view.findViewById(R.id.tvDownloadSpSubject)
        val unit:TextView = view.findViewById(R.id.tvDownloadSpUnit)
        val name:TextView = view.findViewById(R.id.tvDownloadSpName)
        val delete:ImageView = view.findViewById(R.id.ivDownloadSpDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadsAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.download_sample,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DownloadsAdapter.ViewHolder, position: Int) {
        val model:DownloadModel = list.get(position)
        holder.name.text = model.name
        holder.sem.text = model.sem
        holder.subject.text = model.subject
        holder.unit.text = model.unit

        holder.name.setOnClickListener {
            val intent = Intent(context,FileActivity::class.java)
            intent.putExtra("url",model.path)
            intent.putExtra("type",false)
            context.startActivity(intent)
        }

        holder.delete.setOnClickListener {
            val db = databasemenu(context)
            db.deleteuserdata(model.id)
//            val dir: File = getFilesDir()
            val file = File(model.path)
            file.delete()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}