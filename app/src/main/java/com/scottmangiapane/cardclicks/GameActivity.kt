package com.scottmangiapane.cardclicks

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import java.util.Arrays
import java.util.Locale
import java.util.concurrent.TimeUnit
import java.util.function.Consumer
import java.util.stream.Collectors
import androidx.core.content.edit

class GameActivity : AppCompatActivity() {
    private lateinit var cards: List<ImageView>
    private val selectedCards: MutableList<ImageView> = ArrayList()
    private lateinit var vm: CardViewModel
    private var gameTimer: CountDownTimer? = null
    private var showSetsTimer: CountDownTimer? = null
    private val sp: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(applicationContext) }
    private lateinit var buttonPauseRestart: TextView
    private lateinit var buttonStopExit: TextView
    private lateinit var scoreView: TextView
    private lateinit var timeCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        cards = Arrays
            .stream(CARD_IDS)
            .mapToObj { id: Int -> findViewById<View>(id) as ImageView }
            .collect(Collectors.toList())
        cards.forEach(Consumer { b: ImageView -> b.setOnClickListener { v: View -> onButtonClicked(v as ImageView) } })
        vm = ViewModelProvider(this, CardViewModelFactory(CARD_IDS.size, GAME_DURATION))[CardViewModel::class.java]
        buttonPauseRestart = findViewById(R.id.button_pause_restart)
        buttonPauseRestart.setOnClickListener { pauseGame() }
        buttonStopExit = findViewById(R.id.button_stop_exit)
        buttonStopExit.setOnClickListener { endGame() }
        scoreView = findViewById(R.id.final_score)
        timeCount = findViewById(R.id.time_count)
        refreshViews()
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (vm.isGameRunning) pauseGame()
            }
        })
    }

    public override fun onPause() {
        super.onPause()
        gameTimer?.cancel()
        showSetsTimer?.cancel()
    }

    public override fun onResume() {
        super.onResume()
        if (!vm.isGameRunning) {
            endGame()
            return
        }
        gameTimer = object : CountDownTimer(vm.timeRemaining, 200) {
            override fun onTick(milliseconds: Long) {
                vm.timeRemaining = milliseconds
                timeCount.text = String.format(
                    Locale.getDefault(),
                    "%d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(milliseconds),
                    TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60
                )
            }

            override fun onFinish() {
                endGame()
            }
        }.start()
    }

    override fun recreate() {
        super.recreate()
        vm.reset()
    }

    private fun endGame() {
        vm.isGameRunning = false
        gameTimer?.cancel()
        buttonPauseRestart.setBackgroundColor(getColor(R.color.colorDanger))
        buttonPauseRestart.text = getString(R.string.restart)
        buttonPauseRestart.setOnClickListener { recreate() }
        buttonStopExit.text = getString(R.string.exit)
        buttonStopExit.setOnClickListener { finish() }
        selectedCards.clear()
        scoreView.text = String.format(
            Locale.getDefault(),
            "%s · %s",
            getString(R.string.score, vm.score),
            getString(R.string.high, sp.getInt("score_1", 0))
        )
        timeCount.setText(R.string.empty_timer)
        showSets()
    }

    private fun onButtonClicked(button: ImageView) {
        if (!vm.isGameRunning) return
        if (selectedCards.contains(button)) {
            selectedCards.remove(button)
            refreshViews()
            return
        }
        selectedCards.add(button)
        refreshViews()
        if (selectedCards.size == 3) {
            if (vm.game.isSet(
                    cards.indexOf(selectedCards[0]),
                    cards.indexOf(selectedCards[1]),
                    cards.indexOf(selectedCards[2])
                )
            ) {
                val scoreText = getString(R.string.score, ++vm.score)
                scoreView.text = scoreText
                saveScore()
                vm.game.rotateCards(
                    cards.indexOf(selectedCards[0]),
                    cards.indexOf(selectedCards[1]),
                    cards.indexOf(selectedCards[2])
                )
            }
            selectedCards.clear()
            refreshViews()
        }
    }

    private fun pauseGame() {
        val intent = Intent(this@GameActivity, PausedActivity::class.java)
        startActivity(intent)
    }

    private fun refreshViews() {
        for (i in cards.indices) {
            cards[i].alpha = if (selectedCards.isEmpty()) 1f else .5f
            cards[i].setImageResource(vm.game[i].drawable)
        }
        for (button in selectedCards) button.alpha = 1f
        scoreView.text = getString(R.string.score, vm.score)
    }

    private fun saveScore() {
        sp.edit {
            val scores: MutableList<Int> = ArrayList()
            scores.add(sp.getInt("score_1", 0))
            scores.add(sp.getInt("score_2", 0))
            scores.add(sp.getInt("score_3", 0))
            scores.add(vm.score)
            scores.sortWith { a: Int, b: Int -> b - a }
            putInt("score_1", scores[0])
            putInt("score_2", scores[1])
            putInt("score_3", scores[2])
        }
    }

    private fun showSets() {
        showSetsTimer = object : CountDownTimer(1000, 1000) {
            private val sets = vm.game.getSets()
            private var index = 0
            override fun onTick(milliseconds: Long) {
                for (button in cards) button.alpha = .5f
                val set = sets[index++ % sets.size]
                for (index in set) cards[index].alpha = 1f
            }

            override fun onFinish() {
                showSetsTimer?.start()
            }
        }.start()
    }

    companion object {
        private val CARD_IDS = intArrayOf(
            R.id.card_01, R.id.card_02, R.id.card_03, R.id.card_04,
            R.id.card_05, R.id.card_06, R.id.card_07, R.id.card_08,
            R.id.card_09, R.id.card_10, R.id.card_11, R.id.card_12
        )
        private const val GAME_DURATION: Long = 121000
    }
}
