package com.ezlol.visualnovella;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, OnSceneChangeListener {
    LinearLayout dialogLayout;
    Engine engine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        dialogLayout = findViewById(R.id.game_dialog_layout);

        dialogLayout.setOnClickListener(this);

        engine = SL.parse(Main.g());

        engine.setOnSceneChangeListener(this);

        try {
            engine.init();
        } catch (UnknownCommandException | IOException | SceneNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.game_dialog_layout:
                engine.
                break;
        }
    }

    @Override
    public void onSceneChange(SL.Scene prevScene, SL.Scene currentScene) {
        Toast.makeText(this, "Scene smenilas'!", Toast.LENGTH_SHORT).show();
    }
}
