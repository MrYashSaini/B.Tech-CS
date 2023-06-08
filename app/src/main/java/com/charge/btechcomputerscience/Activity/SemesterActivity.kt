package com.charge.btechcomputerscience.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charge.btechcomputerscience.Adapter.SubjectAdapter
import com.charge.btechcomputerscience.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SemesterActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semester)

        recyclerView = findViewById(R.id.rvSemesterRecyclerView)
        val list = mutableListOf<String>()
        val db = Firebase.firestore
        val sem: String = intent.getStringExtra("semName").toString()
        val title = findViewById<TextView>(R.id.tvSemesterTitle)
        title.text = sem
//        set data on recycler view
        db.collection("semester").document(sem).collection("subjects").orderBy("index").get()
            .addOnSuccessListener {
                    documents->
                for (document in documents) {
                    list.add(document.data.get("name").toString())
                }
                val adapter = SubjectAdapter(sem,list,this)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

            }


    }
}