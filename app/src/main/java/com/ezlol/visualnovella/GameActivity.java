package com.ezlol.visualnovella;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, OnSceneChangeListener, OnPhraseChangeListener,
        Dialog.OnDialogEndListener, Engine.OnChoiceChangeListener {
    LinearLayout dialogLayout;
    RelativeLayout blackoutLayout;
    TextView dialogAuthor, dialogContent, blackoutAgree, blackoutDecline;
    Engine engine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        blackoutLayout = findViewById(R.id.blackout_layout);
        blackoutAgree = findViewById(R.id.blackout_agree);
        blackoutDecline = findViewById(R.id.blackout_decline);

        blackoutAgree.setOnClickListener(this);
        blackoutDecline.setOnClickListener(this);

        dialogLayout = findViewById(R.id.game_dialog_layout);
        dialogAuthor = findViewById(R.id.game_dialog_author);
        dialogContent = findViewById(R.id.game_dialog_content);

        engine = SL.parse(Main.g());

        dialogLayout.setOnClickListener(this);

        Engine.setOnChoiceChangeListener(this);
        Engine.setOnDialogEndListener(this);

        engine.setOnSceneChangeListener(this);
        engine.setOnPhraseChangeListener(this);

        try {
            engine.init();
        } catch (UnknownCommandException | IOException | SceneNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setChoiceScene(CharSequence agreeTitle, CharSequence declineTitle) {
        dialogLayout.setVisibility(View.INVISIBLE);

        blackoutAgree.setText(agreeTitle);
        blackoutDecline.setText(declineTitle);

        blackoutLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        Log.d("My", "CLICK!");
        switch(view.getId()) {
            case R.id.game_dialog_layout:
                engine.gameDialogClick();
                break;

            case R.id.blackout_agree:
                Log.d("My", "agree");
                blackoutLayout.setVisibility(View.INVISIBLE);
                try {
                    engine.agreeClick();
                } catch (UnknownCommandException | SceneNotFoundException | IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.blackout_decline:
                Log.d("My", "decline");
                blackoutLayout.setVisibility(View.INVISIBLE);
                try {
                    engine.declineClick();
                } catch (UnknownCommandException | SceneNotFoundException | IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onSceneChange(SL.Scene prevScene, SL.Scene currentScene) {
        Toast.makeText(this, "Scene smenilas'!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPhraseChange(Dialog.Phrase phrase) {
        if(phrase == null) return;
        dialogLayout.setVisibility(View.VISIBLE);
        Log.d("My", "Phrase changed");

        dialogAuthor.setText(phrase.author);
        dialogContent.setText(phrase.content);
    }

    @Override
    public void onDialogEnd(Dialog dialog) {
        Log.d(this.getClass().getSimpleName(), "Dialog end");
        dialogLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onChoiceChange(Dialog.Choice choice) {
        dialogLayout.setVisibility(View.INVISIBLE);
        setChoiceScene(choice.agreeTitle, choice.declineTitle);
    }
}
