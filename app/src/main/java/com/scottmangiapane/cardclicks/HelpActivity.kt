package com.scottmangiapane.cardclicks

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.scottmangiapane.cardclicks.game.Game

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        findViewById<View>(R.id.button_refresh).setOnClickListener { refresh() }
        findViewById<View>(R.id.button_return_to_menu).setOnClickListener { finish() }
        refresh()
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    private fun refresh() {
        val game = Game(3)
        (findViewById<View>(R.id.help_card_1) as ImageView).setImageResource(
            game[0].drawable
        )
        (findViewById<View>(R.id.help_card_2) as ImageView).setImageResource(
            game[1].drawable
        )
        (findViewById<View>(R.id.help_card_3) as ImageView).setImageResource(
            game[2].drawable
        )
    }
}
