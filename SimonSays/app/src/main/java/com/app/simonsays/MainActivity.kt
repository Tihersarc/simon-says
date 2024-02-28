package com.app.simonsays

import android.R.integer
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var soundPool : SoundPool
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

        val redSound = soundPool.load(this, R.raw.your_audio_file, 1);
        val greenSound = soundPool.load(this, R.raw.your_audio_file, 1);
        val blueSound = soundPool.load(this, R.raw.your_audio_file, 1);
        val yellowSound = soundPool.load(this, R.raw.your_audio_file, 1);


        val redButton : Button = findViewById(R.id.redButton)
        val greenButton : Button = findViewById(R.id.greenButton)
        val blueButton : Button = findViewById(R.id.blueButton)
        val yellowButton : Button = findViewById(R.id.yellowButton)

        redButton.setOnClickListener {
            playSound()
        }
    }

    fun nextSound() {

    }

    fun playSound(soundID : Int) {
        soundPool.play(soundID, 1f, 1f, 1, 0, 1f)
    }
}