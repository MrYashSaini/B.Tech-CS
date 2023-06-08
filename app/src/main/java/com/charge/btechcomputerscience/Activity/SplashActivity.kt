package com.charge.btechcomputerscience.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.charge.btechcomputerscience.MainActivity
import com.charge.btechcomputerscience.R

class SplashActivity : AppCompatActivity() {
    private val SPLASH_DELAY: Long = 2000 // Delay in milliseconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY)
    }
}