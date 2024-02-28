package com.app.simonsays

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val red : Button = findViewById(R.id.redButton)
        val green : Button = findViewById(R.id.greenButton)
        val blue : Button = findViewById(R.id.blueButton)
        val yellow : Button = findViewById(R.id.yellowButton)

        
    }
}