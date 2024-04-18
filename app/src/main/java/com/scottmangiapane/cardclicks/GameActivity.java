package com.scottmangiapane.cardclicks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class GameActivity extends AppCompatActivity {
    private static final int[] CARD_IDS = {
            R.id.card_01, R.id.card_02, R.id.card_03, R.id.card_04,
            R.id.card_05, R.id.card_06, R.id.card_07, R.id.card_08,
            R.id.card_09, R.id.card_10, R.id.card_11, R.id.card_12};
    private static final long GAME_DURATION = 121000;

    private final Random generator = new Random();

    private List<ImageView> cards;
    private List<ImageView> selectedCards;
    private CardViewModel vm;

    private CountDownTimer timer;
    private SharedPreferences sp;

    private TextView buttonPauseRestart;
    private TextView buttonStopExit;
    private TextView scoreView;
    private TextView timeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);

        cards = Arrays
                .stream(CARD_IDS)
                .mapToObj(id -> (ImageView) findViewById(id))
                .collect(Collectors.toList());
        cards.forEach(b -> b.setOnClickListener(v -> onButtonClicked((ImageView) v)));
        selectedCards = new ArrayList<>();
        vm = new ViewModelProvider(this, new CardViewModelFactory(CARD_IDS.length, GAME_DURATION))
                .get(CardViewModel.class);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        buttonPauseRestart = findViewById(R.id.button_pause_restart);
        buttonPauseRestart.setOnClickListener(v -> pauseGame());
        buttonStopExit = findViewById(R.id.button_stop_exit);
        buttonStopExit.setOnClickListener(v -> endGame());
        scoreView = findViewById(R.id.final_score);
        timeCount = findViewById(R.id.time_count);

        refreshViews();
        if (!vm.isGameRunning)
            endGame();

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (vm.isGameRunning)
                    pauseGame();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (timer != null)
            timer.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (vm.isGameRunning) {
            timer = new CountDownTimer(vm.timeRemaining, 1000) {
                public void onTick(long milliseconds) {
                    vm.timeRemaining = milliseconds;
                    timeCount.setText(String.format(
                            Locale.getDefault(),
                            "%d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(milliseconds),
                            TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60));
                }

                public void onFinish() {
                    endGame();
                }
            }.start();
        }
    }

    @Override
    public void recreate () {
        super.recreate();
        vm.reset();
    }

    private void endGame() {
        vm.isGameRunning = false;
        if (timer != null)
            timer.cancel();

        buttonPauseRestart.setBackgroundColor(getColor(R.color.colorDanger));
        buttonPauseRestart.setText(getString(R.string.restart));
        buttonPauseRestart.setOnClickListener(v -> this.recreate());
        buttonStopExit.setText(getString(R.string.exit));
        buttonStopExit.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.transition.slide_right_1, R.transition.slide_right_2);
        });
        selectedCards.clear();

        for (ImageView button : cards)
            button.setAlpha(.5f);
        List<Integer[]> sets = vm.game.getSets();
        Integer[] set = sets.get(generator.nextInt(sets.size()));
        for (int index : set)
            cards.get(index).setAlpha(1f);

        scoreView.setText(String.format(
                Locale.getDefault(),
                "%s · %s",
                getString(R.string.score, vm.score),
                getString(R.string.high, sp.getInt("score_1", 0))));
        timeCount.setText(R.string.empty_timer);
    }

    private void onButtonClicked(ImageView button) {
        if (!vm.isGameRunning) return;

        if (selectedCards.contains(button)) {
            selectedCards.remove(button);
            refreshViews();
            return;
        }
        selectedCards.add(button);
        refreshViews();

        if (selectedCards.size() == 3) {
            if (vm.game.isSet(
                    cards.indexOf(selectedCards.get(0)),
                    cards.indexOf(selectedCards.get(1)),
                    cards.indexOf(selectedCards.get(2)))) {
                String scoreText = getString(R.string.score, ++vm.score);
                scoreView.setText(scoreText);
                saveScore();
                vm.game.rotateCards(
                        cards.indexOf(selectedCards.get(0)),
                        cards.indexOf(selectedCards.get(1)),
                        cards.indexOf(selectedCards.get(2)));
            }
            selectedCards.clear();
            refreshViews();
        }
    }

    private void pauseGame() {
        Intent intent = new Intent(GameActivity.this, PausedActivity.class);
        startActivity(intent);
    }

    private void refreshViews() {
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).setAlpha((selectedCards.isEmpty()) ? 1f : .5f);
            cards.get(i).setImageResource(vm.game.get(i).drawable);
        }
        for (ImageView button : selectedCards)
            button.setAlpha(1f);
        scoreView.setText(getString(R.string.score, vm.score));
    }

    private void saveScore() {
        SharedPreferences.Editor editor = sp.edit();
        List<Integer> scores = new ArrayList<>();
        scores.add(sp.getInt("score_1", 0));
        scores.add(sp.getInt("score_2", 0));
        scores.add(sp.getInt("score_3", 0));
        scores.add(vm.score);
        scores.sort((a, b) -> b - a);
        editor.putInt("score_1", scores.get(0));
        editor.putInt("score_2", scores.get(1));
        editor.putInt("score_3", scores.get(2));
        editor.apply();
    }
}
