package com.charge.btechcomputerscience.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.charge.btechcomputerscience.Activity.SemesterActivity
import com.charge.btechcomputerscience.R

class NotesFragment : Fragment() {
    private lateinit var sem1: Button
    private lateinit var sem2: Button
    private lateinit var sem3: Button
    private lateinit var sem4: Button
    private lateinit var sem5: Button
    private lateinit var sem6: Button
    private lateinit var sem7: Button
    private lateinit var sem8: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_notes, container, false)
//        initialize
        sem1 = view.findViewById(R.id.btnNotesSem1)
        sem2 = view.findViewById(R.id.btnNotesSem2)
        sem3 = view.findViewById(R.id.btnNotesSem3)
        sem4 = view.findViewById(R.id.btnNotesSem4)
        sem5 = view.findViewById(R.id.btnNotesSem5)
        sem6 = view.findViewById(R.id.btnNotesSem6)
        sem7 = view.findViewById(R.id.btnNotesSem7)
        sem8 = view.findViewById(R.id.btnNotesSem8)

//      set intent on button
        val intent = Intent(context, SemesterActivity::class.java)
        sem1.setOnClickListener {
            intent.putExtra("semName","sem1")
            startActivity(intent)
        }

        sem2.setOnClickListener {
            intent.putExtra("semName","sem2")
            startActivity(intent)
        }
        sem3.setOnClickListener {
            intent.putExtra("semName","sem3")
            startActivity(intent)
        }
        sem4.setOnClickListener {
            intent.putExtra("semName","sem4")
            startActivity(intent)
        }
        sem5.setOnClickListener {
            intent.putExtra("semName","sem5")
            startActivity(intent)
        }
        sem6.setOnClickListener {
            intent.putExtra("semName","sem6")
            startActivity(intent)
        }
        sem7.setOnClickListener {
            intent.putExtra("semName","sem7")
            startActivity(intent)
        }
        sem8.setOnClickListener {
            intent.putExtra("semName","sem8")
            startActivity(intent)
        }


        return view
    }
}