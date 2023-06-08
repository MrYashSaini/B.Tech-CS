package com.charge.btechcomputerscience.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charge.btechcomputerscience.Adapter.ImpTopicAdapter
import com.charge.btechcomputerscience.Models.ImpTopicModel
import com.charge.btechcomputerscience.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ImpSubjectsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_imp_subjects, container, false)
        recyclerView = view.findViewById(R.id.rvImpTopicShow)
        getTopic(view.context)
        return view
    }
//    get data from firebase
    private fun getTopic(context: Context) {
        val firebaseStorage = Firebase.firestore
        val list = mutableListOf<ImpTopicModel>()
        val adapter = ImpTopicAdapter(list,context)
        firebaseStorage.collection("imp topics").orderBy("index").get().addOnSuccessListener {
            documents->
            for(document in documents){
                val model  = ImpTopicModel(
                    document.data.get("image").toString(),
                    document.data.get("name").toString()
                )
                list.add(model)
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(context,2)


        }
    }
}