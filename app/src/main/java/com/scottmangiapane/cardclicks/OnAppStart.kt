package com.scottmangiapane.cardclicks

import android.app.Application
import androidx.preference.PreferenceManager
import androidx.core.content.edit

class OnAppStart : Application() {
    override fun onCreate() {
        super.onCreate()
        val context = applicationContext
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        sp.edit {
            putInt("launch_count", sp.getInt("launch_count", 0) + 1)
        }
    }
}
