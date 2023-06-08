package com.charge.btechcomputerscience.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charge.btechcomputerscience.Adapter.UnitAdapter
import com.charge.btechcomputerscience.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SubjectActivity : AppCompatActivity() {
    private lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject)
//        initialize
        recyclerView = findViewById(R.id.rvSubjectRecyclerView)
        val title:TextView = findViewById(R.id.tvSubjectTitle)

//        set data on recycler view
        val list = mutableListOf<String>()
        val db = Firebase.firestore
        val subjectName:String = intent.getStringExtra("subject").toString()
        title.text = subjectName+" Units"
        val sem:String = intent.getStringExtra("sem").toString()
        val adapter = UnitAdapter(sem,subjectName,list,this)
//        get data from firebase store
        db.collection("semester").document(sem).collection("subjects").document(subjectName).collection("units").get()
            .addOnSuccessListener {
                    documents1->
                for (document in documents1){
                    list.add(document.get("name").toString())
                }
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this)
            }



    }
}