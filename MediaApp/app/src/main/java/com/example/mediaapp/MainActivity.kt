package com.example.mediaapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //use menu/main.xml for menu instead of default menu, triggered when user clicks menu icon
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        menu.removeItem(R.id.menu_home)
        return true
    }
    //this handles when the user clicks an option in the menu/ use ID to verify activity and navigate to Activity with Intents
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