package com.eloops.feedingrate

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.text.style.SuperscriptSpan
import android.widget.EditText
import java.math.BigDecimal
import java.math.RoundingMode

fun Double.addSuperSuffix(suffix: String): CharSequence {
    val fullText = "$this$suffix"

    return SpannableStringBuilder(fullText).apply {
        setSpan(
            SuperscriptSpan(),
            fullText.length - suffix.length,
            fullText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(
            RelativeSizeSpan(0.5f),
            fullText.length - suffix.length,
            fullText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}

fun EditText.clear() {
    this.text = null
}

fun Double.round(scale: Int) =
    BigDecimal(this).setScale(scale, RoundingMode.HALF_EVEN).toDouble()
