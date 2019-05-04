package com.bowling.edward.bowling;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class HomePage extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    ImageButton createGame, createTournament, viewWall, viewFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        createGame = findViewById(R.id.createGameButton);
        createTournament = findViewById(R.id.createTournamentButton);
        viewWall = findViewById(R.id.viewWallButton);
        viewFriends = findViewById(R.id.viewFriendsButton);

        createGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                GoToGame();
            }

        });
        createTournament.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                GoToTournament();
            }

        });
        viewWall.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                GoToWall();
            }

        });
        viewFriends.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                GoToStats();
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
        }
        else if (id == R.id.nav_home) {
            Intent i = new Intent(this, HomePage.class);
            startActivity(i);
        }
        else if (id == R.id.nav_statistics) {
            Intent i = new Intent(this, Statistics.class);
            startActivity(i);
        }
        else if (id == R.id.nav_view_friends) {
            Intent i = new Intent(this, FindFriends.class);
            startActivity(i);
        }
        else if (id == R.id.nav_tournament) {
            Intent i = new Intent(this, Tournament.class);
            startActivity(i);
        }
        else if (id == R.id.nav_view_wall) {
            Intent i = new Intent(this, Wall.class);
            startActivity(i);
        }
        else if (id == R.id.nav_find_alley) {
//            ShowAlleys alleys = new ShowAlleys();
//            alleys.showAlleys();
            Intent i = new Intent(this, LocalAlleys2.class);
            startActivity(i);

        }
        return false;
    }
    public void GoToGame(){
        Intent i = new Intent(HomePage.this, Game.class);
        startActivity(i);
    }
    public void GoToTournament(){
        Intent i = new Intent(HomePage.this, Tournament.class);
        startActivity(i);
    }
    public void GoToWall(){
        Intent i = new Intent(HomePage.this, Wall.class);
        startActivity(i);
    }
    public void GoToStats(){
        Intent i = new Intent(HomePage.this, AllStats.class);
        startActivity(i);
    }

}
