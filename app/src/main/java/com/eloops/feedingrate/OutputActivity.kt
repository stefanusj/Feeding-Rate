package com.eloops.feedingrate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_output.*

class OutputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output)

        setSupportActionBar(toolbar)

        // Mengambil semua input dari intent
        val mass = intent.getDoubleExtra(INTENT_MASS, 0.toDouble())
        val amount = intent.getDoubleExtra(INTENT_AMOUNT, 0.toDouble())
        val frequency = intent.getDoubleExtra(INTENT_FREQUENCY, 0.toDouble())
        val temperature = intent.getDoubleExtra(INTENT_TEMPERATURE, 0.toDouble())
        val oxygen = intent.getDoubleExtra(INTENT_OXYGEN, 0.toDouble())

        // Hitung 'Feeding Rate' dengan perhitungan fuzzy
        val fuzzy = MyFuzzyClass(this)
        val feeding = fuzzy.getFuzzyFeeding(temperature, oxygen)
        tvOutputFeedingRate.text =
            feeding.round(2).addSuperSuffix(getString(R.string.suffix_feeding_rate))

        // Hitung jumlah pakan yang diberikan setiap saat
        val amountFood = ((mass * amount) * (0.03)) / frequency
        tvOutputAmountFood.text =
            amountFood.round(1).addSuperSuffix(getString(R.string.suffix_amount_fish_food))
    }

    companion object {
        const val INTENT_MASS = "mass"
        const val INTENT_AMOUNT = "amount"
        const val INTENT_FREQUENCY = "frequency"
        const val INTENT_TEMPERATURE = "temperature"
        const val INTENT_OXYGEN = "oxygen"
    }
}
