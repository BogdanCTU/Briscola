package com.example.briscolagame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PauseActivity extends AppCompatActivity {

    //Buttons
    private Button restartButton;
    private Button quitButton;
    private Button resumeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        // Buttons

        quitButton = (Button) findViewById(R.id.QuitButtonP);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSaveActivity();
            }
        });

        resumeButton = (Button) findViewById(R.id.ResumeButton);
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Methodes.LoadData();
                openGameActivity();
            }
        });

        restartButton =(Button)findViewById(R.id.RestartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick (View view){
                Methodes.resetVariables();
                openGameActivity();
            }
        });
    }

    private void openGameActivity(){
        Intent intent = new Intent(this, GamePlay.class);
        startActivity(intent);
    }

    private void openSaveActivity() {
        Intent intent = new Intent(this, SaveActivity.class);
        startActivity(intent);
    }

    // EOF - End Of File
}