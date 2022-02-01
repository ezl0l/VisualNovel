package com.ezlol.visualnovella;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView appNameTextView;
    Button startGameBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameBtn = findViewById(R.id.start_game_btn);
        startGameBtn.setOnClickListener(this);

        appNameTextView = findViewById(R.id.app_name_textview);

        Animation a = AnimationUtils.loadAnimation(this, R.anim.deal_with_it);
        a.reset();

        appNameTextView.clearAnimation();
        appNameTextView.startAnimation(a);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.start_game_btn) {
            startActivity(new Intent(this, GameActivity.class));
        }
    }
}