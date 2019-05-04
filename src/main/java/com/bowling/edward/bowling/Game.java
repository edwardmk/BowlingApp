package com.bowling.edward.bowling;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import static java.lang.String.valueOf;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Game extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    TextView scoreText1, scoreText2, scoreText3, scoreText4, scoreText5, scoreText6, scoreText7, scoreText8, scoreText9, scoreText10, scoreText11, scoreText12, scoreText13, scoreText14, scoreText15, scoreText16, scoreText17, scoreText18, scoreText19, scoreText20, scoreText21;
    TextView[] textViews;
    TextView frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10;
    TextView[] frames;
    Button but0, but1, but2, but3, but4, but5, but6, but7, but8, but9, butX, butCA, butSpare, butEnter;
    Button[] buttons;
    int finalScore;
    int multiplier;
    int frameNumber, shotNumber = 1;
    int strikeCount;
    int finalFrameCount;
    int noOfStrikes, noOfSpares, shotsCleared, numberOfShots;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private static final String TAG = "AddToDatabase";
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
            FirebaseUser user = mAuth.getCurrentUser();
        but0 = findViewById(R.id.button0);
        but1 = findViewById(R.id.button1);
        but2 = findViewById(R.id.button2);
        but3 = findViewById(R.id.button3);
        but4 = findViewById(R.id.button4);
        but5 = findViewById(R.id.button5);
        but6 = findViewById(R.id.button6);
        but7 = findViewById(R.id.button7);
        but8 = findViewById(R.id.button8);
        but9 = findViewById(R.id.button9);
        butX = findViewById(R.id.buttonX);
        butSpare = findViewById(R.id.buttonSpare);
        butEnter = findViewById(R.id.buttonEnter);

        butCA = findViewById(R.id.buttonClear);

        butSpare.setVisibility(View.INVISIBLE);
        butX.setVisibility(View.VISIBLE);

        buttons = new Button[]{but0, but1, but2, but3, but4, but5, but6, but7, but8, but9, butSpare, butX};

        scoreText1 = findViewById(R.id.score1);
        scoreText2 = findViewById(R.id.score2);
        scoreText3 = findViewById(R.id.score3);
        scoreText4 = findViewById(R.id.score4);
        scoreText5 = findViewById(R.id.score5);
        scoreText6 = findViewById(R.id.score6);
        scoreText7 = findViewById(R.id.score7);
        scoreText8 = findViewById(R.id.score8);
        scoreText9 = findViewById(R.id.score9);
        scoreText10 = findViewById(R.id.score10);
        scoreText11 = findViewById(R.id.score11);
        scoreText12 = findViewById(R.id.score12);
        scoreText13 = findViewById(R.id.score13);
        scoreText14 = findViewById(R.id.score14);
        scoreText15 = findViewById(R.id.score15);
        scoreText16 = findViewById(R.id.score16);
        scoreText17 = findViewById(R.id.score17);
        scoreText18 = findViewById(R.id.score18);
        scoreText19 = findViewById(R.id.score19);
        scoreText20 = findViewById(R.id.score20);
        scoreText21 = findViewById(R.id.score21);

        frame1 = findViewById(R.id.first);
        frame2 = findViewById(R.id.second);
        frame3 = findViewById(R.id.third);
        frame4 = findViewById(R.id.forth);
        frame5 = findViewById(R.id.fifth);
        frame6 = findViewById(R.id.sixth);
        frame7 = findViewById(R.id.seventh);
        frame8 = findViewById(R.id.eighth);
        frame9 = findViewById(R.id.ninth);
        frame10 = findViewById(R.id.tenth);

        frames = new TextView[]{frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10};

        textViews = new TextView[]{scoreText1, scoreText2, scoreText3, scoreText4, scoreText5, scoreText6, scoreText7, scoreText8, scoreText9, scoreText10, scoreText11, scoreText12, scoreText13, scoreText14, scoreText15, scoreText16, scoreText17, scoreText18, scoreText19, scoreText20, scoreText21};
        mAuth = FirebaseAuth.getInstance();
        userID = user.getUid();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d(TAG, "onDataChange: Added information to database: \n" +
                        dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_game) {
            Intent i = new Intent(this, Game.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_home) {
            Intent i = new Intent(this, HomePage.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_statistics){ //DO your stuff }
            Intent i = new Intent(this, Statistics.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_view_friends) {
            Intent i = new Intent(this, FindFriends.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_tournament) {
            Intent i = new Intent(this, Tournament.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_view_wall) {
            Intent i = new Intent(this, Wall.class);
            startActivity(i);
            finish();
        }
        return false;
    }

    public void Enter(View view) {
        Toast.makeText(getApplicationContext(), "Post successful", Toast.LENGTH_SHORT).show();

    }

    public void Button0(android.view.View v) {
        UpdateFrame("0");
        numberOfShots++;
    }

    public void Button1(android.view.View v) {
        UpdateFrame("1");
        numberOfShots++;
    }

    public void Button2(android.view.View v) {
        UpdateFrame("2");
        numberOfShots++;
    }

    public void Button3(android.view.View v) {
        UpdateFrame("3");
        numberOfShots++;
    }

    public void Button4(android.view.View v) {
        UpdateFrame("4");
        numberOfShots++;
    }

    public void Button5(android.view.View v) {
        UpdateFrame("5");
        numberOfShots++;
    }

    public void Button6(android.view.View v) {
        UpdateFrame("6");
        numberOfShots++;
    }

    public void Button7(android.view.View v) {
        UpdateFrame("7");
        numberOfShots++;
    }

    public void Button8(android.view.View v) {
        UpdateFrame("8");
        numberOfShots++;
    }

    public void Button9(android.view.View v) {
        UpdateFrame("9");
        numberOfShots++;
    }

    public void ClearAll(android.view.View v) {
        for(int i = 0; i < frames.length; i++){
            frames[i].setText("");
        }
        for(int i = 0; i < textViews.length; i++){
            textViews[i].setText("");
        }
        for (int i = 0; i <= 9; i++) {
            buttons[i].setVisibility(View.VISIBLE);
        }
        butX.setVisibility(View.VISIBLE);
        butSpare.setVisibility(View.INVISIBLE);

        multiplier = 0;
        frameNumber = 0;
        shotNumber = 1;
        strikeCount = 0;
        finalFrameCount = 0;
        noOfStrikes = 0;
        noOfSpares = 0;
        shotsCleared = 0;
        finalScore = 0;
        numberOfShots = 0;
    }

    public void ButtonX(android.view.View v) {
        noOfStrikes++;
        shotsCleared++;
        numberOfShots++;
        if(frameNumber == 9){
            finalFrame("X");
        }
        else {
            textViews[shotNumber - 1].setText(valueOf("X"));
            if (frameNumber == 0) {
                frames[frameNumber].setText(valueOf("10"));
            } else if (multiplier == 0) {
                String lastFrame = frames[frameNumber - 1].getText().toString();

                int lastFrameInt = Integer.parseInt(lastFrame);
                int finalScore = 10 + lastFrameInt;
                String finalScoreString = valueOf(finalScore);

                frames[frameNumber].setText(finalScoreString);
            } else if (multiplier == 1 || frameNumber == 1) {
                SpareMultiplier(10);
            } else {
                StrikeMultiplier(10);

            }
            shotNumber++;
            shotNumber++;
            frameNumber++;
            multiplier = 2;
        }
        strikeCount++;

    }

    public void ButtonSpare(android.view.View v) {


        if(frameNumber == 9){
            finalFrame("/");
        }
        else {
            if (frameNumber == 0) {
                textViews[shotNumber - 1].setText(valueOf("/"));
                frames[frameNumber].setText(valueOf("10"));
            } else if (multiplier == 0) {
                textViews[shotNumber - 1].setText(valueOf("/"));
                String lastFrame = frames[frameNumber - 1].getText().toString();

                int lastFrameInt = Integer.parseInt(lastFrame);

                int finalScore = 10 + lastFrameInt;
                String finalScoreString = valueOf(finalScore);

                frames[frameNumber].setText(finalScoreString);
                strikeCount = 0;
            }
            else if (multiplier == 1 || frameNumber == 1) {
                textViews[shotNumber - 1].setText(valueOf("/"));
                SpareMultiplier(10);
                strikeCount = 0;

            }
            else {
                textViews[shotNumber - 1].setText(valueOf("/"));
                SpareAfterStrike();
                strikeCount = 0;
            }
            for (int i = 0; i <= 9; i++) {
                buttons[i].setVisibility(View.VISIBLE);
            }
            butX.setVisibility(View.VISIBLE);
            butSpare.setVisibility(View.INVISIBLE);
            multiplier = 1;
            frameNumber++;
            shotNumber++;
        }
        noOfSpares++;
        shotsCleared++;
        numberOfShots++;
    }
    public void SpareAfterStrike(){
        int lastShotInt;
        String lastShot;
        int score = 10;
        String lastFrame;
        int lastFrameInt;
        lastShot = textViews[shotNumber - 2].getText().toString();

        lastShotInt = Integer.parseInt(lastShot);

        if (frameNumber > 1 && strikeCount > 1) {
            String twoFramesAgo = frames[frameNumber - 2].getText().toString();
            int twoFramesAgoInt = Integer.parseInt(twoFramesAgo);
            twoFramesAgoInt = twoFramesAgoInt + lastShotInt;
            String twoFramesAgoString = valueOf(twoFramesAgoInt);
            frames[frameNumber - 2].setText(twoFramesAgoString);

            lastFrame = frames[frameNumber - 1].getText().toString();
            lastFrameInt = Integer.parseInt(lastFrame);
            int newLastFrameInt = twoFramesAgoInt + 10 + score;
            String lastFrameString = valueOf(newLastFrameInt);
            frames[frameNumber - 1].setText(lastFrameString);
        }
        else {
            lastFrame = frames[frameNumber - 1].getText().toString();
            lastFrameInt = Integer.parseInt(lastFrame);
            int newLastFrameInt = lastFrameInt + score;
            String lastFrameString = valueOf(newLastFrameInt);
            frames[frameNumber - 1].setText(lastFrameString);
        }
        lastFrame = frames[frameNumber - 1].getText().toString();
        lastFrameInt = Integer.parseInt(lastFrame);
        int finalScore = lastFrameInt + score;
        String finalScoreString = valueOf(finalScore);
        frames[frameNumber].setText(finalScoreString);


        multiplier--;
        multiplier--;

    }
    public void  UpdateFrame(String score) {
        if(textViews[shotNumber-1] == scoreText20 && multiplier == 2 && !"X".equals(textViews[shotNumber-2].getText().toString())){
            int scoreInt = Integer.parseInt(score);
            StrikeMultiplier(scoreInt);
            for (int i = 0; i <= 9; i++) {
                buttons[i].setVisibility(View.VISIBLE);
            }
            butSpare.setVisibility(View.INVISIBLE);
            butX.setVisibility(View.VISIBLE);
            textViews[shotNumber-1].setText(score);

            EndGame();
        }
        else if(textViews[shotNumber-1] == scoreText20 && multiplier == 2 && "X".equals(textViews[shotNumber-2].getText().toString())){
            int scoreInt = Integer.parseInt(score);
            StrikeMultiplier(scoreInt);
            for (int i = 0; i <= 9; i++) {
                buttons[i].setVisibility(View.VISIBLE);
            }
            butSpare.setVisibility(View.INVISIBLE);
            butX.setVisibility(View.VISIBLE);
            textViews[shotNumber-1].setText(score);
            frames[frameNumber].setText("");

        }
        else if(textViews[shotNumber-1] == scoreText20 && multiplier == 1 && !"X".equals(textViews[shotNumber-2].getText().toString())){
            int scoreInt = Integer.parseInt(score);
            SpareMultiplier(scoreInt);
            textViews[shotNumber - 1].setText(score);

            int removeInts = 10 - scoreInt;

            for (int i = 9; i > removeInts - 1; i--) {
                buttons[i].setVisibility(View.INVISIBLE);
            }
            butSpare.setVisibility(View.VISIBLE);
            butX.setVisibility(View.INVISIBLE);
            EndGame();
        }
        else if(textViews[shotNumber-1] == scoreText20 && multiplier == 1 && "X".equals(textViews[shotNumber-2].getText().toString())){
                int scoreInt = Integer.parseInt(score);
            SpareMultiplier(scoreInt);
            textViews[shotNumber - 1].setText(score);

            int removeInts = 10 - scoreInt;

            for (int i = 9; i > removeInts - 1; i--) {
                buttons[i].setVisibility(View.INVISIBLE);
            }
            butSpare.setVisibility(View.VISIBLE);
            butX.setVisibility(View.INVISIBLE);
            frames[frameNumber].setText("");
        }
        else if(textViews[shotNumber-1] == scoreText20 && multiplier == 0 && "X".equals(textViews[shotNumber-2].getText().toString())){
            textViews[shotNumber - 1].setText(score);
        }

        else if (textViews[shotNumber-1] == scoreText20 && shotNumber % 2 == 0 && multiplier == 0){
            String shot1, shot2;

            textViews[shotNumber - 1].setText(score);

            shot1 = textViews[shotNumber - 2].getText().toString();
            shot2 = textViews[shotNumber - 1].getText().toString();

            int shot1Int, shot2Int, frameScore;

            shot1Int = Integer.parseInt(shot1);
            shot2Int = Integer.parseInt(shot2);

            frameScore = shot1Int + shot2Int;

            String lastFrame = frames[frameNumber - 1].getText().toString();

            int lastFrameInt = Integer.parseInt(lastFrame);

            finalScore = frameScore + lastFrameInt;
            String finalScoreString = valueOf(finalScore);

            frames[frameNumber].setText(finalScoreString);

            frameNumber++;
            for (int i = 0; i <= 9; i++) {
                buttons[i].setVisibility(View.VISIBLE);
            }
            butX.setVisibility(View.VISIBLE);
            butSpare.setVisibility(View.INVISIBLE);
            strikeCount = 0;
            EndGame();
        }

        else if(textViews[shotNumber-1] == scoreText21 && multiplier == 2 && !"/".equals(textViews[shotNumber-2].getText().toString())){
            int scoreInt = Integer.parseInt(score);
            StrikeMultiplier(scoreInt);
            for (int i = 0; i <= 9; i++) {
                buttons[i].setVisibility(View.VISIBLE);
            }
            butSpare.setVisibility(View.INVISIBLE);
            butX.setVisibility(View.VISIBLE);
            textViews[shotNumber-1].setText(score);

            EndGame();
        }
        else if(textViews[shotNumber-1] == scoreText21 && multiplier == 2 && "/".equals(textViews[shotNumber-2].getText().toString())){
            int scoreInt = Integer.parseInt(score);
            StrikeMultiplier(scoreInt);
            for (int i = 0; i <= 9; i++) {
                buttons[i].setVisibility(View.VISIBLE);
            }
            butSpare.setVisibility(View.INVISIBLE);
            butX.setVisibility(View.VISIBLE);
            textViews[shotNumber-1].setText(score);

            frames[frameNumber].setText("");
        }
        else if(textViews[shotNumber-1] == scoreText21 && multiplier == 1 && !"/".equals(textViews[shotNumber-2].getText().toString())){
            int scoreInt = Integer.parseInt(score);
            SpareMultiplier(scoreInt);
            textViews[shotNumber - 1].setText(score);

            int removeInts = 10 - scoreInt;

            for (int i = 9; i > removeInts - 1; i--) {
                buttons[i].setVisibility(View.INVISIBLE);
            }
            butSpare.setVisibility(View.VISIBLE);
            butX.setVisibility(View.INVISIBLE);
            EndGame();
        }
        else if(textViews[shotNumber-1] == scoreText21 && multiplier == 1 && "/".equals(textViews[shotNumber-2].getText().toString())){
            int scoreInt = Integer.parseInt(score);
            SpareMultiplier(scoreInt);
            textViews[shotNumber - 1].setText(score);

            int removeInts = 10 - scoreInt;

            for (int i = 9; i > removeInts - 1; i--) {
                buttons[i].setVisibility(View.INVISIBLE);
            }
            butSpare.setVisibility(View.VISIBLE);
            butX.setVisibility(View.INVISIBLE);
            frames[frameNumber].setText("");
        }
        else if(textViews[shotNumber-1] == scoreText20 && multiplier == 0 && "X".equals(textViews[shotNumber-2].getText().toString())){
            textViews[shotNumber - 1].setText(score);
        }

        else if(textViews[shotNumber-1] == scoreText21){
            FinalShot(score);
        }

        else if (shotNumber % 2 == 0 && frameNumber == 0) {
            String shot1, shot2;

            textViews[shotNumber - 1].setText(score);

            shot1 = textViews[shotNumber - 2].getText().toString();
            shot2 = textViews[shotNumber - 1].getText().toString();

            int shot1Int, shot2Int, frameScore;

            shot1Int = Integer.parseInt(shot1);
            shot2Int = Integer.parseInt(shot2);

            frameScore = shot1Int + shot2Int;
            String frameScoreString = valueOf(frameScore);

            frames[frameNumber].setText(frameScoreString);
            frameNumber++;
            for (int i = 0; i <= 9; i++) {
                buttons[i].setVisibility(View.VISIBLE);
            }
            butX.setVisibility(View.VISIBLE);
            butSpare.setVisibility(View.INVISIBLE);
            strikeCount = 0;
        }
        else if (shotNumber % 2 == 0 && multiplier == 0) {

            String shot1, shot2;

            textViews[shotNumber - 1].setText(score);

            shot1 = textViews[shotNumber - 2].getText().toString();
            shot2 = textViews[shotNumber - 1].getText().toString();

            int shot1Int, shot2Int, frameScore;

            shot1Int = Integer.parseInt(shot1);
            shot2Int = Integer.parseInt(shot2);

            frameScore = shot1Int + shot2Int;

            String lastFrame = frames[frameNumber - 1].getText().toString();

            int lastFrameInt = Integer.parseInt(lastFrame);

            finalScore = frameScore + lastFrameInt;
            String finalScoreString = valueOf(finalScore);

            frames[frameNumber].setText(finalScoreString);

            frameNumber++;
            for (int i = 0; i <= 9; i++) {
                buttons[i].setVisibility(View.VISIBLE);
            }
            butX.setVisibility(View.VISIBLE);
            butSpare.setVisibility(View.INVISIBLE);
            strikeCount = 0;
        }
        else if ( multiplier == 1) {
            int scoreInt = Integer.parseInt(score);
            SpareMultiplier(scoreInt);
            textViews[shotNumber - 1].setText(score);

            int removeInts = 10 - scoreInt;

            for (int i = 9; i > removeInts - 1; i--) {
                buttons[i].setVisibility(View.INVISIBLE);
            }
            butSpare.setVisibility(View.VISIBLE);
            butX.setVisibility(View.INVISIBLE);
        }
        else if (shotNumber % 2 == 0 && multiplier == 2) {
            int scoreInt = Integer.parseInt(score);
            StrikeMultiplier(scoreInt);
            textViews[shotNumber - 1].setText(score);

            for (int i = 0; i <= 9; i++) {
                buttons[i].setVisibility(View.VISIBLE);
            }
            butSpare.setVisibility(View.INVISIBLE);
            butX.setVisibility(View.VISIBLE);
        }
        else {
            textViews[shotNumber - 1].setText(score);

            int scoreInt = Integer.parseInt(score);

            int removeInts = 10 - scoreInt;

            for (int i = 9; i > removeInts - 1; i--) {
                buttons[i].setVisibility(View.INVISIBLE);
            }
            butSpare.setVisibility(View.VISIBLE);
            butX.setVisibility(View.INVISIBLE);
        }
        shotNumber++;
    }

    public void SpareMultiplier(int score) {
        String lastFrame = frames[frameNumber - 1].getText().toString();
        int lastFrameInt = Integer.parseInt(lastFrame);
        if (score == 10){
            int newLastFrameInt = lastFrameInt + score;
            String lastFrameString = valueOf(newLastFrameInt);
            frames[frameNumber - 1].setText(lastFrameString);

            lastFrame = frames[frameNumber - 1].getText().toString();
            lastFrameInt = Integer.parseInt(lastFrame);
            int finalScore = lastFrameInt + score;
            String finalScoreString = valueOf(finalScore);
            frames[frameNumber].setText(finalScoreString);
        }
        else {
            String lastFrameString = valueOf(lastFrameInt);
            frames[frameNumber - 1].setText(lastFrameString);

            lastFrame = frames[frameNumber - 1].getText().toString();
            lastFrameInt = Integer.parseInt(lastFrame);

            int finalScore = score + lastFrameInt;

            String finalScoreString = valueOf(finalScore);

            frames[frameNumber - 1].setText(finalScoreString);

            multiplier--;

        }

    }
    public void finalFrame(String score){
        int lastShotInt;
        int scoreInt;
        String notNullStrike = textViews[shotNumber - 4].getText().toString();

        if(finalFrameCount > 1){
        FinalShot(score);
        }
        if (score.equals("X") && multiplier == 2){
            lastShotInt = 10;
            scoreInt = 10;
            if (strikeCount > 1 && notNullStrike.equals("")) {
                String twoFramesAgo = frames[frameNumber - 2].getText().toString();
                int twoFramesAgoInt = Integer.parseInt(twoFramesAgo);
                twoFramesAgoInt = twoFramesAgoInt + lastShotInt;
                String twoFramesAgoString = valueOf(twoFramesAgoInt);
                frames[frameNumber - 2].setText(twoFramesAgoString);
            }
            if(strikeCount < 2) {
                String lastFrame = frames[frameNumber - 1].getText().toString();
                int lastFrameInt = Integer.parseInt(lastFrame);
                int newLastFrameInt = lastFrameInt + scoreInt;
                String lastFrameString = valueOf(newLastFrameInt);
                frames[frameNumber - 1].setText(lastFrameString);
            }
            else{
                String lastFrame = frames[frameNumber - 1].getText().toString();
                int lastFrameInt = Integer.parseInt(lastFrame);
                int newLastFrameInt = lastFrameInt + scoreInt + lastShotInt;
                String lastFrameString = valueOf(newLastFrameInt);
                frames[frameNumber - 1].setText(lastFrameString);
            }
            textViews[shotNumber-1].setText("X");

            multiplier--;

            shotNumber++;
        }
        else if(score.equals("X") && multiplier == 1) {
            String lastFrame = frames[frameNumber - 1].getText().toString();
            int lastFrameInt = Integer.parseInt(lastFrame);
            scoreInt = 10;

            int newLastFrameInt = lastFrameInt + scoreInt;
            String lastFrameString = valueOf(newLastFrameInt);
            frames[frameNumber - 1].setText(lastFrameString);

            textViews[shotNumber-1].setText("X");

            multiplier--;

            shotNumber++;
        }
        else if(score.equals("/") && multiplier == 2){
            textViews[shotNumber - 1].setText(valueOf("/"));
            String lastShot;
            scoreInt = 10;

            lastShot = textViews[shotNumber - 2].getText().toString();

            lastShotInt = Integer.parseInt(lastShot);

            if (frameNumber > 1 && strikeCount > 1) {
                String twoFramesAgo = frames[frameNumber - 2].getText().toString();
                int twoFramesAgoInt = Integer.parseInt(twoFramesAgo);
                twoFramesAgoInt = twoFramesAgoInt + lastShotInt;
                String twoFramesAgoString = valueOf(twoFramesAgoInt);
                frames[frameNumber - 2].setText(twoFramesAgoString);
            }
            String lastFrame = frames[frameNumber - 1].getText().toString();
            int lastFrameInt = Integer.parseInt(lastFrame);
            int newLastFrameInt = lastFrameInt + scoreInt + lastShotInt;
            String lastFrameString = valueOf(newLastFrameInt);
            frames[frameNumber - 1].setText(lastFrameString);
            shotNumber++;
            multiplier--;
            multiplier--;
        }
        else if(score.equals("/") && multiplier == 1){
            textViews[shotNumber - 1].setText(valueOf("/"));
            String lastFrame = frames[frameNumber - 1].getText().toString();
            int lastFrameInt = Integer.parseInt(lastFrame);
            finalScore = 0;
            scoreInt = 10;
            int newLastFrameInt = lastFrameInt + scoreInt;
            String lastFrameString = valueOf(newLastFrameInt);
            frames[frameNumber - 1].setText(lastFrameString);

            lastFrame = frames[frameNumber - 1].getText().toString();
            lastFrameInt = Integer.parseInt(lastFrame);
            int finalScore = lastFrameInt + scoreInt;
            String finalScoreString = valueOf(finalScore);
            frames[frameNumber].setText(finalScoreString);
            lastFrameString = valueOf(lastFrameInt);
            frames[frameNumber - 1].setText(lastFrameString);
            multiplier--;
            shotNumber++;
        }
        else if(score.equals("/")){
            textViews[shotNumber - 1].setText(valueOf("/"));
            shotNumber++;
        }
        else if(score.equals("X")){
            textViews[shotNumber-1].setText("X");
            shotNumber++;
        }
        finalFrameCount++;
    }
    public void StrikeMultiplier(int score) {

            int lastShotInt;
            String lastShot;
            String notNullStrike = textViews[shotNumber - 4].getText().toString();

            if (score == 10) {

                lastShotInt = 10;

                 if (frameNumber > 1 && strikeCount > 1 && notNullStrike.equals("")) {
                    String twoFramesAgo = frames[frameNumber - 2].getText().toString();
                    int twoFramesAgoInt = Integer.parseInt(twoFramesAgo);
                    twoFramesAgoInt = twoFramesAgoInt + lastShotInt;
                    String twoFramesAgoString = valueOf(twoFramesAgoInt);
                    frames[frameNumber - 2].setText(twoFramesAgoString);
                    }
                    if(strikeCount < 2) {
                        String lastFrame = frames[frameNumber - 1].getText().toString();
                        int lastFrameInt = Integer.parseInt(lastFrame);
                        int newLastFrameInt = lastFrameInt + score;
                        String lastFrameString = valueOf(newLastFrameInt);
                        frames[frameNumber - 1].setText(lastFrameString);
                    }
                    else{
                        String lastFrame = frames[frameNumber - 1].getText().toString();
                        int lastFrameInt = Integer.parseInt(lastFrame);
                        int newLastFrameInt = lastFrameInt + score + lastShotInt;
                        String lastFrameString = valueOf(newLastFrameInt);
                        frames[frameNumber - 1].setText(lastFrameString);
                    }
                String lastFrame = frames[frameNumber - 1].getText().toString();
                int lastFrameInt = Integer.parseInt(lastFrame);
                int finalScore = lastFrameInt + score;
                String finalScoreString = valueOf(finalScore);
                frames[frameNumber].setText(finalScoreString);

                multiplier--;
                multiplier--;

            }
            else {
                lastShot = textViews[shotNumber - 2].getText().toString();
                lastShotInt = Integer.parseInt(lastShot);
                if (frameNumber > 1 && strikeCount > 1){
                        String twoFramesAgo = frames[frameNumber - 2].getText().toString();
                        int twoFramesAgoInt = Integer.parseInt(twoFramesAgo);
                        twoFramesAgoInt = twoFramesAgoInt + lastShotInt;
                        String twoFramesAgoString = valueOf(twoFramesAgoInt);
                        frames[frameNumber - 2].setText(twoFramesAgoString);

                        int newLastFrameInt = twoFramesAgoInt + 10 + lastShotInt + lastShotInt;
                        String lastFrameString = valueOf(newLastFrameInt);
                        frames[frameNumber - 1].setText(lastFrameString);
                }

                else {
                    String lastFrame = frames[frameNumber - 1].getText().toString();
                    int lastFrameInt = Integer.parseInt(lastFrame);
                    int newLastFrameInt = lastFrameInt + score + lastShotInt;
                    String lastFrameString = valueOf(newLastFrameInt);
                    frames[frameNumber - 1].setText(lastFrameString);
                }

                String lastFrame = frames[frameNumber - 1].getText().toString();
                int lastFrameInt = Integer.parseInt(lastFrame);
                int finalScore = lastFrameInt + score + lastShotInt;
                String finalScoreString = valueOf(finalScore);
                frames[frameNumber].setText(finalScoreString);

                multiplier--;
                multiplier--;
                frameNumber++;
            }
    }
    public void FinalShot(String score){
        int scoreInt = 0;
        int finalLastShotInt = 0, finalTwoShotsAgo = 0;

        textViews[shotNumber-1].setText(score);
        String lastShotString = textViews[shotNumber-2].getText().toString();

        String finalTwoShotsAgoString = textViews[shotNumber-3].getText().toString();

        if(!("X").equals(finalTwoShotsAgoString)){
            finalTwoShotsAgo = Integer.parseInt(finalTwoShotsAgoString);
        }
        else if(finalTwoShotsAgoString.equals("X")){
            finalTwoShotsAgo = 10;
        }
        if(!"X".equals(lastShotString) && !"/".equals(lastShotString)){
            finalLastShotInt = Integer.parseInt(lastShotString);
        }else
        if(lastShotString.equals("X")){
            finalLastShotInt = 10;
        }
        else if(lastShotString.equals("/")){
            finalLastShotInt = 10 - finalTwoShotsAgo;
        }
        if(!("X").equals(score) && !("/").equals(score)){
            scoreInt = Integer.parseInt(score);
        }else
        if(score.equals("X")){
            scoreInt = 10;
        }
        else if(score.equals("/")){
            scoreInt = 10 - finalLastShotInt;
        }
        String lastFrame = frames[frameNumber-1].getText().toString();
        int lastFrameInt = Integer.parseInt(lastFrame);

        int finalScore = lastFrameInt + scoreInt + finalLastShotInt + finalTwoShotsAgo;

        frames[frameNumber].setText(valueOf(finalScore));

        EndGame();
    }
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


    public String getTime(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm");
        date.setTimeZone(TimeZone.getTimeZone("GMT"));

        String localTime = date.format(currentLocalTime);

        return localTime;
    }
    public String getDate(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("dd:MM");

        String dateString = date.format(currentLocalTime);

        return dateString;
    }
    public String getDateTime(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("dd:MM:HH:mm");

        String dateTimeString = date.format(currentLocalTime);

        return dateTimeString;
    }
    public void EndGame(){
        String finalScoreString = frames[frames.length-1].getText().toString();
        int finalScore = Integer.parseInt(finalScoreString);
        int averageFrame;
        averageFrame = finalScore/10;
        CompletedGame cg = new CompletedGame(averageFrame, finalScore, noOfStrikes, noOfSpares, shotsCleared, getDate(), getTime(), getDateTime());
        final String user_id = mAuth.getCurrentUser().getUid();
        myRef.child("users").child(user_id).child("games").push().setValue(cg);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users").child(user_id).child("gameCount");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null) {
                    String gameCountString = dataSnapshot.getValue().toString();
                    int gameCount = Integer.parseInt(gameCountString);
                    gameCount++;
                    myRef.child("users").child(user_id).child("gameCount").setValue(gameCount);
                }
                else{
                    myRef.child("users").child(user_id).child("gameCount").setValue(1);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
        toastMessage("Game complete");

        Intent i = new Intent(Game.this, HomePage.class);
        startActivity(i);
        finish();
    }
}
