package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
      private   lateinit var   binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding =  ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        // Hide the status bar for devices below Android 11 (API level 30)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // Other initialization code

        Handler().postDelayed({
            // Start the main activity after delay
            val intent =  Intent(this ,  MainActivity::class.java)
             startActivity(intent)
            finish()
        } , 2000)


    }
}