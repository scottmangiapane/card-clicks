package com.scottmangiapane.cardcliques;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class HelpButton extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_help);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#303030"));
            getWindow().setNavigationBarColor(Color.parseColor("#303030"));
        }
        findViewById(R.id.button_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });
        findViewById(R.id.button_return_to_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
            }
        });
        refresh();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.transition.slide_left_1, R.transition.slide_left_2);
        return;
    }

    private void refresh() {
        Clique clique = new Clique(1, 3);
        ImageView[] gridDraw = new ImageView[]{
                (ImageView) findViewById(R.id.imageView_0),
                (ImageView) findViewById(R.id.imageView_1),
                (ImageView) findViewById(R.id.imageView_2)};
        for (int i = 0; i < clique.width(); i++)
            clique.setNewCard(0, i);
        clique.refreshAllValues();
        clique.setNewCardCheckSetPossible(0, (int) (Math.random() * 2));
        for (int i = 0; i < clique.width(); i++) {
            gridDraw[i].setColorFilter(null);
            if (clique.cards[0][i].drawable == 0)
                gridDraw[i].setImageResource(R.drawable.card_empty);
            else
                gridDraw[i].setImageResource(clique.cards[0][i].drawable);
        }
    }
}
