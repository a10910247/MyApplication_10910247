package com.example.myapplication_10910247

import android.content.Intent
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.myapplication_10910247.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private lateinit var textView1:TextView
    private lateinit var button:Button
    private lateinit var button2:Button
    private lateinit var button3:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)



        binding.button.setOnClickListener {
            Intent (this,location::class.java);
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            Intent (this,lightsensor::class.java);
            startActivity(intent)
        }

        binding.button3.setOnClickListener {
            Intent (this,Gsensor::class.java);
            startActivity(intent)
        }




    }
}