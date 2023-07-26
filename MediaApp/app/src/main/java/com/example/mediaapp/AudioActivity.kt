package com.example.mediaapp

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class AudioActivity : AppCompatActivity() {

    private lateinit var number: EditText
    private lateinit var guessBtn: Button
    private lateinit var result: TextView
    private val success: MediaPlayer by lazy { MediaPlayer.create(this, R.raw.success) }
    private val wrong: MediaPlayer by lazy { MediaPlayer.create(this, R.raw.wrong) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)

        number = findViewById(R.id.number)
        guessBtn = findViewById(R.id.guessBtn)

        guessBtn.setOnClickListener {
            onGuessButtonClicked()
        }
    }

    private fun onGuessButtonClicked() {
        val randNum = Random()
        val random = randNum.nextInt(5) + 1

        val userChoice = number.text.toString().trim().toIntOrNull()

        if (userChoice == null) {
            Toast.makeText(this, "Please enter a valid number.", Toast.LENGTH_SHORT).show()
            return
        }

        if (userChoice < 1 || userChoice > 5) {
            Toast.makeText(this, "Please guess a number between 1 and 5.", Toast.LENGTH_SHORT).show()
            return
        }

        if (userChoice == random) {
            if (success.isPlaying) {
                success.seekTo(0)
            } else {
                success.start()
            }
            Toast.makeText(this, "Congratulations, you guessed the number correctly", Toast.LENGTH_SHORT).show()
        } else {
            if (wrong.isPlaying) {
                    wrong.seekTo(0)
                } else {
                    wrong.start()
                }
            Toast.makeText(this, "Sorry, better luck next time!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        success.release()
        wrong.release()
    }
}