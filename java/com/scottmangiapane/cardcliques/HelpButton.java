package com.scottmangiapane.cardcliques;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

    }
}
