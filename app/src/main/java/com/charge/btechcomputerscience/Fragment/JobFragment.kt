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


class JobFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_job, container, false)
        val internshala: TextView = view.findViewById(R.id.tvJobInternshala)
        val linkdin: TextView = view.findViewById(R.id.tvJobLinkedin)
        val indeed: TextView = view.findViewById(R.id.tvJobIndeed)
        val glassdoor: TextView = view.findViewById(R.id.tvJobGlassdoor)


        val intent = Intent(view.context, OtherActivity::class.java)


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