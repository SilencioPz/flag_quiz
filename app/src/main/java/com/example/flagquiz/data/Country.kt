package com.example.flagquiz.data

import android.content.Context
import com.example.flagquiz.R

data class Country(
    val name: String = "", // Será definido no Repository
    val nameEn: String,
    val namePt: String,
    val nameEs: String,
    val flagResId: Int,
    val region: String
) {

    fun getName(language: String): String {
        return when (language.lowercase()) {
            "pt" -> namePt
            "es" -> nameEs
            else -> nameEn
        }
    }

    fun getFlagDescription(context: Context): String {
        return context.getString(R.string.flag_description, this.name)
    }
}