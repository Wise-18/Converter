package com.example.converter

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    private val viewModel: CurrencyConverterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val amountInput = findViewById<EditText>(R.id.amountInput)
        val fromCurrency = findViewById<Spinner>(R.id.fromCurrency)
        val toCurrency = findViewById<Spinner>(R.id.toCurrency)
        val convertButton = findViewById<Button>(R.id.convertButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        viewModel.currencies.observe(this, Observer { currencies ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            fromCurrency.adapter = adapter
            toCurrency.adapter = adapter
        })

        viewModel.result.observe(this, Observer {
            resultText.text = it
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        convertButton.setOnClickListener {
            val amount = amountInput.text.toString().toDoubleOrNull() ?: 0.0
            val from = fromCurrency.selectedItem as? String ?: "USD"
            val to = toCurrency.selectedItem as? String ?: "USD"
            viewModel.convert(from, to, amount)
        }
    }
}