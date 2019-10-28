package com.eloops.feedingrate

import android.content.Context
import net.sourceforge.jFuzzyLogic.FIS
import org.jetbrains.anko.toast

class MyFuzzyClass(private val context: Context) {

    fun getFuzzyFeeding(temperature: Double, oxygen: Double): Double {

        // Load file 'FCL' dari assets folder
        val inputStream = context.assets.open(FILE_NAME_FCL)

        // Gagal meload file 'FCL' ?
        FIS.load(inputStream, true)?.let {
            // Berhasil di load

            // Set inputs
            it.setVariable(INPUT_TEMPERATURE, temperature)
            it.setVariable(INPUT_OXYGEN, oxygen)

            // Evaluate
            it.evaluate()

            return it.getVariable(OUTPUT_FEEDING_PERCENT).value
        } ?: run {
            // Gagal di load

            context.toast(
                String.format(
                    context.getString(R.string.error_fail_load_file),
                    inputStream
                )
            )

            return 0.toDouble()
        }
    }

    companion object {
        // Berdasarkan pada file 'FCL'
        const val INPUT_TEMPERATURE = "temperature"
        const val INPUT_OXYGEN = "oxygen"
        const val OUTPUT_FEEDING_PERCENT = "feeding"

        // Nama file 'FCL' di folder assets
        const val FILE_NAME_FCL = "tipper.fcl"
    }
}