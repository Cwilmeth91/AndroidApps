package com.example.mediaapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        menu.removeItem(R.id.menu_game)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // We have 4 different activities to Handle via Menus
        when (item.itemId) {
            R.id.menu_game -> {
                val audioIntent = Intent(this, AudioActivity::class.java)
                startActivity(audioIntent)
            }
            R.id.menu_pictures -> {
                val pictureIntent = Intent(this, PictureActivity::class.java)
                startActivity(pictureIntent)
            }
            R.id.menu_calc -> {
                val calcIntent = Intent(this, CalcActivity::class.java)
                startActivity(calcIntent)
            }
            R.id.menu_home -> {
                val homeIntent = Intent(this, MainActivity::class.java)
                startActivity(homeIntent)
            }
        }
        return true
    }

    private fun onGuessButtonClicked() {
        val randNum = Random()
        val random = randNum.nextInt(20) + 1
        result = findViewById(R.id.randoNum)

        val userChoice = number.text.toString().trim().toIntOrNull()

        if (userChoice == null) {
            Toast.makeText(this, "Please enter a valid number.", Toast.LENGTH_SHORT).show()
            return
        }

        if (userChoice < 1 || userChoice > 20) {
            Toast.makeText(this, "Please guess a number between 1 and 20.", Toast.LENGTH_SHORT).show()
            return
        }

        result.text = "The Random Number this time was: $random"

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