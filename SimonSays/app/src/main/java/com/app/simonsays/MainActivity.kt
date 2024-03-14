package com.app.simonsays

import android.graphics.Color
import android.graphics.Paint
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var soundPool : SoundPool
    private var sequenceList : MutableList<Int> = mutableListOf()
    private var soundList : MutableList<Int> = mutableListOf()
    private var userInput : MutableList<Int> = mutableListOf()
    private var isGameRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val brush : Paint = Paint()
        brush.color = Color.RED
        brush.style = Paint.Style.STROKE
        brush.strokeWidth = 10f

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
        Log.d("simon", "Red: $redSound , Green: $greenSound , Blue: $blueSound , Yellow: $yellowSound")
        soundList.add(redSound)
        soundList.add(greenSound)
        soundList.add(blueSound)
        soundList.add(yellowSound)

        val startButton : Button = findViewById(R.id.startButton)

        val redButton : Button = findViewById(R.id.redButton)
        val greenButton : Button = findViewById(R.id.greenButton)
        val blueButton : Button = findViewById(R.id.blueButton)
        val yellowButton : Button = findViewById(R.id.yellowButton)

        startButton.setOnClickListener {
            startRound()
        }

        redButton.setOnClickListener {
            if (!isGameRunning) {
                playSound(redSound)
            }
            else {
                userInput.add(1)
                checkInput()
            }

        }
        greenButton.setOnClickListener {
            if (!isGameRunning) {
                playSound(greenSound)
            }
            else {
                userInput.add(2)
                checkInput()
            }

        }
        blueButton.setOnClickListener {
            if (!isGameRunning) {
                playSound(blueSound)
            }
            else {
                userInput.add(3)
                checkInput()
            }

        }
        yellowButton.setOnClickListener {
            if (!isGameRunning) {
                playSound(yellowSound)
            }
            else {
                userInput.add(4)
                checkInput()
            }

        }
    }

    private fun playSound(soundID : Int) {
        soundPool.play(soundID, 1f, 1f, 1, 0, 1f)
    }

    private fun playSequence() {
        sequenceList.forEachIndexed { index, color ->
            Handler().postDelayed({
                playSound(soundList[color - 1])
                // You can add visual feedback here if needed
            }, index * 2000)
        }
    }

    private fun startRound(listLength: Int = 4) {
        isGameRunning = true
        sequenceList.clear()
        userInput.clear()

        for (i in 0 until listLength) {
            sequenceList.add((1..4).random())
        }

        Log.d("simon", "This round's list: $sequenceList")
        playSequence()
    }

    private fun checkInput() {
        for (i in userInput.indices) {
            if (sequenceList[i] != userInput[i]) {
                Log.d("simon", "You lost")
                isGameRunning = false
                return
            }
        }

        isGameRunning = false
    }
}
