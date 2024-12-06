package com.example.bmicalculator

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityBmiGraphBinding
import com.example.bmicalculator.databinding.ActivityMainBinding

class bmi_graph : AppCompatActivity() {
     private  lateinit var   binding: ActivityBmiGraphBinding
    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding  =  ActivityBmiGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide the status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // For Android 11 (API 30) and above
            val windowInsetsController = window.insetsController
            windowInsetsController?.hide(android.view.WindowInsets.Type.statusBars())
        } else {
            // For older versions of Android
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        // Hide the status bar for devices below Android 11 (API level 30)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)




        val getBmiValue =  intent.getFloatExtra("bmi" ,  0.5f)
        binding.bmiView.text = String.format("Your BMI:%.2f" , getBmiValue)

        val bmiCategory  =  intent.getStringExtra("bmiCategory")
        binding.bmiCategoryView.text = bmiCategory

        val animator  = ObjectAnimator.ofFloat(binding.bmiCategoryView , View.ALPHA , 1f ,
            0f , 1f)
        animator.duration =  500
        animator.repeatCount =  ObjectAnimator.INFINITE
        animator.repeatMode =  ObjectAnimator.REVERSE
        animator.start()

          // again  go to main  Main Activity
        binding.calculateAgainBtn.setOnClickListener {
             val intent = Intent(this ,  MainActivity ::class.java)
             startActivity(intent)
        }


    }
}