package com.example.flagquiz.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import android.view.ContextThemeWrapper

@SuppressLint("NewApi")
fun Context.setAppLanguage(languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)

    val resources = this.resources
    val configuration = resources.configuration
    configuration.setLocale(locale)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        this.createConfigurationContext(configuration)
    }

    resources.updateConfiguration(configuration, resources.displayMetrics)

    if (this is Activity) {
        this.recreate()
    }
}