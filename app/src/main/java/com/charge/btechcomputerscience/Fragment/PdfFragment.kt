package com.charge.btechcomputerscience.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charge.btechcomputerscience.Adapter.PdfAdapter
import com.charge.btechcomputerscience.Models.PdfModel
import com.charge.btechcomputerscience.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PdfFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:PdfAdapter
    private lateinit var context: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view= inflater.inflate(R.layout.fragment_pdf, container, false)
        this.recyclerView = view.findViewById(R.id.rvPdfRecyclerView)
        val list = mutableListOf<PdfModel>()
        val db = Firebase.firestore
        context = view.context
        val sharedPreferences = context.getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE)
        val sem:String = sharedPreferences.getString("sem",null).toString()
        val subject:String = sharedPreferences.getString("subject",null).toString()
        val unit:String = sharedPreferences.getString("unit",null).toString()

        adapter = PdfAdapter(list,view.context,sem,subject,unit)
        db.collection("semester/$sem/subjects/$subject/units/$unit/pdf").orderBy("index").get().addOnSuccessListener {
            result->
            for(document in result){
                val model = PdfModel(
                    document.data.get("name").toString(),
                    document.data.get("date").toString(),
                    document.data.get("time").toString(),
                    document.data.get("link").toString(),

                )
                list.add(model)
                Log.d("pdf name",document.data.get("name").toString())
                Log.d("pdf size","size: "+db.collection("semenster/sem1/subject1/unit1/notes").count().toString()
                )
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(view.context)
        }

        return view
    }


}