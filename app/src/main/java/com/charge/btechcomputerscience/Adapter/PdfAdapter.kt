package com.charge.btechcomputerscience.Adapter

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.charge.btechcomputerscience.Activity.FileActivity
import com.charge.btechcomputerscience.Models.PdfModel
import com.charge.btechcomputerscience.R
import com.charge.btechcomputerscience.databasemenu
import java.io.File

class PdfAdapter(private val list: List<PdfModel>,
                 private val context:Context,
                 private val sem:String,
                 private val subject:String,
                 private val unit:String,
                ):
    RecyclerView.Adapter<PdfAdapter.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val pdfTitle: TextView = view.findViewById(R.id.tvPdfName)
        val date:TextView = view.findViewById(R.id.tvPdfDateTime)
        val download: ImageView = view.findViewById(R.id.ivPdfDownload)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pdf_sample,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list.get(position)
        holder.pdfTitle.text = model.name
        holder.date.text = model.date+" "+model.time
        if(checkFile(model.name)){
            holder.download.visibility = View.GONE
        }
        holder.download.setOnClickListener {
            downloadFile(context,model.name,".pdf","notes",model.link,sem,subject,unit)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context,FileActivity::class.java)
            intent.putExtra("url",model.link)
            intent.putExtra("type",true)
            context.startActivity(intent)
        }
    }
//    check file downloaded or not
    fun checkFile(name:String): Boolean {
        val appSpecificExternalDir = File(context.getExternalFilesDir(null), "notes/"+name+".pdf")
        val file = File(appSpecificExternalDir.toString())
        Log.d("fileexists",file.exists().toString())
        return file.exists()
    }
//  download file from firebase storage and store in phone
    fun downloadFile(
        context: Context,
        fileName: String,
        fileExtension: String,
        destinationDirectory: String?,
        url: String?,
        sem:String?,
        subject: String,
        unit: String
    ): Long {
        val downloadmanager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setTitle(fileName)
        request.setDestinationInExternalFilesDir(
            context,
            destinationDirectory,
            fileName + fileExtension
        )
        val db = databasemenu(context)
        val appSpecificExternalDir = File(context.getExternalFilesDir(null), "notes/"+fileName+fileExtension)
        db.insertuserdata(sem,subject,unit,fileName,appSpecificExternalDir.toString())
        return downloadmanager.enqueue(request)
    }
}