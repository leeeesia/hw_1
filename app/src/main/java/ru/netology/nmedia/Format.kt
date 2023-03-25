package ru.netology.nmedia

import java.math.RoundingMode
import java.text.DecimalFormat

object Format {
    fun value(value: Int): String {
        when (value) {
            in 0..999 -> return value.toString()
            in 1000..9999 -> {
                val df = DecimalFormat("#.#")
                df.roundingMode = RoundingMode.DOWN
                return df.format(value / 1000.0).toString() + "K"
            }
            in 10000..999999 -> {
                val df = DecimalFormat("#")
                df.roundingMode = RoundingMode.DOWN
                return df.format(value / 1000.0).toString() + "К"
            }
            else -> {
                val df = DecimalFormat("#.#")
                df.roundingMode = RoundingMode.DOWN
                return df.format(value / 1_000_000.0).toString() + "M"
            }
        }
    }
}