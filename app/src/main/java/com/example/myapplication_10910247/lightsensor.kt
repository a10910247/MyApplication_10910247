package com.example.myapplication_10910247

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication_10910247.databinding.ActivityMainBinding

class lightsensor : AppCompatActivity() {

    private lateinit var light: Button
    private lateinit var back123: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_lightsensor)

        val startServiceButton: Button = findViewById(R.id.light)
        startServiceButton.setOnClickListener {
            val serviceIntent= Intent (this,myservice::class.java);
            startService(serviceIntent)

        val startActivityButton: Button=findViewById(R.id.back123)
            startActivityButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

        }

    }

    /*override fun onBackPressed() {
        super.onBackPressed()*/


    }
}