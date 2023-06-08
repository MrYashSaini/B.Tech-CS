package com.charge.btechcomputerscience.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.charge.btechcomputerscience.R

class OtherActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)
        val url = intent.getStringExtra("url").toString()
        webView = findViewById(R.id.otherWebView)
        webView.loadUrl(url)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

    }
}