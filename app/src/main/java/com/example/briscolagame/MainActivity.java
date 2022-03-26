package com.example.briscolagame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton PVP_Button;
    private ImageButton PVC_Button;
    private Button OnlinePlayerButton;
    private Button OnlineEnemyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buttons and Listeners \\

        PVP_Button = findViewById(R.id.PVP_Button);
        PVP_Button.setOnClickListener(view -> {
            openGamePlayActivity();
            Variables.gameStyle = 0;   // 0 pvp   -   1 pvc easy   -   2 pvc hard
        });

        PVC_Button = findViewById(R.id.PVC_Button);
        PVC_Button.setOnClickListener(view -> {
            openGamePlayActivity();
            Variables.gameStyle = 1;   // 0 pvp   -   1 pvc easy   -   2 pvc hard
        });

        OnlinePlayerButton = findViewById(R.id.OnlinePlayerButton);
        OnlinePlayerButton.setOnClickListener(view -> {
            openOnlineGamePlayActivity();
            Variables.gameStyle = 0;
            Variables.player_number = 1;   // Play as player
        });

        OnlineEnemyButton = findViewById(R.id.OnlineEnemyButton);
        OnlineEnemyButton.setOnClickListener(view -> {
            openOnlineGamePlayActivity();
            Variables.gameStyle = 0;
            Variables.player_number = 2;   // Play as enemy
        });

    }

    public void openGamePlayActivity(){
        Intent intent = new Intent(this, GamePlay.class);
        startActivity(intent);
    }

    public void openOnlineGamePlayActivity(){
        Intent intent = new Intent(this, OnlineGamePlay.class);
        startActivity(intent);
    }

    //EOF - End Of File
}