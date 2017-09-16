package com.example.joey.match_members;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the start button
        Button startButton = (Button) findViewById(R.id.startButton);
        //connect start button to listener that will start the game
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent beginGame = new Intent(getApplicationContext(), Game.class);
                startActivity(beginGame);
            }
        });

    }
}
