package com.example.briscolagame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SaveActivity extends AppCompatActivity {

    // Buttons

    private Button SaveQuitButton;
    private Button QuitGameButton;
    private Button BackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        // Buttons

        SaveQuitButton = (Button) findViewById(R.id.QuitButton);
        SaveQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Methodes.SaveData();   //TODO
                openMainActivity();
            }
        });

        QuitGameButton = (Button) findViewById(R.id.ResumeButton);
        QuitGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        BackButton =(Button)findViewById(R.id.RestartButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick (View view){
                openPauseActivity();
            }
        });

    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openPauseActivity(){
        Intent intent = new Intent(this, PauseActivity.class);
        startActivity(intent);
    }

    // EOF - End Of File
}