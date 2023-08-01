package com.example.mediaapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mediaapp.R.*

class CalcActivity : AppCompatActivity() {

    private lateinit var etBoughtStockAmount: EditText
    private lateinit var etBoughtStockPrice: EditText
    private lateinit var etCurrentStockPrice: EditText
    private lateinit var btnCalculate: Button
    private lateinit var calcResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_calc)

        etBoughtStockAmount = findViewById(id.etBoughtStockAmount)
        etBoughtStockPrice = findViewById(id.etBoughtStockPrice)
        etCurrentStockPrice = findViewById(id.etCurrentStockPrice)
        btnCalculate = findViewById(id.btnCalculate)
        calcResult = findViewById(id.etStockCalcResult)

        btnCalculate.setOnClickListener {
            calculateStocksToBuy()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        menu.removeItem(R.id.menu_calc)
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

    private fun calculateStocksToBuy() {
        val boughtStockAmount = etBoughtStockAmount.text.toString().toIntOrNull() ?: 0
        val boughtStockPrice = etBoughtStockPrice.text.toString().toDoubleOrNull() ?: 0.0
        val currentStockPrice = etCurrentStockPrice.text.toString().toDoubleOrNull() ?: 0.0

        if (boughtStockAmount <= 0 || boughtStockPrice <= 0.0 || currentStockPrice <= 0.0) {
            calcResult.text = getString(string.invalid_input_please_enter_valid_values)
        } else {
            val totalInvested = boughtStockAmount * boughtStockPrice
            val currentTotalValue = boughtStockAmount * currentStockPrice
            val stocksToBuy = (totalInvested - currentTotalValue) / currentStockPrice
            val stocksToBuyPrice = stocksToBuy * currentStockPrice

            if (stocksToBuy <= 0) {
                calcResult.text = getString(string.congratulations_stocks)
            } else {
                calcResult.text = "You need to buy $stocksToBuy stocks at $stocksToBuyPrice to bring your average down"


            }
        }
    }
}