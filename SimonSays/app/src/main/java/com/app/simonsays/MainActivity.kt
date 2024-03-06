package com.app.simonsays

import android.R.integer
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.newFixedThreadPoolContext


class MainActivity : AppCompatActivity() {

    private lateinit var soundPool : SoundPool
    private var list : MutableList<Int> = mutableListOf()
    private var hasStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        val builder = SoundPool.Builder()
        builder.setAudioAttributes(audioAttributes).setMaxStreams(1)
        soundPool = builder.build()

        val redSound = soundPool.load(this, R.raw.red_sound, 1)
        val greenSound = soundPool.load(this, R.raw.green_sound, 1)
        val blueSound = soundPool.load(this, R.raw.blue_sound, 1)
        val yellowSound = soundPool.load(this, R.raw.yellow_sound, 1)

        val startButton : Button = findViewById(R.id.startButton)

        val redButton : Button = findViewById(R.id.redButton)
        val greenButton : Button = findViewById(R.id.greenButton)
        val blueButton : Button = findViewById(R.id.blueButton)
        val yellowButton : Button = findViewById(R.id.yellowButton)

        startButton.setOnClickListener {
            startRound()
        }

        redButton.setOnClickListener {
            playSound(redSound)
        }
        greenButton.setOnClickListener {
            playSound(greenSound)
        }
        blueButton.setOnClickListener {
            playSound(blueSound)
        }
        yellowButton.setOnClickListener {
            playSound(yellowSound)
        }
    }

    private fun playSound(soundID : Int) {
        soundPool.play(soundID, 1f, 1f, 1, 0, 1f)
    }

    private fun startRound(listLength: Int = 4) {
        hasStarted = true

        for (i in 0..listLength) {
            list.add((0..3).random())
        }
        Log.d("simon", "This round's list: $list")

        list.forEach {
            playSound(it)

        }

    }
}