package com.example.gupta.matchthemembers;

import android.os.CountDownTimer;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String[] MEMBERS = {"Aayush Tyagi", "Abhinav Koppu", "Adhiraj Datar", "Akkshay Khoslaa", "Amy Shen", "Aneesh Jindal", "Ashwin Vaidyanathan", "Ben Goldberg", "Billy Lu", "Boris Yue", "Edward Liu", "Candice Ye", "Cody Hsieh", "Daniel Andrews", "Eliot Han", "Emaan Hariri", "Eric Kong", "Jared Gutierrez", "Jeffrey Zhang", "Jessica Chen", "Julia Luo", "Justin Kim", "Kevin Jiang", "Krishnan Rajiyah", "Kristin Ho", "Leon Kwak", "Levi Walsh", "Mohit Katyal", "Mudit Mittal", "Peter Schafhalter", "Radhika Dhomse", "Rochelle Shen", "Sahil Lamba", "Sarah Tang", "Sayan Paul", "Sharie Wang", "Shiv Kushwah", "Shreya Reddy", "Shubham Goenka", "Sumukh Shivakumar", "Tarun Khasnavis", "Victor Sun", "Vidya Ravikumar", "Wilbur Shi", "Young Lin", "Zach Govani"};

    private class Member {
        String name;
        int image;
        Member(String n) {
            name = n;
            image = getResources().getIdentifier(name.replace(" ", "").toLowerCase(), "drawable", getPackageName());
        }
    }

    private class Club {

        private ArrayList<Member> allMembers;
        private ArrayList<Member> availableMembers;
        Member currentMember;

        Club() {
            allMembers = new ArrayList<>();
            for (String name : MEMBERS) {
                allMembers.add(new Member(name));
            }
            generateAvailableMembers();
        }
        private void generateAvailableMembers() {
            availableMembers = new ArrayList<>(allMembers);
            Collections.shuffle(availableMembers);
        }
        void nextMember() {
            if (availableMembers.size() == 0) {
                generateAvailableMembers();
            }
            currentMember = availableMembers.remove(0);
        }

    }

    int score = 0;
    Club club;

    //Views
    ImageView profileImage;
    Button name1Button, name2Button, name3Button, name4Button, endGameButton;
    TextView scoreText, timerText;
    AlertDialog dialog;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Initialize views
        scoreText = (TextView) findViewById(R.id.scoreText);
        timerText = (TextView) findViewById(R.id.timer);
        profileImage = (ImageView) findViewById(R.id.profileImage);
        name1Button = (Button) findViewById(R.id.name1Button);
        name2Button = (Button) findViewById(R.id.name2Button);
        name3Button = (Button) findViewById(R.id.name3Button);
        name4Button = (Button) findViewById(R.id.name4Button);
        endGameButton = (Button) findViewById(R.id.endGameButton);

        //Set onclick listeners
        profileImage.setOnClickListener(this);
        name1Button.setOnClickListener(this);
        name2Button.setOnClickListener(this);
        name3Button.setOnClickListener(this);
        name4Button.setOnClickListener(this);
        endGameButton.setOnClickListener(this);

        //Build (but not show) the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle(R.string.endGameTitle).setMessage(R.string.endGameMessage);
        builder.setPositiveButton(R.string.endGameConfirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                onBackPressed();
            }
        });
        builder.setNegativeButton(R.string.endGameCancel, null);

        dialog = builder.create();

        //Create the club
        club = new Club();
        club.nextMember();

        //Show profile
        showProfile(club.currentMember);


    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProfile(club.currentMember);
    }

    private void showProfile(Member member) {
        profileImage.setImageResource(member.image);
        ((TextView) findViewById(R.id.scoreText)).setText(String.format(getResources().getString(R.string.scoreText), score));

        // Build timer
        if (timer != null) {
            timer.cancel();
        }
        timer = new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerText.setText(String.format(getResources().getString(R.string.secondsLeftText), ((millisUntilFinished / 1000) + 1)));
            }

            public void onFinish() {
                prepNextProfile(false, getString(R.string.timerDoneText));

            }
        }.start();

        //Create the list of incorrect answers
        ArrayList<String> incorrectNames = new ArrayList<>();
        while (incorrectNames.size() < 3) {
            int random = (int) (Math.random() * MEMBERS.length);
            String possible_incorrect = MEMBERS[random];
            if (!incorrectNames.contains(possible_incorrect) && !member.name.equals(possible_incorrect)) {
                incorrectNames.add(possible_incorrect);
            }
        }

        //Randomize buttons
        ArrayList<String> buttonTexts = new ArrayList<>(incorrectNames);
        buttonTexts.add(member.name);
        Collections.shuffle(buttonTexts);
        name1Button.setText(buttonTexts.get(0));
        name2Button.setText(buttonTexts.get(1));
        name3Button.setText(buttonTexts.get(2));
        name4Button.setText(buttonTexts.get(3));

    }

    public void prepNextProfile(boolean correct, String message) {
        if (correct) {
            score++;
        } else {
            Toast.makeText(this, message + getString(R.string.toastText) + club.currentMember.name, Toast.LENGTH_SHORT).show();
        }
        club.nextMember();
        showProfile(club.currentMember);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.name1Button:
            case R.id.name2Button:
            case R.id.name3Button:
            case R.id.name4Button:
                prepNextProfile(((Button) view).getText().equals(club.currentMember.name), getString(R.string.incorrectText));
                break;
            case R.id.profileImage:
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.NAME, club.currentMember.name);
                startActivity(intent);
                break;
            case R.id.endGameButton:
                dialog.show();
                break;
        }

    }

}
