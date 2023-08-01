package com.example.mediaapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class PictureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        menu.removeItem(R.id.menu_pictures)
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
}