package com.example.tobi.howmany.logic;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Tobi on 19.01.2019.
 */

public class Player implements Serializable{

    private int currentGuess;
    private String name;
    private String color;
    private int points;
    private int lastPointsEarned;

    public int getCurrentGuess() {
        return currentGuess;
    }

    public void setCurrentGuess(int currentGuess) {
        this.currentGuess = currentGuess;
    }

    public Player(String name, String color){
        this.name = name;
        this.color = color;
        this.points = 0;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getPoints() {
        return points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int pointsToAdd){this.points += pointsToAdd;}

    public int getLastPointsEarned() {
        return lastPointsEarned;
    }

    public void setLastPointsEarned(int lastPointsEarned) {
        this.lastPointsEarned = lastPointsEarned;
    }

}
