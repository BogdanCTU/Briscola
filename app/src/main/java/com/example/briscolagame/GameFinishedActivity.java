package com.example.briscolagame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameFinishedActivity extends AppCompatActivity {

    public static boolean PlayerWins;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finished);

        TextView winner = findViewById(R.id.WinnderLabel);
        Button QuitButton = findViewById(R.id.QuitGameFinishedButton);
        Button RestartButton = findViewById(R.id.RestartFinishedButton);

        QuitButton.setOnClickListener(view -> openMainActivity());
        RestartButton.setOnClickListener(view -> {
            Methodes.resetVariables();
            openGameActivity();
        });

        if(PlayerWins) winner.setText("Player Wins!");
        else winner.setText("Enemy Wins!");

    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openGameActivity(){
        Intent intent = new Intent(this, GamePlay.class);
        startActivity(intent);
    }

    // EOF - End Of File
}