package com.example.briscolagame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import java.io.FileNotFoundException;

public class PauseActivity extends AppCompatActivity {

    //Buttons
    private Button restartButton;
    private Button quitButton;
    private Button SaveQuitButton;
    private Button resumeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        // Buttons
        SetID();
        SetListeners();
    }

    // Methods

    private void SetID(){
        quitButton = findViewById(R.id.QuitButtonP);
        SaveQuitButton = findViewById(R.id.QuitSaveButtonP);
        resumeButton = findViewById(R.id.ResumeButton);
        restartButton = findViewById(R.id.RestartButton);
    }

    private void SetListeners(){
        quitButton.setOnClickListener(view -> {
            Methodes.resetVariables();
            openMainActivity();
        });

        SaveQuitButton.setOnClickListener(view -> {
            try {
                Methodes.SaveDataFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            openMainActivity();
        });

        resumeButton.setOnClickListener(view -> openGameActivity());

        restartButton.setOnClickListener(view -> {
            Methodes.resetVariables();
            openGameActivity();
        });
    }

    private void openGameActivity(){
        Intent intent = new Intent(this, GamePlay.class);
        startActivity(intent);
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // EOF - End Of File
}