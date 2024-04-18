package com.scottmangiapane.cardclicks

import android.app.Application
import androidx.preference.PreferenceManager

class OnAppStart : Application() {
    override fun onCreate() {
        super.onCreate()
        val context = applicationContext
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sp.edit()
        editor.putInt("launch_count", sp.getInt("launch_count", 0) + 1)
        editor.apply()
    }
}
