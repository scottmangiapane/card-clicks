package com.scottmangiapane.cardclicks

import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {
    private lateinit var highScores: Array<TextView>
    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        highScores = arrayOf(
            findViewById(R.id.score_1),
            findViewById(R.id.score_2),
            findViewById(R.id.score_3)
        )
        sp = PreferenceManager.getDefaultSharedPreferences(
            applicationContext
        )
        findViewById<View>(R.id.button_help).setOnClickListener { launchHelp() }
        findViewById<View>(R.id.button_play).setOnClickListener { launchGame() }
        renderHighScores()
        showRatingDialog()
    }

    public override fun onResume() {
        super.onResume()
        renderHighScores()
    }

    private fun renderHighScores() {
        highScores[0].text = if (sp.getInt("score_1", 0) == 1) getString(
            R.string.set,
            sp.getInt("score_1", 0)
        ) else getString(R.string.sets, sp.getInt("score_1", 0))
        highScores[1].text = if (sp.getInt("score_2", 0) == 1) getString(
            R.string.set,
            sp.getInt("score_2", 0)
        ) else getString(R.string.sets, sp.getInt("score_2", 0))
        highScores[2].text = if (sp.getInt("score_3", 0) == 1) getString(
            R.string.set,
            sp.getInt("score_3", 0)
        ) else getString(R.string.sets, sp.getInt("score_3", 0))
    }

    private fun launchHelp() {
        val intent = Intent(this@MainActivity, HelpActivity::class.java)
        startActivity(intent)
    }

    private fun launchGame() {
        val intent = Intent(this@MainActivity, GameActivity::class.java)
        startActivity(intent)
    }

    private fun showRatingDialog() {
        if (sp.getInt("launch_count", 0) == 10) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.rate_card_clicks)
                .setMessage(R.string.rate_message)
                .setPositiveButton(R.string.sure) { _: DialogInterface?, _: Int ->
                    try {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                "market://details?id=$packageName".toUri()
                            )
                        )
                    } catch (_: ActivityNotFoundException) {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                ("https://play.google.com/store/apps/details?id="
                                        + packageName).toUri()
                            )
                        )
                    }
                }
                .setNegativeButton(R.string.no_thanks) { _: DialogInterface?, _: Int -> }
                .create().show()
        }
    }
}