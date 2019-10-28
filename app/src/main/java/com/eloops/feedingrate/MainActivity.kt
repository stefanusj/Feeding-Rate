package com.eloops.feedingrate

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        btnCalculate.setOnClickListener {

            // Ambil semua input
            val mass = etInputFishMass.text.toString().toDoubleOrNull()
            val amount = etInputFishAmount.text.toString().toDoubleOrNull()
            val frequency = etInputFrequencyFeed.text.toString().toDoubleOrNull()
            val temperature = etInputPoolTemperature.text.toString().toDoubleOrNull()
            val oxygen = etInputPoolOxygenLevel.text.toString().toDoubleOrNull()

            // Cek apakah terdapat input yang kosong
            if ((mass == null) or (amount == null) or (frequency == null) or (temperature == null) or (oxygen == null)) {
                toast(R.string.error_input)
                return@setOnClickListener
            }

            startActivity<OutputActivity>(
                OutputActivity.INTENT_MASS to mass,
                OutputActivity.INTENT_AMOUNT to amount,
                OutputActivity.INTENT_FREQUENCY to frequency,
                OutputActivity.INTENT_TEMPERATURE to temperature,
                OutputActivity.INTENT_OXYGEN to oxygen
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menuReset -> {
                // Reset semua input
                etInputFishMass.clear()
                etInputFishAmount.clear()
                etInputPoolTemperature.clear()
                etInputPoolOxygenLevel.clear()
                etInputFrequencyFeed.clear()
                toast(R.string.feedback_menu_reset)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
