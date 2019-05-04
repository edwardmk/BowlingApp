package com.bowling.edward.bowling;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class AllStats extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    TextView avgFrame, avgClearFrames, avgScore, avgStrikes, gmsPlayed;
    private FirebaseAuth mAuth;
    public List<Integer> averageFrameList = new ArrayList<>();
    public List<Integer> averageClearCountList = new ArrayList<>();
    public List<Integer> averageScoreList = new ArrayList<>();
    public List<Integer> averageStrikesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_stats);
        mAuth = FirebaseAuth.getInstance();

        avgFrame = findViewById(R.id.averageFrame);
        avgClearFrames = findViewById(R.id.averageFrameClear);
        avgScore = findViewById(R.id.averageGameScore);
        avgStrikes = findViewById(R.id.averageStrikes);
        gmsPlayed = findViewById(R.id.gamesPlayed);

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }
        final String user_id = mAuth.getCurrentUser().getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users").child(user_id).child("gameCount");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String gameCountString = dataSnapshot.getValue().toString();
                int gameCount = Integer.parseInt(gameCountString);
                GamesPlayed(gameCountString);
                AverageFrames(gameCount);
                AverageClearedFrames(gameCount);
                AverageScore(gameCount);
                AverageStrikes(gameCount);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }
    private void GamesPlayed(String gameCount) {
        gmsPlayed.setText(gameCount);

    }

    private void AverageFrames(final int gameCount){
        final String user_id = mAuth.getCurrentUser().getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users").child(user_id).child("games");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    int average1 = ds.child("averageFrame").getValue(int.class);
                    averageFrameList.add(average1);
                    int sum = 0;
                    for(int i = 0; i < averageFrameList.size(); i++){
                        sum = sum + averageFrameList.get(i);
                    }
                    int averageFrameFinal = sum/gameCount;

                    String averageFrameFinalString = valueOf(averageFrameFinal);

                    avgFrame.setText(averageFrameFinalString);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }


    private void AverageClearedFrames(final int gameCount) {
        final String user_id = mAuth.getCurrentUser().getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users").child(user_id).child("games");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    int clearCount = ds.child("clearCount").getValue(int.class);
                    averageClearCountList.add(clearCount);
                    int sum = 0;
                    for(int i = 0; i < averageClearCountList.size(); i++){
                        sum = sum + averageClearCountList.get(i);
                    }
                    int averageclearCount = sum/gameCount;

                    String averageclearCountFinalString = valueOf(averageclearCount);

                    avgClearFrames.setText(averageclearCountFinalString);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }


    private void AverageScore(final int gameCount) {
        final String user_id = mAuth.getCurrentUser().getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users").child(user_id).child("games");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    int finalScore = ds.child("finalScore").getValue(int.class);
                    averageScoreList.add(finalScore);
                    int sum = 0;
                    for(int i = 0; i < averageScoreList.size(); i++){
                        sum = sum + averageScoreList.get(i);
                    }
                    int averageScoreFinal = sum/gameCount;

                    String averageScoreFinalString = valueOf(averageScoreFinal);

                    avgScore.setText(averageScoreFinalString);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }

    private void AverageStrikes(final int gameCount) {
        final String user_id = mAuth.getCurrentUser().getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users").child(user_id).child("games");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    int strikes = ds.child("strikeCount").getValue(int.class);
                    averageStrikesList.add(strikes);
                    int sum = 0;
                    for(int i = 0; i < averageStrikesList.size(); i++){
                        sum = sum + averageStrikesList.get(i);
                    }
                    int averageStrikesFinal = sum/gameCount;

                    String averageStrikesFinalString = valueOf(averageStrikesFinal);

                    avgStrikes.setText(averageStrikesFinalString);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
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
        else if (id == R.id.nav_statistics) {
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
}
