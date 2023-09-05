package com.example.basketapp;

import android.util.Log;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchesList {

    ArrayList<Match> mList = new ArrayList<Match>();


    // Constructor retrieving data from database through PHP file
    public MatchesList(String ip){
        String url="http://"+ip+"/Projects/application2022/getMatches.php";
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            mList = okHttpHandler.populateList(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Match> getmList() {
        return mList;
    }

    // Returning games playing in a specific date
    public List<String> getSpecificDateMatches(String date){
        List<String> tempList = new ArrayList<String>();

        for (Match m: mList) {
            tempList.add(m.getMatchToString(date));
        }

        if(tempList.isEmpty()){tempList.add("No matches scheduled");}
        return tempList;
    }
}
