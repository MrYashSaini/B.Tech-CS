package com.charge.btechcomputerscience.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.charge.btechcomputerscience.Activity.OtherActivity
import com.charge.btechcomputerscience.R
class InternshipFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_internship, container, false)
        val internshala: TextView = view.findViewById(R.id.tvInternshipInternshala)
        val linkdin: TextView = view.findViewById(R.id.tvInternshipLinkedin)
        val indeed: TextView = view.findViewById(R.id.tvInternshipIndeed)
        val glassdoor: TextView = view.findViewById(R.id.tvInternshipGlassdoor)

        val intent = Intent(view.context,OtherActivity::class.java)


        internshala.setOnClickListener {
            intent.putExtra("url",internshala.text.toString())
            startActivity(intent)
        }
        linkdin.setOnClickListener {
            intent.putExtra("url",linkdin.text.toString())
            startActivity(intent)
        }
        indeed.setOnClickListener {
            intent.putExtra("url",indeed.text.toString())
            startActivity(intent)
        }
        glassdoor.setOnClickListener {
            intent.putExtra("url",glassdoor.text.toString())
            startActivity(intent)
        }
        return view
    }
}