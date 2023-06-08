package com.charge.btechcomputerscience.Fragment

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charge.btechcomputerscience.Adapter.DownloadsAdapter
import com.charge.btechcomputerscience.Models.DownloadModel
import com.charge.btechcomputerscience.R
import com.charge.btechcomputerscience.databasemenu


class DownloadsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var semSpinner:Spinner
    private lateinit var subjectSpinner:Spinner
    private lateinit var unitSpinner:Spinner
    val semList = listOf("select sem", "sem1","sem2", "sem3","sem4","sem5"," sem6"," sem7","sem8")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_downloads, container, false)
        recyclerView = view.findViewById(R.id.rvDownloadsShow)
        semSpinner = view.findViewById(R.id.spDownloadsSem)
        subjectSpinner = view.findViewById(R.id.spDownloadsSubject)
        unitSpinner  = view.findViewById(R.id.spDownloadsUnit)
        setData(view.context)
        setSem(view.context)

        return view
    }
//   set sem list in sem spinner
    private fun setSem(context: Context) {

        val semAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, semList)
        semAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        semSpinner.adapter = semAdapter

        semSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (semList[position]!="select sem"){
                    getSemNotes(context,semList[position])
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle empty selection for spinner1
            }
        }

    }
//  get notes detail of selected sem
    private fun getSemNotes(context: Context,sem: String) {
        val databaseMenu = databasemenu(context)
        val res = databaseMenu.getdata()
        val list = mutableListOf<DownloadModel>()
        val adapter = DownloadsAdapter(list,context)
        val subjectSet = mutableSetOf<String>()
        subjectSet.add("select subject")
        while (res.moveToNext()) {
            if (res.getString(1)==sem) {
                subjectSet.add(res.getString(2))
                val model = DownloadModel(
                    res.getString(0).toInt(),
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5)
                )
                list.add(model)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context)
            }
        }
        if (list.size==0){
            val subjectAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, list)
            subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            subjectSpinner.adapter = subjectAdapter

            val unitAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, list)
            unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            unitSpinner.adapter = unitAdapter
        }

        else{
            setSubject(context,subjectSet)
        }

    }

//    set subject list on subject spineer
    private fun setSubject(context: Context, subjectSet: MutableSet<String>) {
        Log.d("subject set",subjectSet.size.toString())
        val subjectAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, subjectSet.toList())
        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        subjectSpinner.adapter = subjectAdapter


        subjectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Handle item selection for spinner2
                if (subjectSet.toList().get(position)!="select subject"){
                    getSubjectNotes(context,subjectSet.toList().get(position))
                }
                
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle empty selection for spinner2
            }
        }
    }
//    mySpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, emptyList())
//mySpinner.setSelection(0)
//  get notes from database by subject
    private fun getSubjectNotes(context: Context, subject: String) {
        val databaseMenu = databasemenu(context)
        val res = databaseMenu.getdata()
        val list = mutableListOf<DownloadModel>()
        val adapter = DownloadsAdapter(list,context)
        val unitSet = mutableSetOf<String>()
        unitSet.add("select unit")
        while (res.moveToNext()) {
            if (res.getString(2)==subject) {
                unitSet.add(res.getString(3))
                val model = DownloadModel(
                    res.getString(0).toInt(),
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5)
                )
                list.add(model)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context)

            }
        }
        setUnit(context,unitSet,subject)
    }

//    set unit list in unit spinner
    private fun setUnit(context: Context, unitSet: MutableSet<String>,subject: String) {
        val unitAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, unitSet.toList())
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitSpinner.adapter = unitAdapter
        unitSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Handle item selection for spinner2
                if (unitSet.toList().get(position)!="select unit"){
                    getUnitNotes(context,subject, unitSet.toList()[position])
                }
                Log.d("checkunitsp", unitSet.toList()[position])

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle empty selection for spinner2
            }
        }
    }
//  get notes using unit from database
    private fun getUnitNotes(context: Context, subject: String,unit: String) {
        val databaseMenu = databasemenu(context)
        val res = databaseMenu.getdata()
        val list = mutableListOf<DownloadModel>()
        val adapter = DownloadsAdapter(list,context)
        val unitSet = mutableSetOf<String>()
        unitSet.add("select unit")
        while (res.moveToNext()) {
            if (res.getString(2) == subject) {
                if (res.getString(3) == unit) {
                    unitSet.add(res.getString(3))
                    val model = DownloadModel(
                        res.getString(0).toInt(),
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5)
                    )
                    list.add(model)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(context)

                }
            }
        }
    }

//   show all notes on recycler view
    private fun setData(context:Context) {
        val databaseMenu = databasemenu(context)
        val res = databaseMenu.getdata()
        val list = mutableListOf<DownloadModel>()
        val adapter = DownloadsAdapter(list,context)
        while (res.moveToNext()) {
            var model = DownloadModel(
                res.getString(0).toInt(),
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getString(5)
            )
            list.add(model)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

}