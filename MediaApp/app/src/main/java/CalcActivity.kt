package com.example.mediaapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalcActivity : AppCompatActivity() {

    private lateinit var etBoughtStockAmount: EditText
    private lateinit var etBoughtStockPrice: EditText
    private lateinit var etCurrentStockPrice: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        etBoughtStockAmount = findViewById(R.id.etBoughtStockAmount)
        etBoughtStockPrice = findViewById(R.id.etBoughtStockPrice)
        etCurrentStockPrice = findViewById(R.id.etCurrentStockPrice)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)

        btnCalculate.setOnClickListener {
            calculateStocksToBuy()
        }
    }

    private fun calculateStocksToBuy() {
        val boughtStockAmount = etBoughtStockAmount.text.toString().toIntOrNull() ?: 0
        val boughtStockPrice = etBoughtStockPrice.text.toString().toDoubleOrNull() ?: 0.0
        val currentStockPrice = etCurrentStockPrice.text.toString().toDoubleOrNull() ?: 0.0

        if (boughtStockAmount <= 0 || boughtStockPrice <= 0.0 || currentStockPrice <= 0.0) {
            tvResult.text = "Invalid input. Please enter valid values."
        } else {
            val totalInvested = boughtStockAmount * boughtStockPrice
            val currentTotalValue = boughtStockAmount * currentStockPrice
            val stocksToBuy = (totalInvested - currentTotalValue) / currentStockPrice

            if (stocksToBuy <= 0) {
                tvResult.text = "Congratulations! You don't need to buy any more stocks."
            } else {
                tvResult.text = "You need to buy $stocksToBuy stocks at the current price."
            }
        }
    }
}