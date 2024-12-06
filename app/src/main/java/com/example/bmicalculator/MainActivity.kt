package com.example.bmicalculator

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.inputmethodservice.InputMethodService
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.bmicalculator.databinding.ActivityBmiGraphBinding
import com.example.bmicalculator.databinding.ActivityMainBinding
import com.shashank.sony.fancytoastlib.FancyToast
import java.util.Objects
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private  lateinit var   binding: ActivityMainBinding
    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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

          // graph button  invisible
          binding.bmiGraphButton.visibility =  View.INVISIBLE

        binding.calculateBmiButton.setOnClickListener {
            val age =  binding.ageEdt.text.toString().toFloatOrNull()
            val height =  binding.heightEdt.text.toString()  .toFloatOrNull()
            val weight =  binding.weightEdt.text.toString()  .toFloatOrNull()
            hideKeyboard(this)
            if (age !=  null &&  height  != null  &&  weight!=  null)  {
                val bmi =  (weight /  (height /  100).pow(2))
               val bmiResult  = String.format("%.2f" , bmi)
                val bmiCategory  =  when  {
                      bmi <18.5 ->  "UNDER WEIGHT"
                    bmi < 25 -> "NORMAL WEIGHT"
                    bmi < 30 ->  "OVER WEIGHT"
                    else -> "OBESE"
                }
                binding.resultTv.text  =  String.format("YOUR BMI: $bmiResult \nCATEGORY: $bmiCategory")

                  // now graph button  is visible
                 binding.bmiGraphButton.visibility =  View.VISIBLE

                  /// blink  bmi graph  button  code
                val animator  = ObjectAnimator.ofFloat(binding.bmiGraphButton , View.ALPHA , 1f ,
                    0f , 1f)
                animator.duration =  500
                animator.repeatCount =  ObjectAnimator.INFINITE
                animator.repeatMode =  ObjectAnimator.REVERSE
                animator.start()

                //  pass the data  bmi and bmi category  in another activity

                binding.bmiGraphButton.setOnClickListener {
                      val intent  =  Intent(this , bmi_graph::class.java)
                     intent.putExtra("bmi" , bmi)
                     intent.putExtra("bmiCategory" , bmiCategory)
                    startActivity(intent)
                }
            } else {
               FancyToast.makeText(this@MainActivity ,  "Please Enter All  Field" , FancyToast.LENGTH_SHORT , FancyToast.WARNING , true).show()
            }

        }
    }
 private   fun  hideKeyboard(context: Context)  {
        val imm  =  context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
       val windowToken  =  (context as Activity).currentFocus?.windowToken
     imm.hideSoftInputFromWindow(windowToken , 0)
    }
}