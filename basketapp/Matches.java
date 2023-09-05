package com.example.basketapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;
import com.harrywhewell.scrolldatepicker.OnDateSelectedListener;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Matches extends AppCompatActivity {
    private OkHttpHandler ok;
    private String myIP = "192.168.56.1";
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private MatchesList ml;
    private DayScrollDatePicker dayDatePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ok = new OkHttpHandler();
        try {
            ml = new MatchesList(myIP);
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);

        //Connecting with XML activity_matches file
        setContentView(R.layout.activity_matches);

        getSupportActionBar().hide();

        //Bottom navigation menu connection
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.matches);


        //Bottom navigation menu listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.matches:
                        return true;
                    case R.id.ranking:
                        startActivity(new Intent(getApplicationContext(),Ranking.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



        //Single-Row scrollable calendar
        dayDatePicker = findViewById(R.id.dayDatePicker);

        //Setting current dates on calendar
        DateTime localDate  = DateTime.now();
        int month=localDate.getMonthOfYear();
        int year = localDate.getYear();
        dayDatePicker.setStartDate(localDate.getDayOfMonth()-4,month,year);
        dayDatePicker.setEndDate(localDate.getDayOfMonth()+4,month,year);

        //Calendar listener
        dayDatePicker.getSelectedDate(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@Nullable Date date) {

                //Formatting chosen date and converting to String
//                SimpleDateFormat spf= new SimpleDateFormat("yyyy-MM-dd");
//                String dateAsString = spf.format(date);

                // Filling list with selected date's games
                List<String> tempList = ml.getSpecificDateMatches(date);

                //Filling ListView
                listView = (ListView) findViewById(R.id.listView);
                adapter = new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.activity_matches_list_item,R.id.matches_list_item,
                        tempList);
                listView.setAdapter(adapter);
            }
        });
    }
}