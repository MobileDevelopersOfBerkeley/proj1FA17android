package com.example.kanyes.miniproject1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.kanyes.miniproject1.R.id.ans1;

public class Game extends AppCompatActivity implements View.OnClickListener{
    private Button ans1, ans2, ans3, ans4, exit;
    private ImageView pic;
    private String[] members;
    private int correctAnswer = getAnswer();
    private TextView scoreDisplay, timer;
    private int score;
    private CountDownTimer count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ans1 = (Button) findViewById(R.id.ans1);
        ans2 = (Button) findViewById(R.id.ans2);
        ans3 = (Button) findViewById(R.id.ans3);
        ans4 = (Button) findViewById(R.id.ans4);
        exit = (Button) findViewById(R.id.exit);
        pic = (ImageView) findViewById(R.id.pic);
        members = new String[]{"Aayush Tyagi", "Abhinav Koppu", "Adhiraj Datar", "Akkshay Khoslaa", "Amy Shen", "Aneesh Jindal", "Ashwin Vaidyanathan", "Ben Goldberg", "Billy Lu", "Boris Yue", "Edward Liu", "Candice Ye", "Cody Hsieh", "Daniel Andrews", "Eliot Han", "Emaan Hariri", "Eric Kong", "Jared Gutierrez", "Jeffrey Zhang", "Jessica Chen", "Julia Luo", "Justin Kim", "Kevin Jiang", "Krishnan Rajiyah", "Kristin Ho", "Leon Kwak", "Levi Walsh", "Mohit Katyal", "Mudit Mittal", "Peter Schafhalter", "Radhika Dhomse", "Rochelle Shen", "Sahil Lamba", "Sarah Tang", "Sayan Paul", "Sharie Wang", "Shiv Kushwah", "Shreya Reddy", "Shubham Goenka", "Sumukh Shivakumar", "Tarun Khasnavis", "Victor Sun", "Vidya Ravikumar", "Wilbur Shi", "Young Lin", "Zach Govani"};
        scoreDisplay = (TextView) findViewById(R.id.score);
        score=0;
        timer = (TextView) findViewById(R.id.timer);

        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        ans4.setOnClickListener(this);
        exit.setOnClickListener(this);
        pic.setOnClickListener(this);
        count = new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf((int)(millisUntilFinished/1000)));
            }
            @Override
            public void onFinish() {
                timer.setText(String.valueOf(0));
                dispToast();
                changeNames();
                correctAnswer = getAnswer();
                changeImage((String) getRightButton().getText());
                count.start();
            }
        };
        changeNames();
        changeImage((String) getRightButton().getText());
        Toast toast= Toast.makeText(getApplicationContext(),"Practice! From the next pic on, you only have 5 seconds to select. Good luck!", Toast.LENGTH_LONG);
        toast.show();
    }
    protected void onStart() {
        super.onStart();
        count.start();
    }
    protected void onDestroy(){
        super.onDestroy();
        count.cancel();
    }
    protected void onPause(){
        super.onPause();
        count.cancel();
    }
    protected void onResume(){
        super.onResume();
        count.cancel();
    }

    public int[] getRandomIndices(){
        int[] toReturn = new int[4];
        for (int i=0;i<=3;i++) {
            toReturn[i] = (int) (Math.random() * members.length);
            for (int j = 0; j <= 3; j++) {
                while ((i != j) && toReturn[i] == toReturn[j]) {
                    toReturn[i] = (int) (Math.random() * members.length);
                }
            }
        }
        return toReturn;
    } //Creates 4 random indices that will be used to set the buttons
    public void changeNames(){
        int[] indices = getRandomIndices();
        ans1.setText(members[indices[0]]);
        ans2.setText(members[indices[1]]);
        ans3.setText(members[indices[2]]);
        ans4.setText(members[indices[3]]);
        count.start();
    } //Changes the names of each button to names selected by getRandomIndices()
    public int getAnswer(){
        int soln=(int)(Math.random() * 4);
        if (soln==0){
            return R.id.ans1;
        }
        else if (soln==1){
            return R.id.ans2;
        }
        else if (soln==2){
            return R.id.ans3;
        }
        else{
            return R.id.ans4;
        }
    } //Returns a random R.id.____ which will be the solution to the current game screen
    public Button getRightButton(){
        if (correctAnswer == R.id.ans1){
            return ans1;
        }
        if (correctAnswer == R.id.ans2){
            return ans2;
        }
        if (correctAnswer == R.id.ans3){
            return ans3;
        }
        if (correctAnswer == R.id.ans4){
            return ans4;
        }
        return ans1;
    } //Returns the button that is the correct answer for the current image
    public void changeImage(String text){
        String imgName = ((text).toLowerCase()).replaceAll("\\s","");
        int id = getResources().getIdentifier(imgName, "drawable", getPackageName());
        pic.setImageDrawable(ResourcesCompat.getDrawable(getResources(), id, null));
    } // Changes the image based on the correct button
    public void scoreUp(){
        score++;
        scoreDisplay.setText(Integer.toString(score));
    }
    public void dispToast(){
        Toast toast= Toast.makeText(getApplicationContext(),"Sorry, that's incorrect!", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() != R.id.exit && v.getId() != R.id.pic) {
            if (v.getId() == R.id.ans1){
                if (correctAnswer == R.id.ans1) {
                    scoreUp();
                }
                else
                    dispToast();
            }
            else if (v.getId() == R.id.ans2){
                if (correctAnswer == R.id.ans2) {
                    scoreUp();
                }
                else
                    dispToast();
            }
            else if (v.getId() == R.id.ans3){
                if (correctAnswer == R.id.ans3) {
                    scoreUp();
                }
                else
                    dispToast();
            }
            else if (v.getId() == R.id.ans4){
                if (correctAnswer == R.id.ans4) {
                    scoreUp();
                }
                else
                    dispToast();
            }
            changeNames();
            correctAnswer = getAnswer();
            changeImage((String) getRightButton().getText());
        }
        if (v.getId() == R.id.pic){
            Intent toContact = new Intent(Intent.ACTION_INSERT);
            toContact.setType(ContactsContract.Contacts.CONTENT_TYPE);
            String cName = (String) getRightButton().getText();
            toContact.putExtra(ContactsContract.Intents.Insert.NAME, cName);
            startActivity(toContact);
        }
        if (v.getId() == R.id.exit) {
            AlertDialog.Builder alert = new AlertDialog.Builder(Game.this).setTitle("ATTENTION:");
            alert.setMessage("Do you really want to quit?");
            alert.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
            alert.setNegativeButton("Cancel", null).show();
        }
    }
}



