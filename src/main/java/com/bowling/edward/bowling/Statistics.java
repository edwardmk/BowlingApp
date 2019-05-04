package com.bowling.edward.bowling;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

import static java.lang.String.valueOf;

public class Statistics extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private FirebaseAuth mAuth;
    private String userID;
    LineChartView lineChartView;
    List<String> axisList = new ArrayList<>();
    List<Integer> yAxisList = new ArrayList<>();

    String[] axisData = new String[5];
    int[] yAxisData = new int[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        final List yAxisValues = new ArrayList();
        final List axisValues = new ArrayList();
        lineChartView = findViewById(R.id.chart);
        List lines = new ArrayList();
        final LineChartData data = new LineChartData();
        data.setLines(lines);

        Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));
        lines.add(line);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference statsRef = ref.child("users").child(userID).child("games");
        statsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int average1 = ds.child("averageFrame").getValue(int.class);
                    yAxisList.add(average1);
                }
                for (int i = 4; i >= 0; i--) {
                    int average = yAxisList.get(i);
                    yAxisData[i] = average;
                }
                for (int i = 0; i < yAxisData.length; i++) {
                    yAxisValues.add(new PointValue(i, yAxisData[i]));
                }
                Axis yAxis = new Axis();
                yAxis.setName("Average Score");
                yAxis.setTextColor(Color.parseColor("#03A9F4"));
                yAxis.setTextSize(16);
                data.setAxisYLeft(yAxis);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        ref = FirebaseDatabase.getInstance().getReference();
        statsRef = ref.child("users").child(userID).child("games");
        statsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String dateTime = ds.child("date").getValue().toString();
                    axisList.add(dateTime);
                }
                for (int i = 4; i >= 0; i--) {
                    String dateTime = axisList.get(i);
                    axisData[i] = dateTime;
                }
                for (int i = 0; i < axisData.length; i++) {
                    axisValues.add(i, new AxisValue(i).setLabel(valueOf(axisData[i])));
                }
                Axis axis = new Axis();
                axis.setValues(axisValues);
                axis.setTextSize(16);
                axis.setTextColor(Color.parseColor("#03A9F4"));
                data.setAxisXBottom(axis);

                lineChartView.setLineChartData(data);
                Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
                viewport.top = 30;
                lineChartView.setMaximumViewport(viewport);
                lineChartView.setCurrentViewport(viewport);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
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
