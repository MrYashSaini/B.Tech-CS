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
import com.charge.btechcomputerscience.Adapter.VideoAdapter
import com.charge.btechcomputerscience.Models.PdfModel
import com.charge.btechcomputerscience.Models.VideoModel
import com.charge.btechcomputerscience.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class VideoFragment : Fragment() {
    private lateinit var adapter: VideoAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video, container, false)
        recyclerView = view.findViewById(R.id.rvVideoRecyclerView)
        val list = mutableListOf<VideoModel>()
        val db = Firebase.firestore
        adapter = VideoAdapter(list,view.context)
        val sharedPreferences = view.context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val sem:String = sharedPreferences.getString("sem",null).toString()
        val subject:String = sharedPreferences.getString("subject",null).toString()
        val unit:String = sharedPreferences.getString("unit",null).toString()

        db.collection("semester/$sem/subjects/$subject/units/$unit/video").orderBy("index").get().addOnSuccessListener {
                result->
            for(document in result){
                val model = VideoModel(
                    document.data.get("name").toString(),
                    document.data.get("date").toString(),
                    document.data.get("link").toString(),

                    )
                list.add(model)
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(view.context)
        }

        return view
    }

}