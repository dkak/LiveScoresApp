package com.example.basketapp;

import android.util.Log;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Match {

    private int id;
    private String homeTeamName;
    private String awayTeamName;
    private int pointsHomeTeam;
    private int pointsAwayTeam;
    private String date;

    public Match(int id, String homeTeamName, String awayTeamName, int pointsHomeTeam, int pointsAwayTeam, String date) {
        this.id = id;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.pointsHomeTeam = pointsHomeTeam;
        this.pointsAwayTeam = pointsAwayTeam;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public int getPointsHomeTeam() {
        return pointsHomeTeam;
    }

    public int getPointsAwayTeam() {
        return pointsAwayTeam;
    }

    public String getDate() {
        return date.substring(0,10);
    }

    public String getTime() {return date.substring(11,16);}


    public String getMatchToString(Date matchDate){

        Date localDate = DateTime.now().toDate();

        String matchDetails=getTime();

        if(match)

        if(matchDate.before(localDate)){

        }

        String text = getHomeTeamName() + "  " + matchDetails + "  " + getAwayTeamName();

        return text;
    }
}
