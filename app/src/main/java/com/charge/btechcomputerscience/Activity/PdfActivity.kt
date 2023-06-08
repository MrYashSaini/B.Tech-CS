package com.charge.btechcomputerscience.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.charge.btechcomputerscience.R
import com.github.barteksc.pdfviewer.PDFView

class PdfActivity : AppCompatActivity() {
    private lateinit var pdfView: PDFView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)


    }


}