package com.bignerdranch.android.mdbfirstminiappproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    Button startGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        //When pressed, the startGame Button leads from the welcome screen to the game screen
        startGame = (Button)findViewById(R.id.startButton);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (WelcomeScreen.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
