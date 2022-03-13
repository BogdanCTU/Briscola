package com.example.briscolagame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton PVP_Button;
    private ImageButton PVC_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buttons \\

        PVP_Button = (ImageButton) findViewById(R.id.PVP_Button);
        PVP_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGamePlayActivity();
                Variables.gameStyle = 0;   // 0 pvp   -   1 pvc easy   -   2 pvc hard
            }
        });

        PVC_Button = (ImageButton) findViewById(R.id.PVC_Button);
        PVC_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGamePlayActivity();
                Variables.gameStyle = 1;   // 0 pvp   -   1 pvc easy   -   2 pvc hard
            }
        });

    }

    public void openGamePlayActivity(){
        Intent intent = new Intent(this, GamePlay.class);
        startActivity(intent);
    }

    //EOF - End Of File
}