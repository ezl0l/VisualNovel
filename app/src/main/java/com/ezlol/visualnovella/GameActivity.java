package com.ezlol.visualnovella;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout dialogLayout;
    Engine engine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        dialogLayout = findViewById(R.id.game_dialog_layout);

        dialogLayout.setOnClickListener(this);

        engine = SL.parse(Main.g());

        try {
            engine.init();
        } catch (UnknownCommandException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.game_dialog_layout:

                break;
        }
    }
}
