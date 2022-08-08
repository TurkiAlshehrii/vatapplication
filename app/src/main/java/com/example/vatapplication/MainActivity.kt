package com.example.vatapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.vatapplication.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    // binding object
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root // reference layout file
        setContentView(view) // link layout


        binding.btnCalculateVat.setOnClickListener {
            calculateVat()
        }// End setOnClickListener


    }// End onCreate

    private fun calculateVat() {
        val stringVatet = binding.etTotalCost.text.toString()
        val cost = stringVatet.toDouble()

        // Radio group
        val selectedID = binding.rgVatOption.checkedRadioButtonId

        val vatPercentage = when(selectedID){
            R.id.rb_vat_10 -> 0.10
            R.id.rb_vat_15 -> 0.15
            else -> 0.20
        } // End when

        var vat = vatPercentage * cost
        var total = cost + vat
        // Switch value
        val roundVat = binding.switchRoundUp.isChecked

        if (roundVat){
            total = kotlin.math.ceil(total)
        }

        // Total Formatting
        val formatedTotal = NumberFormat.getCurrencyInstance().format(total)
        binding.txtCostTotal.text = getString(R.string.total_amount, formatedTotal)

    }

}// End MainActivity