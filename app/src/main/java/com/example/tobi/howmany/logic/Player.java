package com.example.tobi.howmany.logic;

/**
 * Created by Tobi on 19.01.2019.
 */

public class Player {

    private String name;
    private String color;
    private int points;

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

}
