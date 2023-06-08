package com.charge.btechcomputerscience.Activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charge.btechcomputerscience.Adapter.ImpTopicAdapter
import com.charge.btechcomputerscience.Adapter.ShowTopicsAdapter
import com.charge.btechcomputerscience.Models.ShowTopicModel
import com.charge.btechcomputerscience.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ShowTopicsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_topics)
        val name = intent.getStringExtra("name").toString()
        recyclerView = findViewById(R.id.rvShowTopicsShow)
        setData(this,name)
    }

    private fun setData(context: Context,name: String) {
        val list = mutableListOf<ShowTopicModel>()
        val adapter = ShowTopicsAdapter(list, context)
        val firestore = Firebase.firestore
        firestore.collection("imp topics/$name/topics").orderBy("index").get()
            .addOnSuccessListener {
                documents->
                for (document in documents){
                    val model = ShowTopicModel(
                        document.data.get("name").toString(),
                        document.data.get("link").toString()
                    )
                    list.add(model)
                }
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context)
            }
    }
}