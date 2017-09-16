package com.bignerdranch.android.mdbfirstminiappproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //initiating the four buttons
    Button firstChoice;
    Button secondChoice;
    Button thirdChoice;
    Button fourthChoice;
    CountDownTimer mCountDown;

    //initiating the end game button (to be worked on)
    Button endGame;

    //initiating an int variable for storing the overall score
    int initialScore =0;

    //initiating the textviews that keep track of the score and the time
    TextView gameScore ;
    TextView time;

    //Initiating the imageview that contain MDB members' photos
    ImageView memberPics;

    //Creating a MemberItem list with each member's photo corresponding to his/her name
    List<MemberItem> list;

    //creating a random generator
    Random r;


    int turn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();

        memberPics = (ImageView)findViewById(R.id.memberPic);

        firstChoice = (Button)findViewById(R.id.firstChoice);
        secondChoice = (Button)findViewById(R.id.secondChoice);
        thirdChoice = (Button)findViewById(R.id.thirdChoice);
        fourthChoice = (Button)findViewById(R.id.fourthChoice);
        gameScore = (TextView)findViewById(R.id.gameScore);
        endGame = (Button)findViewById(R.id.endGameButton);
        //Initial game score = 0
        gameScore.setText(""+0);
        time = (TextView)findViewById(R.id.time);

        list = new ArrayList<>();

        //add all member photos and names to the list
        for (int i=0; i<new Databse().answer.length; i++)
        {
            list.add(new MemberItem(new Databse().answer[i], new Databse().memberPics[i]));
        }

        //shuffle the MemberItem List
        Collections.shuffle(list);

        //input a new question/new 4 options
        newQuestion(turn);

         mCountDown = new CountDownTimer(5100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Time's up! Next Question...", Toast.LENGTH_SHORT).show();
                //at the end of 5 seconds, initiating a new question/new 4 options (ISSUES, when any of the options is pressed
                //5 seconds end, the timer fails to display the correct countdown, and newQuestion method is automatically called.
                newQuestion(turn);
                mCountDown.cancel();
                mCountDown.start();
            }
        }.start();
        mCountDown.cancel();
        mCountDown.start();

        firstChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if the answer is correct
                if(firstChoice.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){
                    Toast.makeText(MainActivity.this, "Correct! +1 point", Toast.LENGTH_SHORT).show();
                    initialScore +=1;
                    gameScore.setText(""+initialScore);

                    //check if it is the last question
                    if(turn<list.size()){
                        newQuestion(turn);
                        mCountDown.cancel();
                        mCountDown.start();
                    }else{
                        Toast.makeText(MainActivity.this,"You finished the quiz!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (MainActivity.this,WelcomeScreen.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Wrong. Next Question...", Toast.LENGTH_SHORT).show();
                    newQuestion(turn);
                    mCountDown.cancel();
                    mCountDown.start();
                }
            }
        });

        secondChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if the answer is correct
                if(secondChoice.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){
                    Toast.makeText(MainActivity.this, "Correct! +1 point", Toast.LENGTH_SHORT).show();
                    initialScore +=1;
                    gameScore.setText(""+initialScore);

                    //check if the last question
                    if(turn<list.size()){
                        newQuestion(turn);
                        mCountDown.cancel();
                        mCountDown.start();
                    }else{
                        Toast.makeText(MainActivity.this,"You finished the quiz!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (MainActivity.this,WelcomeScreen.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Wrong. Next Question...", Toast.LENGTH_SHORT).show();
                    newQuestion(turn);
                    mCountDown.cancel();
                    mCountDown.start();
                }


            }
        });

        thirdChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if the answer is correct
                if(thirdChoice.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){
                    Toast.makeText(MainActivity.this, "Correct! +1 point", Toast.LENGTH_SHORT).show();
                    initialScore +=1;
                    gameScore.setText(""+initialScore);

                    //check if the last question
                    if(turn<list.size()){
                        newQuestion(turn);
                        mCountDown.cancel();
                        mCountDown.start();
                    }else{
                        Toast.makeText(MainActivity.this,"You finished the quiz!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (MainActivity.this,WelcomeScreen.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Wrong. Next Question...", Toast.LENGTH_SHORT).show();
                    newQuestion(turn);
                    mCountDown.cancel();
                    mCountDown.start();
                }

            }
        });

        fourthChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if the answer is correct
                if(fourthChoice.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){
                    Toast.makeText(MainActivity.this, "Correct! +1 point", Toast.LENGTH_SHORT).show();
                    initialScore +=1;
                    gameScore.setText(""+initialScore);

                    //check if the last question
                    if(turn<list.size()){
                        newQuestion(turn);
                        mCountDown.cancel();
                        mCountDown.start();
                    }else{
                        Toast.makeText(MainActivity.this,"You finished the quiz!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (MainActivity.this,WelcomeScreen.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Wrong. Next Question...", Toast.LENGTH_SHORT).show();
                    newQuestion(turn);
                    mCountDown.cancel();
                    mCountDown.start();
                }

            }
        });

        memberPics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set an intent to contacts, where user can make a contact quickly
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.NAME, list.get(turn-1).getName());
                startActivity(intent);
            }
        });

        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                //creating title
                alertDialogBuilder.setTitle("Do you want to end the game?");

                //creating alert message
                alertDialogBuilder.setMessage("click yes to exit");
                //so users can't
                alertDialogBuilder.setCancelable(false);

                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //if clicked, go back to main activity (welcome screen)
                        mCountDown.cancel();
                        Intent myIntent = new Intent(MainActivity.this, WelcomeScreen.class);
                        startActivity(myIntent);

                    }
                });

                alertDialogBuilder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //if no is selected, do nothing
                        dialog.cancel();
                    }
                });
                AlertDialog alert11 = alertDialogBuilder.create();
                alert11.show();
            }
        });
    }

    private void newQuestion(int number){
        //shuffle the list
        //set member image (the first term) to the screen
        Collections.shuffle(list);
        memberPics.setImageResource(list.get(number-1).getImage());


        //decide on which button to place the correct answer
        //r.nextInt gives a ramdom integer between 0 (inclusive) and 4 (exclusive)
        int correct_answer = r.nextInt(4)+1;

        int firstButton = number -1;
        int secondButton;
        int thirdButton;
        int fourthButton;

        //Based on what correct_answer is (random int generation from 0-3),
        // set one of the four options randomly to the correct answer
        switch(correct_answer){
            //If correct_answer is 1
            case 1:
                firstChoice.setText(list.get(firstButton).getName());

                do {
                    //set the second Button to the a random number from 0 to the size of the list (exclusive)
                    secondButton = r.nextInt(list.size());
                }
                //the while loop below makes sure that secondButton doesn't have the same content as the firstButton
                //The same applies to all the while statements below (to avoid any repetition of possible options
                //among all the four choices)
                while (secondButton == firstButton);
                do {
                    //set the third Button to the a random number from 0 to the size of the list (exclusive)
                    thirdButton = r.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                } while (fourthButton == firstButton || fourthButton == secondButton ||
                        fourthButton == thirdButton);

                secondChoice.setText(list.get(secondButton).getName());
                thirdChoice.setText(list.get(thirdButton).getName());
                fourthChoice.setText(list.get(fourthButton).getName());

                break;
            case 2:

                secondChoice.setText(list.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(list.size());
                } while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                } while (fourthButton == firstButton || fourthButton == secondButton ||
                        fourthButton == thirdButton);

                firstChoice.setText(list.get(secondButton).getName());
                thirdChoice.setText(list.get(thirdButton).getName());
                fourthChoice.setText(list.get(fourthButton).getName());

                break;
            case 3:

                thirdChoice.setText(list.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(list.size());
                } while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                } while (fourthButton == firstButton || fourthButton == secondButton ||
                        fourthButton == thirdButton);

                secondChoice.setText(list.get(secondButton).getName());
                firstChoice.setText(list.get(thirdButton).getName());
                fourthChoice.setText(list.get(fourthButton).getName());

                break;
            case 4:

                fourthChoice.setText(list.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(list.size());
                } while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                } while (fourthButton == firstButton || fourthButton == secondButton ||
                        fourthButton == thirdButton);

                secondChoice.setText(list.get(secondButton).getName());
                thirdChoice.setText(list.get(thirdButton).getName());
                firstChoice.setText(list.get(fourthButton).getName());

                break;
        }
        //initiating a 5-sec countdown





    }
}
