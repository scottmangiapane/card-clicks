package com.scottmangiapane.cardclicks;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.scottmangiapane.cardclicks.game.Game;

public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_help);

        findViewById(R.id.button_refresh).setOnClickListener(view -> refresh());
        findViewById(R.id.button_return_to_menu).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
        });
        refresh();

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
                overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
            }
        });
    }

    private void refresh() {
        Game game = new Game(3);
        ((ImageView) findViewById(R.id.help_card_1)).setImageResource(game.get(0).drawable);
        ((ImageView) findViewById(R.id.help_card_2)).setImageResource(game.get(1).drawable);
        ((ImageView) findViewById(R.id.help_card_3)).setImageResource(game.get(2).drawable);
    }
}
