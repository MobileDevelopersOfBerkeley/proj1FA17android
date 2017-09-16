package com.example.joey.match_members;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Game extends AppCompatActivity {

    final static String[] members = {"Aayush Tyagi", "Abhinav Koppu", "Adhiraj Datar", "Akkshay Khoslaa", "Amy Shen", "Aneesh Jindal", "Ashwin Vaidyanathan", "Ben Goldberg", "Billy Lu", "Boris Yue", "Edward Liu", "Candice Ye", "Cody Hsieh", "Daniel Andrews", "Eliot Han", "Emaan Hariri", "Eric Kong", "Jared Gutierrez", "Jeffrey Zhang", "Jessica Chen", "Julia Luo", "Justin Kim", "Kevin Jiang", "Krishnan Rajiyah", "Kristin Ho", "Leon Kwak", "Levi Walsh", "Mohit Katyal", "Mudit Mittal", "Peter Schafhalter", "Radhika Dhomse", "Rochelle Shen", "Sahil Lamba", "Sarah Tang", "Sayan Paul", "Sharie Wang", "Shiv Kushwah", "Shreya Reddy", "Shubham Goenka", "Sumukh Shivakumar", "Tarun Khasnavis", "Victor Sun", "Vidya Ravikumar", "Wilbur Shi", "Young Lin", "Zach Govani"};
    static String answer =  new String("");
    int score = 0;
    private Button answerOne;
    private Button answerTwo;
    private Button answerThree;
    private Button answerFour;
    private ImageView memberImage;
    private TextView scoreString;
    private Toast wrongAnswer;
    private TextView timerText;
    private CountDownTimer timer;
    private Button exitButton;
    private Dialog exitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //init buttons and imageView and Score String
        answerOne = (Button) findViewById(R.id.answerOne);
        answerTwo = (Button) findViewById(R.id.answerTwo);
        answerThree = (Button) findViewById(R.id.answerThree);
        answerFour = (Button) findViewById(R.id.answerFour);
        memberImage = (ImageView) findViewById(R.id.memberImage);
        scoreString = (TextView) findViewById(R.id.scoreDisplay);
        timerText = (TextView) findViewById(R.id.timer);
        timer = createTimer();
        exitButton = (Button) findViewById(R.id.exitButton);

        //create toast for incorrect answer
        wrongAnswer = Toast.makeText(getApplicationContext(), "Incorrect Answer!", Toast.LENGTH_SHORT);

        //create exit Dailog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Are you sure you want to exit?");
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //user clicks yes
                Intent returnToMenu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(returnToMenu);
            }
        });
        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //user clicks no
            }
        });
        exitDialog = dialogBuilder.create();

        newRound();
        initListeners();

    }


    private void newRound(){

        timer.cancel();

        //init unique string array
        String[] names = new String[4];
        for(int i = 0; i < names.length; i++) {
            names[i] = new String("");
        }
        do {
            names[0] = members[(int) (Math.random()*members.length)];
            names[1] = members[(int) (Math.random()*members.length)];
            names[2] = members[(int) (Math.random()*members.length)];
            names[3] = members[(int) (Math.random()*members.length)];
        }while(!containsDuplicates(names));

        //collect all of answer Button objects

        //set text of buttons
        answerOne.setText(names[0]);
        answerTwo.setText(names[1]);
        answerThree.setText(names[2]);
        answerFour.setText(names[3]);

        //get Image String

        String imageName = new String(names[(int) (Math.random()*names.length)]);
        //set Image
        Context context = memberImage.getContext();
        int resID = context.getResources().getIdentifier(toImageString(imageName), "drawable", context.getPackageName());
        memberImage.setImageResource(resID);
        memberImage.setTag(imageName);

        //set score string
        scoreString.setText("Score: " + score);

        //initialize round timer
        timer = createTimer();

    }

    private CountDownTimer createTimer(){
        return new CountDownTimer(6100, 1000) {

            public void onTick(long millisUntilFinished) {
                timerText.setText(millisUntilFinished / 1000 - 1 + "s");
            }

            public void onFinish() {
                newRound();
            }
        }.start();
    }

    private void initListeners(){
        answerOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkWin(answerOne);
            }

        });
        answerTwo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkWin(answerTwo);
            }

        });
        answerThree.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkWin(answerThree);
            }

        });
        answerFour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkWin(answerFour);
            }

        });
        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                exitDialog.show();
            }
        });
        memberImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addContactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
                addContactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                addContactIntent.putExtra(ContactsContract.Intents.Insert.NAME, String.valueOf(memberImage.getTag()));
                startActivity(addContactIntent);
            }
        });
    }

    private void checkWin(Button button){
        if(toImageString(button.getText().toString()).equals(toImageString(String.valueOf(memberImage.getTag())))){
            score++;
            newRound();
        }else{
            wrongAnswer.show();
            //to disable move on after this
            newRound();

        }
    }

    private static String toImageString(String s){
        return s.toLowerCase().replaceAll("\\s+","");
    }

    private static boolean containsDuplicates(String[] names) {
        for (int i = 0; i < names.length; i++) {
            for (int j = i + 1; j < names.length; j++) {
                if (names[i].equals(names[j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
